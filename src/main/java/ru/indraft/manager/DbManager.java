package ru.indraft.manager;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import ru.indraft.database.model.Instrument;

import java.io.IOException;
import java.sql.SQLException;

public class DbManager {

    private static final String DATA_BASE_URL = "jdbc:h2:./TIStatDB";

    private static ConnectionSource connectionSource;

    public static void initDataBase() {
        createConnectionSource();
        // dropTables();
        createTables();
        closeConnectionSource();
    }

    private static void createConnectionSource() {
        try {
            connectionSource = new JdbcConnectionSource(DATA_BASE_URL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ConnectionSource getConnectionSource() {
        if (connectionSource == null) {
            createConnectionSource();
        }
        return connectionSource;
    }

    public static void createTables() {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Instrument.class);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void dropTables() {
        try {
            TableUtils.dropTable(connectionSource, Instrument.class, true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void closeConnectionSource() {
        if (connectionSource != null) {
            try {
                connectionSource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
