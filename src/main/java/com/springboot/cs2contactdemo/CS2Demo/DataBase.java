package com.springboot.cs2contactdemo.CS2Demo;

import java.sql.*;


public class DataBase {
    private Connection con;
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String userName = "root";
    private String password = "password";
    private String url = "jdbc:mysql://localhost:3306/contact_db";


    //建立数据库连接
    public DataBase() {

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void exit() {
        try {
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 根据名字查询
    public ResultSet select_name(String name) throws SQLException {
        String sql = "select * from contact where username = ?";

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, name);

        ResultSet rs = pstmt.executeQuery();

        return rs;
    }

    // 根据电话号码查询
    public ResultSet select_phone(String phone) throws SQLException {
        String sql = "select * from contact where phone = ?";

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, phone);

        ResultSet rs = pstmt.executeQuery();

        return rs;
    }

    // 增
    public Boolean insert(String name, String address,String phone) {
        String sql = "insert into contact values (null, ?,?,?)";

        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setString(3, phone);

            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    // 修改 名字
    public void update_name(int id, String name) {
        String sql = "update contact set username = ? where id= ? ";// 生成一条mysql语句

        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //修改地址
    public void update_address(int id, String adress) {
        String sql = "update contact set address = ? where id= ? ";// 生成一条mysql语句

        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, adress);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 修改电话
    public void update_phone(int id, String phone) {
        String sql = "update contact set phone = ? where id= ? ";// 生成一条mysql语句

        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, phone);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 根据id删除
    public Boolean delete(int id) {
        String sql = "delete from contact where id=?";

        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    // 根据名字删除
    public Boolean delete(String name) {
        String sql = "delete from contact where username=?";

        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);

            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

}
