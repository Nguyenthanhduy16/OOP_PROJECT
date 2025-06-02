package screen.student.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Activity;
import model.Admin;
import model.Student;

public class TestViewStudentScreen extends Application {
	private static Student student;
	private static Admin admin;
    @Override
    public void start(Stage stage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("/screen/student/view/StudentLayout.fxml"));
      Scene scene = new Scene(root);
      
      stage.setTitle("Student Management Page");
      stage.setMinWidth(1000);
      stage.setMinHeight(600); 
      stage.setScene(scene); 
      stage.show();  
  
    } 

    public static void main(String[] args) {
//    	student = new Student();
//    	student.addActivity(new Activity("Hackathon 2025", "Nguyen Van A", true, 95, "2025-04-15", "Hanoi"));
////    	student.addActivity(new Activity("Robotics Competition", "Le Van C", true, 100, "2025-02-20", "Da Nang"));
////    	student.addActivity(new Activity("Cybersecurity Training", "Pham Thi D", false, 76, "2025-05-01", "Hue"));
////    	student.addActivity(new Activity("Tech Talk: Data Science", "Do Van E", true, 90, "2025-01-28", "Can Tho"));
    		admin = new Admin();
        admin.addActivity(new Activity("Học tập", "Hackathon 2025" , "2024.2", false, 95, "2025-04-15", "Hanoi"));
        admin.addActivity(new Activity("AI Workshop", "AI Workshop", "2023.1", false, 88, "2025-03-10", "Ho Chi Minh City"));
        admin.addActivity(new Activity("Chính trị văn hóa", "Robotics Competition", "2023.2", false, 100, "2025-02-20", "Da Nang"));
        admin.addActivity(new Activity("Tổ chức kỷ luật", "Cybersecurity Training","2024.2" ,false, 76, "2025-05-01", "Hue"));

        launch(args);
    }
}