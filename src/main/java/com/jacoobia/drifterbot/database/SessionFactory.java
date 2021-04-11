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

/**
 * The SQL sessions factory that builds an SQL sessions using the MyBatis framework
 * and allows us to get the mappers stored into memory to run basic database operations
 * using annotations only.
 * MyBatis also allows for an XML based implementation for more advanced operations but
 * since we only require basic thing such as select, update etc then using annotations
 * is way less boilerplate code.
 */
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
