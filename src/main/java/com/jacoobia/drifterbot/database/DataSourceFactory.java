package com.jacoobia.drifterbot.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.sql.DataSource;

import static com.jacoobia.drifterbot.database.DataSourceFactory.DataSourceProperty.*;
import static com.jacoobia.drifterbot.utils.PropertyFileReader.read;

/**
 * The datasource factory that builds a datasource on demand,
 * only used once but keeps things small and neat and builds our
 * {@link MysqlDataSource} object.
 */
public class DataSourceFactory
{

    @Getter
    @AllArgsConstructor
    protected enum DataSourceProperty
    {
        URL("database.url"),
        DRIVER("database.driver"),
        USER("database.user"),
        PASSWORD("database.password");

        private final String property;
    }

    /**
     * Reads the property file to load the database
     * details and then build our datasource
     * @return a {@link MysqlDataSource} object
     */
    public static DataSource getDataSource()
    {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(read(URL.getProperty()));
        dataSource.setUser(read(USER.getProperty()));
        dataSource.setPassword(read(PASSWORD.getProperty()));
        return dataSource;
    }

}
