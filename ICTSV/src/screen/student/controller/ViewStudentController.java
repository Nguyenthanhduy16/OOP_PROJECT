package screen.student.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Student;

public class ViewStudentController {
    private Student student;
	public ViewStudentController(Student student) {
		this.student = student;
		
	}
	@FXML
	public void initialize() {
	    final String ITEM_FXML_FILE_PATH = "/screen/student/view/Activity.fxml";

	    int column = 0;
	    int row = 1;

	    try {
            for (int i = 0; i < student.getRegisteredActivities().size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ITEM_FXML_FILE_PATH));
                ActivityController controller = new ActivityController();
                fxmlLoader.setController(controller);
                
	            AnchorPane anchorPane = new AnchorPane();
	            anchorPane = fxmlLoader.load();

				controller.setData(student.getRegisteredActivities().get(i));


	            // Đưa AnchorPane vào grid
	            if (column == 3) {
	                column = 0;
	                row++;
	            }

				gridPane.add(anchorPane, column++, row);
	            GridPane.setMargin(anchorPane, new Insets(20, 10, 10, 10));
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
    @FXML
    private Label studentName;
    @FXML
    private ToggleGroup semesterViewActivity;
    @FXML
    private TextField searchTextField;
    @FXML
    private AnchorPane paneThanhDieuHuong;
    @FXML
    private ToggleGroup semesterViewActivity1;
    @FXML
    private AnchorPane Student_controller;
    @FXML
    private GridPane gridPane;

    @FXML
    void btnRegisterActivity(ActionEvent event) {
    	System.out.println("Clicked Register Activity");
    }

    @FXML
    void showRegistedActivity(ActionEvent event) {
    	System.out.println("Showing registered activities");
    }

    @FXML
    void showGrade(ActionEvent event) {

    }
    @FXML
    void showActivity(ActionEvent event) {

    }
    @FXML
    void logOut(ActionEvent event) {

    }

    @FXML
    void register(ActionEvent event) {

    }
    @FXML
    private void registerSearchButtonClicked(MouseEvent event) {
        System.out.println("Search button clicked!");
        // thêm xử lý nếu cần
    }
    @FXML
    private void registerActivity(ActionEvent event) {
        System.out.println("Register activity button clicked");
        // Gọi logic đăng ký hoạt động ở đây (nếu cần)
    }

}
