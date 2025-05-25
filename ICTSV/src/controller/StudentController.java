package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentController extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/StudentLayout.fxml"));
        Scene scene = new Scene(root);
        
        stage.setTitle("Student Management Page");
        stage.setMinWidth(1600);
        stage.setMinHeight(1000);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);  // <-- Hàm này sẽ gọi phương thức start()
    }
}
