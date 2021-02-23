package cn.com.egova.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHelper {
    public static final String url = "jdbc:mysql://localhost:3306/cscgdb0330";
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "123456";  


    public Connection conn = null;
    public PreparedStatement pst = null;
  
    public DBHelper(String sql) {
        try {
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接
            
            pst = conn.prepareStatement(sql);//准备执行语句
        } catch (Exception e) {
            System.out.println("连接数据库出现异常:"+e);
        }
    }
     
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {
            System.out.println("关闭数据库连接发生错误:"+e);
        }  
    }  
}  