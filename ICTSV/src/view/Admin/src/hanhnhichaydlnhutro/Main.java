package hanhnhichaydlnhutro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load FXML
        Parent root = FXMLLoader.load(getClass().getResource("demo.fxml"));

        // Tạo Scene và thêm CSS
        Scene scene = new Scene(root, 2000, 2000);
        scene.getStylesheets().addAll(
            // các file CSS phải nằm chung thư mục với demo.fxml hoặc adjust path tương ứng
            getClass().getResource("style/theme.css").toExternalForm(),
            getClass().getResource("style/button.css").toExternalForm(),
            getClass().getResource("style/check_box.css").toExternalForm(),
            getClass().getResource("style/layout.css").toExternalForm(),
            getClass().getResource("style/table.css").toExternalForm(),
            getClass().getResource("style/text.css").toExternalForm()
        );

        // Thiết lập Stage
        primaryStage.setTitle("Chương trình JavaFX cơ bản");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
