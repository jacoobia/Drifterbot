package com.jacoobia.drifterbot.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.sql.DataSource;

import static com.jacoobia.drifterbot.database.DataSourceFactory.DataSourceProperty.*;
import static com.jacoobia.drifterbot.utils.PropertyFileReader.read;

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

    public static DataSource getDataSource()
    {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(read(URL.getProperty()));
        dataSource.setUser(read(USER.getProperty()));
        dataSource.setPassword(read(PASSWORD.getProperty()));
        return dataSource;
    }

}
