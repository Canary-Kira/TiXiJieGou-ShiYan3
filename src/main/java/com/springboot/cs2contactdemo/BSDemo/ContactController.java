package com.springboot.cs2contactdemo.BSDemo;


import com.springboot.cs2contactdemo.BSDemo.dto.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    //添加联系人
    @PostMapping("/add")
    public Response addContact(@RequestBody Contact contact) {
        try {
            boolean result = contactService.AddContact(contact.getName(), contact.getAddress(), contact.getPhone());
            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(false);
        }
    }

    //获取联系人列表
    @GetMapping("/contacts")
    public List<Contact> getAllContacts() throws SQLException {
        try {
            return contactService.getAllContacts();
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常情况，例如返回空列表或错误信息
            return null;
        }
    }


    //删除联系人
    @PostMapping("/delete")
    public Response deleteContact(@RequestBody Contact contact) {
        try {
            boolean result = contactService.DeleteContact(contact.getId());
            return new Response(result);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(false);
        }
    }
}

