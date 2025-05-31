package test;

import java.io.File;

import model.Activity;
import model.Admin;

public class ActivityTest {
    public static void main(String[] args) {
        // Tạo hoạt động mới
        Activity newActivity = new Activity("HocTap", "Quản lý công việc", true, 15, "29/05/2025", "Bách khoa");

        // Tạo tài khoản admin
        Admin admin = new Admin("admin001", "admin", "admin123");

        // Thêm hoạt động và lưu vào file JSON
       admin.addNewActivity(newActivity);

        // In ra tất cả các hoạt động
        admin.printActivity();
        
       //admin.removeOutdatedActivity(newActivity);

    }
}
