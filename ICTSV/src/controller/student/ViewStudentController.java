package controller.student;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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
	    final String ITEM_FXML_FILE_PATH = "/view/Activity.fxml";

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
    private GridPane gridPane;

    @FXML
    void btnRegisterActivity(ActionEvent event) {

    }

    @FXML
    void showRegistedActivity(ActionEvent event) {

    }

    @FXML
    void showGrade(ActionEvent event) {

    }

    @FXML
    void out(ActionEvent event) {

    }

    @FXML
    void register(ActionEvent event) {

    }

}
