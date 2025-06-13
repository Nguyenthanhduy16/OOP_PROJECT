package controller;

import entity.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.function.Consumer;

public class StudentInfoController {
    @FXML
    private Label labelName;
    @FXML
    private Label labelID;
    @FXML
    private ImageView imageView;
    @FXML
    private Button infoButton;

    private Student student;
    private Consumer<Student> onInfoClicked;

    public void setData(Student student) {
        this.student = student;
        labelName.setText(student.getUserName());
        labelID.setText(student.getUserID());
        
        String path = "/view/avatar/" + student.getUserName() + ".jpg";
        Image image = new Image(getClass().getResourceAsStream(path));
        imageView.setImage(image);
        
        double SIZE = 150;
        imageView.setFitWidth(SIZE);
        imageView.setFitHeight(SIZE);
        imageView.setPreserveRatio(false);

        // ---- Clip (crop) trung tâm
        Rectangle clip = new Rectangle(SIZE, SIZE);
        imageView.setClip(clip);
        // Nếu muốn set ảnh riêng cho từng sinh viên, có thể sửa ở đây
        // imageView.setImage(new Image("file:path/to/avatar.png"));
    }
    


    public Student getStudent() {
        return student;
    }

    public void setOnInfoClicked(Consumer<Student> c) { this.onInfoClicked = c; }

    @FXML
    private void initialize() {
        infoButton.setOnAction(e -> {
            if (onInfoClicked != null) onInfoClicked.accept(student);
        });
    }
} 