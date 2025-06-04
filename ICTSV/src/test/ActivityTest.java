package test;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Activity;
import model.Admin;
import model.User;

public class ActivityTest {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            // Đọc danh sách user từ file JSON
            List<User> users = mapper.readValue(new File("src/data/data.json"),
                    new TypeReference<List<User>>() {});

            // Kiểm tra nếu là Admin thì cho phép thêm và xóa hoạt động
            for (User user : users) {
                if (user instanceof Admin) {
                    Admin admin = (Admin) user;
                    System.out.println("\nĐăng nhập thành công với Admin: " + admin.getUserName());

                    // Ví dụ thêm hoạt động
                    Activity newActivity = new Activity("HocTap", "Quản lý hệ thống", false, 20, "29/05/2025", "Nhà C2 Đại học Bách khoa Hà Nội");
                    
                    //Thêm hoạt động
                    admin.addNewActivity(newActivity);
                    
                    //Xóa hoạt động
                    //admin.removeOutdatedActivity(newActivity);
                    
                    admin.printActivity();
                    
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
