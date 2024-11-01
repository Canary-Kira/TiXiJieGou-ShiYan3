package com.springboot.cs2contactdemo.CS3Demo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

    //注入数据库
    private DataBase db = new DataBase();

    //判断联系人是否存在
    public boolean checkContactById(int id) throws SQLException {
        DataBase db = new DataBase();
        return db.checkUserById(id);
    }

    //查看联系人
    public ResultSet ViewContact (String name) throws SQLException {
        DataBase db = new DataBase();
        return db.select_name(name);
    }

    //添加联系人
    public boolean AddContact(String name, String address ,String phone) {
        return db.insert(name, address, phone);
    }

    //修改联系人
    public void ModifyContact(int id, String name, String address, String phone) {
        db.update_name(id,name);
        db.update_address(id,address);
        db.update_phone(id,phone);
    }

    //删除联系人
    public boolean DeleteContact (int id) {
        return db.delete(id);
    }

    //退出程序
    public void ShutDown(){
        db.exit();
    }

}
