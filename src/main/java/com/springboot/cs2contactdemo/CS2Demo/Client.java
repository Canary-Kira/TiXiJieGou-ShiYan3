package com.springboot.cs2contactdemo.CS2Demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        DataBase db = new DataBase();
        int choice;
        do {
            System.out.println("-------个人通讯录系统---------");
            System.out.println("1. 查看联系人");
            System.out.println("2. 添加联系人");
            System.out.println("3. 修改联系人");
            System.out.println("4. 删除联系人");
            System.out.println("0. 退出");
            System.out.print("请输入您的选择: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // 清除换行符

            switch (choice) {
                case 1:
                    System.out.print("请输入姓名: ");
                    String select_name = scanner.next();
                    ResultSet rs = db.select_name(select_name);

                    if (rs != null) {
                        while (rs.next()) {
                            System.out.println(rs.getString(1)+"    "+rs.getString(2)+"    "+
                                               rs.getString(3)+"    "+rs.getString(4));
                        }
                    }

                    break;
                case 2:
                    System.out.print("请输入姓名: ");
                    String name = scanner.next();
                    System.out.print("请输入地址: ");
                    String address = scanner.next();
                    System.out.print("请输入电话: ");
                    String phone = scanner.next();

                    if(db.insert(name, address, phone)) {
                        System.out.println("添加成功");
                    }else{
                        System.out.println("添加失败");
                    }
                    break;
                case 3:
                    System.out.print("请输入要修改的联系人ID: ");
                    int update_Id = scanner.nextInt();
                    scanner.next();
                    System.out.print("请输入新的姓名: ");
                    String newName = scanner.next();
                    System.out.print("请输入新的地址: ");
                    String newAddress = scanner.next();
                    System.out.print("请输入新的电话: ");
                    String newPhone = scanner.next();

                    db.update_name(update_Id,newName);
                    db.update_address(update_Id,newAddress);
                    db.update_phone(update_Id,newPhone);
                    System.out.println("修改成功");
                    break;
                case 4:
                    System.out.print("请输入要删除的联系人ID: ");
                    int delete_Id = scanner.nextInt();
                    if(db.delete(delete_Id)) {
                        System.out.println("删除成功");
                    }else{
                        System.out.println("删除失败");
                    }
                    break;
                case 0:
                    System.out.println("退出系统");
                    db.exit();
                    break;
                default:
                    System.out.println("无效选择，请重试");
            }
        } while (choice != 0);
        scanner.close();

    }

}
