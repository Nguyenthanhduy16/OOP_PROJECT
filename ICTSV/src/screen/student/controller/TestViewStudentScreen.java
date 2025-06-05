package screen.student.controller;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Activity;
import model.Admin;
import model.Student;
import model.User;

public class TestViewStudentScreen extends Application {
	private static Student student;
	private static Admin admin;
    @Override
    public void start(Stage stage) throws Exception {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/student/view/StudentLayout.fxml"));
        Parent root = loader.load();

        // Lấy controller từ FXMLLoader
        StudentController controller = loader.getController();
        // Truyền dữ liệu admin và student vào controller
        controller.setAdmin(admin);
        controller.setStudent(student);	
        controller.initData(student); // Gọi phương thức initData để khởi tạo dữ liệu cho student
        
        Scene scene = new Scene(root);
        stage.setTitle("Student Management Page");
        stage.setMinWidth(1000);
        stage.setMinHeight(600); 
        stage.setScene(scene); 
        stage.show();  
  
    } 

    public static void main(String[] args) {
    	try {
	        ObjectMapper mapper = new ObjectMapper();

	        File file = new File("src/data/data.json"); // đường dẫn file JSON
            List<User> users = mapper.readValue(new File("src/data/data.json"),
                    new TypeReference<List<User>>() {});

	        for (User u : users) {
	            if (u instanceof Student && u.getUserID().equals("20235804")) {
	                student = (Student) u;
	            }
	            if (u instanceof Admin && u.getUserID().equals("GV1001")) {
	                admin = (Admin) u;
	            }
	        }

	        if (student == null) {
	            System.out.println("Student không tồn tại trong JSON.");
	            return;
	        }
	        // Gán admin cho student 
	        student.setAdmin(admin);
	        System.out.println("Admin activities: " + admin.getAllActivities().size());
	        System.out.println("All activities:");
	        for (Activity a : admin.getAllActivities()) {
	            System.out.println(" - " + a.getName());
	        }
	        System.out.println("Student activities:");
	        for (Activity a : student.getRegisteredActivities()) {
	            System.out.println(" - " + a.getName());
	        }
	        launch(args);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
    }
}