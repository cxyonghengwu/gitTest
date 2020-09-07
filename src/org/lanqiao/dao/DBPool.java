package org.lanqiao.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class DBPool {
    //1、声明本身类型的对象。static。
    private static DataSource dataSource = new ComboPooledDataSource("c3p0-config");

    //2、私有化构造方法
    private DBPool() {
    }

    //3、定义方法用于获取本身对象
    public static DataSource getInstance() {
        return dataSource;
    }
}
