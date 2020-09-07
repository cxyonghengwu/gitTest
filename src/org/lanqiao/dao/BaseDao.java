package org.lanqiao.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<T> {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8";
    String name = "root";
    String password = "123";

    T t = null;
    Class<T> clazz;

    /**
     * 构造方法，通过反射得到泛型中的数据对象
     */
    public BaseDao() {
        clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * 获得数据库连接
     *
     * @return 数据库连接
     */
    private Connection getConn() {
        Connection conn = null;
        try {
            conn = DBPool.getInstance().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭连接
     *
     * @param resultSet
     * @param statement
     * @param connection
     */
    private void closeAll(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     *
     * @param statement
     * @param connection
     */
    private void closeAll(Statement statement, Connection connection) {
        try {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 有参数查询方法
     *
     * @param sql     查询语句
     * @param objects 参数数组
     * @return 查询结果集
     */
    public List<T> executeQuery(String sql, Object[] objects) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<>();
        try {
            conn = getConn();
            //3、statment对象创建 - 》 预编译
            stat = conn.prepareStatement(sql);//sql 发送到数据库 -> db 编译 sql
            for (int i = 0; i < objects.length; i++) {
                stat.setObject(i + 1, objects[i]);
            }
            //4、调用方法执行sql
            rs = stat.executeQuery();//把参数发送到数据库 - > 用参数替代？ -> 执行sql语句
            //5、处理返回结果(DQL) no DML
            ResultSetMetaData rsmd = rs.getMetaData();//获得头信息
            int columuCount = rsmd.getColumnCount();
            while (rs.next()) {
                T t = (T) clazz.newInstance();
                for (int i = 0; i < columuCount; i++) {
                    Field f = clazz.getDeclaredField(rsmd.getColumnName(i + 1));//列名 -> 属性名 -> 反射得到属性
                    f.setAccessible(true);
                    f.set(t, rs.getObject(i + 1));//反射给属性赋值
                }
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, stat, conn);
        }
        return list;
    }

    /**
     * 无参数查询方法
     *
     * @param sql 查询语句
     * @return 查询结果集
     */
    protected List<T> executeQuery(String sql) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<>();
        try {
            conn = getConn();
            //3、statment对象创建 - 》 预编译
            stat = conn.prepareStatement(sql);//sql 发送到数据库 -> db 编译 sql
            //4、调用方法执行sql
            rs = stat.executeQuery();//把参数发送到数据库 - > 用参数替代？ -> 执行sql语句
            //5、处理返回结果(DQL) no DML
            ResultSetMetaData rsmd = rs.getMetaData();//获得头信息
            int columuCount = rsmd.getColumnCount();
            while (rs.next()) {
                T t = (T) clazz.newInstance();
                for (int i = 0; i < columuCount; i++) {
                    Field f = clazz.getDeclaredField(rsmd.getColumnName(i + 1));//列名 -> 属性名 -> 反射得到属性
                    f.setAccessible(true);
                    f.set(t, rs.getObject(i + 1));//反射给属性赋值
                }
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, stat, conn);
        }
        return list;
    }

    /**
     * 查询记录条数
     *
     * @param sql 查询语句
     * @return 查询结果集
     */
    protected int executeCount(String sql) {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        int count = -1;
        try {
            conn = getConn();
            //3、statment对象创建 - 》 预编译
            stat = conn.prepareStatement(sql);//sql 发送到数据库 -> db 编译 sql
            //4、调用方法执行sql
            rs = stat.executeQuery();//把参数发送到数据库 - > 用参数替代？ -> 执行sql语句
            //5、处理返回结果(DQL) no DML
            ResultSetMetaData rsmd = rs.getMetaData();//获得头信息
            int columuCount = rsmd.getColumnCount();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, stat, conn);
        }
        return count;
    }

    /**
     * 执行DML操作
     *
     * @param sql
     * @param objects
     * @return 影响条数
     */
    protected int executeUpdate(String sql, Object[] objects) {
        Connection conn = null;
        PreparedStatement stat = null;
        int ret = 0;
        try {
            conn = getConn();
            //3、statment对象创建
            stat = conn.prepareStatement(sql);
            for (int i = 0; i < objects.length; i++) {
                stat.setObject(i + 1, objects[i]);
            }
            //4、调用方法执行sql
            ret = stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(stat, conn);
        }
        return ret;
    }
}
