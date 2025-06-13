package controller;

import entity.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        Path diskImg = Paths.get("src", "avatar", student.getUserName() + ".jpg");
        Image img;

        if (Files.exists(diskImg)) 
        {
            img = new Image(diskImg.toUri().toString());
        } 
        else 
        {
        	img = new Image(getClass().getResource("/avatar/default.jpg").toExternalForm());
        }

        imageView.setImage(img);
        final double SIZE = 150;
        imageView.setFitWidth(SIZE);
        imageView.setFitHeight(SIZE);
        imageView.setPreserveRatio(false);
        imageView.setClip(new Rectangle(SIZE, SIZE));
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