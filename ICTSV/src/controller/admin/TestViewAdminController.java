package controller.admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TestViewAdminController extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Admin.fxml"));
            Parent root = loader.load();

            // Create and set up the scene
            Scene scene = new Scene(root, 1600, 1000); // Match FXML's prefWidth and prefHeight
            primaryStage.setScene(scene);

            // Configure the stage
            primaryStage.setTitle("Admin Management System");
            primaryStage.setResizable(false); // Optional: Prevent resizing
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load FXML file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}