package screen;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import controller.StudentController;
import entity.Activity;
import entity.Admin;
import entity.Student;
import entity.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class ViewStudentScreen extends Application {
	private static Student student;
	private static Admin admin;
    @Override
    public void start(Stage stage) throws Exception {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/StudentLayout.fxml"));
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
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/view/img/logo.png")));
        stage.show();  
  
    } 

    public static void main(String[] args) {
    	launch(args);
    }
}