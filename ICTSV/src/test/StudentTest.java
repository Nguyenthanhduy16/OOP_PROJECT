package test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Activity;
import model.Student;
import model.User;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class StudentTest {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            
            // Đọc danh sách người dùng
            List<User> users = mapper.readValue(new File("src/data/data.json"),
                    new TypeReference<List<User>>() {});

            // Đọc tất cả hoạt động từ activity.json
            List<Activity> allActivities = mapper.readValue(new File("src/data/activity.json"),
                    new TypeReference<List<Activity>>() {});

            Scanner sc = new Scanner(System.in);

            for (User user : users) {
                if (user instanceof Student) {
                    Student student = (Student) user;
                    System.out.println("\nĐăng nhập thành công với Student: " + student.getUserName());

                    boolean running = true;
                    while (running) {
                        System.out.println("\n===== Menu Student =====");
                        System.out.println("1. In tất cả hoạt động");
                        System.out.println("2. Tìm hoạt động theo tên");
                        System.out.println("3. Xem các hoạt động đã đăng ký");
                        System.out.println("4. Thêm hoạt động");
                        System.out.println("5. Xóa hoạt động");
                        System.out.println("6. Tính tổng điểm");
                        System.out.println("0. Thoát");
                        System.out.print("Chọn chức năng: ");

                        int choice = sc.nextInt();
                        sc.nextLine(); // clear buffer

                        switch (choice) {
                            case 1:
                                student.printAllActivities();
                                break;
                            case 2:
                                System.out.print("Nhập từ khóa: ");
                                String keyword = sc.nextLine();
                                student.searchActivitiesByName(keyword).forEach(act ->
                                        System.out.println(act.getTitle() + " - " + act.getName() + " - Score: " + act.getScore()));
                                break;
                            case 3:
                                student.viewRegisteredActivities();
                                break;
                            case 4:
                                System.out.println("Danh sách tất cả hoạt động có thể đăng ký:");
                                for (int i = 0; i < allActivities.size(); i++) {
                                    Activity act = allActivities.get(i);
                                    System.out.println((i + 1) + ". " + act.getTitle() + " - " + act.getName());
                                }

                                System.out.print("Chọn số thứ tự hoạt động để thêm: ");
                                int index = sc.nextInt();
                                sc.nextLine();

                                if (index >= 1 && index <= allActivities.size()) {
                                    Activity selectedActivity = allActivities.get(index - 1);
                                    student.addActivity(selectedActivity.getName());  // sửa tại đây
                                    System.out.println("Đã thêm hoạt động: " + selectedActivity.getName());
                                } else {
                                    System.out.println("Lựa chọn không hợp lệ.");
                                }
                                break; // 🔥 QUAN TRỌNG: cần có break ở cuối mỗi case
                            case 5:
                                System.out.print("Tên hoạt động cần xóa: ");
                                String removeTitle = sc.nextLine();
                                student.removeActivity(removeTitle);
                                break;
                            case 6:
                                System.out.println("Tổng điểm: " + student.totalScore());
                                break;
                            case 0:
                                running = false;
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ.");
                        }
                    }
                }
            }

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi khi đọc danh sách user từ file data.json");
        }
    }
}
