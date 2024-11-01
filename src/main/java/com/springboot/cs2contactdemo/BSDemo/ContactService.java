package com.springboot.cs2contactdemo.BSDemo;

import org.springframework.stereotype.Service;
import com.springboot.cs2contactdemo.BSDemo.dto.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {
    //注入数据库
    private ContactMapper db = new ContactMapper();

    //判断联系人是否存在
    public boolean checkContactById(int id) throws SQLException {
        return db.checkUserById(id);
    }

    //查看联系人
    public ResultSet ViewContact (String name) throws SQLException {
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


    // 获取所有联系人
    public List<Contact> getAllContacts() throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        ResultSet rs = db.selectAllContacts();
        while (rs.next()) {
            Contact contact = new Contact();
            contact.setId(rs.getInt("id"));
            contact.setName(rs.getString("username"));
            contact.setAddress(rs.getString("address"));
            contact.setPhone(rs.getString("phone"));
            contacts.add(contact);
        }
        return contacts;
    }

    //退出程序
    public void ShutDown(){
        db.exit();
    }
}
