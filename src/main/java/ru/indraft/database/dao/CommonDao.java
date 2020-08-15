package ru.indraft.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import ru.indraft.manager.DbManager;
import ru.indraft.database.model.BaseModel;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public abstract class CommonDao {

    protected ConnectionSource connectionSource;

    public CommonDao() {
        this.connectionSource = DbManager.getConnectionSource();
    }

    public <T extends BaseModel, I> Dao<T, I> getDao(Class<T> clazz) {
        try {
            return DaoManager.createDao(connectionSource, clazz);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public <T extends BaseModel, I> void createOrUpdate(T model, Class<T> tClass) {
        Dao<T, I> dao = getDao(tClass);
        try {
            dao.createOrUpdate(model);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbManager.closeConnectionSource();
        }
    }

    public <T extends BaseModel, I> void createOrUpdate(Collection<T> models, Class<T> tClass) {
        Dao<T, I> dao = getDao(tClass);
        try {
            for (var model : models) {
                dao.createOrUpdate(model);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbManager.closeConnectionSource();
        }
    }

    public <T extends BaseModel, I> List<T> queryForAll(Class<T> tClass) {
        Dao<T,I> dao = getDao(tClass);
        try {
            return dao.queryForAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbManager.closeConnectionSource();
        }
        return null;
    }

}
