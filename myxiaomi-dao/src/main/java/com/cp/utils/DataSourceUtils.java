package com.cp.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * cp 2019-09-10  14:19
 */
public class DataSourceUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> threadLocal;
    static {
        try {
            threadLocal = new ThreadLocal<>();
            Properties properties = new Properties();
            InputStream is = DataSourceUtils.class.getClassLoader().getResourceAsStream("database.properties");
            properties.load(is);
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("初始化连接池失败"+e,e );
        }

    }

    public static DataSource getDataSource(){
        return dataSource;
    }

    public static Connection getConnection() throws Exception {
        Connection conn = threadLocal.get();
        if(conn==null){
            conn=dataSource.getConnection();
            threadLocal.set(conn);
        }
        return conn;
    }

    public static void startTransaction() throws Exception{
        getConnection().setAutoCommit(false);
    }

    public static void commit() throws Exception{
        getConnection().commit();
    }

    public static void rollback() throws Exception{
        getConnection().rollback();
    }

    public static void close() throws Exception{
        getConnection().close();
        threadLocal.remove();
    }


}
