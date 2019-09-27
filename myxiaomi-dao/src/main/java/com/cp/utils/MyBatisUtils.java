package com.cp.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * cp 2019-09-19  20:26
 */
public class MyBatisUtils {
    private static SqlSessionFactory factory;
    private static Logger logger;
    static {
        try {
            logger=Logger.getLogger(MyBatisUtils.class.getName());
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            factory=new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("mybatis工厂初始化失败");
        }
    }

    public static SqlSession openSession(){
        return factory.openSession();
    }
}
