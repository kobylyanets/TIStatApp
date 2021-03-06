package ru.indraft.manager;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.indraft.database.model.Instrument;
import ru.indraft.database.model.Operation;

import java.io.IOException;
import java.sql.SQLException;

public class DbManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbManager.class);

    private static final String DATA_BASE_URL = "jdbc:h2:./TIStatDB";

    private static ConnectionSource connectionSource;

    public static void initDataBase() {
        createConnectionSource();
//        dropTables();
        createTables();
        closeConnectionSource();
    }

    private static void createConnectionSource() {
        try {
            connectionSource = new JdbcConnectionSource(DATA_BASE_URL);
        } catch (SQLException e) {
            LOGGER.error(e.getLocalizedMessage());
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
            TableUtils.createTableIfNotExists(connectionSource, Operation.class);
        } catch (SQLException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
    }

    private static void dropTables() {
        try {
            TableUtils.dropTable(connectionSource, Instrument.class, true);
            TableUtils.dropTable(connectionSource, Operation.class, true);
        } catch (SQLException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
    }

    public static void closeConnectionSource() {
        if (connectionSource != null) {
            try {
                connectionSource.close();
            } catch (IOException e) {
                LOGGER.error(e.getLocalizedMessage());
            }
        }
    }

}
