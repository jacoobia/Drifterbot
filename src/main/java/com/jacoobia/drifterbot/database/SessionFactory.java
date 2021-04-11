package com.jacoobia.drifterbot.database;

import com.jacoobia.drifterbot.database.mapper.MetricsMapper;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

public class SessionFactory
{

    private final SqlSessionFactory sqlSessionFactory;

    public SessionFactory()
    {
        DataSource dataSource = DataSourceFactory.getDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(MetricsMapper.class);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }

    public SqlSession getSession()
    {
        return sqlSessionFactory.openSession();
    }

    public static MetricsMapper getMetricsMapper(SqlSession session)
    {
        return session.getMapper(MetricsMapper.class);
    }

}
