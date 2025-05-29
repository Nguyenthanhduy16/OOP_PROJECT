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
    public void start(Stage primaryStage) throws Exception {
        final String STUDENT_FXML_FILE_PATH = "/screen/student/view/StudentView.fxml"; // Kiểm tra tên file "StudentView.fxml" có đúng không
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STUDENT_FXML_FILE_PATH));
        
		ViewStudentController viewStoreController = new ViewStudentController(student, admin);
		fxmlLoader.setController(viewStoreController);
        
        Parent root = fxmlLoader.load();	

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ICTSV");
        primaryStage.show();
    } 

    public static void main(String[] args) {
    	student = new Student();
    	student.addActivity(new Activity("Hackathon 2025", "Nguyen Van A", true, 95, "2025-04-15", "Hanoi"));
//    	student.addActivity(new Activity("Robotics Competition", "Le Van C", true, 100, "2025-02-20", "Da Nang"));
//    	student.addActivity(new Activity("Cybersecurity Training", "Pham Thi D", false, 76, "2025-05-01", "Hue"));
//    	student.addActivity(new Activity("Tech Talk: Data Science", "Do Van E", true, 90, "2025-01-28", "Can Tho"));
        admin = new Admin();
        admin.addActivity(new Activity("Hackathon 2025", "Nguyen Van A", false, 95, "2025-04-15", "Hanoi"));
        admin.addActivity(new Activity("AI Workshop", "Tran Thi B", false, 88, "2025-03-10", "Ho Chi Minh City"));
        admin.addActivity(new Activity("Hackathon 2025", "Nguyen Van A", false, 95, "2025-04-15", "Hanoi"));
        admin.addActivity(new Activity("AI Workshop", "Tran Thi B", false, 88, "2025-03-10", "Ho Chi Minh City"));
        admin.addActivity(new Activity("Robotics Competition", "Le Van C", false, 100, "2025-02-20", "Da Nang"));
        admin.addActivity(new Activity("Cybersecurity Training", "Pham Thi D", false, 76, "2025-05-01", "Hue"));
        admin.addActivity(new Activity("Tech Talk: Data Science", "Do Van E", false, 90, "2025-01-28", "Can Tho"));
        admin.addActivity(new Activity("Startup Pitching", "Pham Van G", false, 0, "2025-06-12", "Da Lat"));
        admin.addActivity(new Activity("Cloud Computing Seminar", "Nguyen Van H", false, 0, "2025-07-01", "Nha Trang"));
        launch(args);
    }
}