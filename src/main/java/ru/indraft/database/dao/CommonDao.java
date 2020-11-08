package ru.indraft.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.indraft.manager.DbManager;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public abstract class CommonDao<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);
    private final Class<T> tClass;

    protected ConnectionSource connectionSource;

    public CommonDao(Class<T> tClass) {
        this.connectionSource = DbManager.getConnectionSource();
        this.tClass = tClass;
    }

    public Dao<T, ?> getDao() {
        try {
            return DaoManager.createDao(connectionSource, tClass);
        } catch (SQLException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        return null;
    }

    public void createOrUpdate(T model) {
        var dao = getDao();
        try {
            dao.createOrUpdate(model);
        } catch (SQLException e) {
            LOGGER.error(e.getLocalizedMessage());
        } finally {
            DbManager.closeConnectionSource();
        }
    }

    public void createOrUpdate(Collection<T> models) {
        var dao = getDao();
        try {
            for (var model : models) {
                dao.createOrUpdate(model);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getLocalizedMessage());
        } finally {
            DbManager.closeConnectionSource();
        }
    }

    public List<T> queryForAll() {
        var dao = getDao();
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            LOGGER.error(e.getLocalizedMessage());
        } finally {
            DbManager.closeConnectionSource();
        }
        return null;
    }

}
