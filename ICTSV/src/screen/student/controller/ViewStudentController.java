package screen.student.controller;

import java.io.IOException;
import java.util.List;

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
import model.Activity;
import model.Admin;
import model.Student;

public class ViewStudentController {
    private Student student;
    private Admin admin;
	public ViewStudentController(Student student, Admin admin) {
		this.student = student;
		this.admin = admin;
	}
	@FXML
	public void initialize() {
	    final String ITEM_FXML_FILE_PATH = "/screen/student/view/Activity.fxml";
//        // Hiển thị tên sinh viên lên Label
//        studentName.setText("Chào mừng, " + student.getName());
//
//        // Clear trước khi load
//        gridPane.getChildren().clear();
	    int column = 0;
	    int row = 1;

	    try {
	    	List<Activity> activities = admin.getAllActivities();
	    	for (Activity activity : activities) {
	    	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ITEM_FXML_FILE_PATH));
	    	    ActivityController controller = new ActivityController();
	    	    fxmlLoader.setController(controller);

                
	            AnchorPane anchorPane = new AnchorPane();
	            anchorPane = fxmlLoader.load();

				controller.setData(activity, student);

				anchorPane.setUserData(controller);
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
    	 initialize();
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
    	System.exit(0);
    }

    @FXML
    void register(ActionEvent event) {
        System.out.println("User đang tick checkbox (chưa xử lý đăng ký)");
    }
    @FXML
    private void registerSearchButtonClicked(MouseEvent event) {
        System.out.println("Search button clicked!");
        // thêm xử lý nếu cần
    }
    @FXML
    private void btnSubmitRegister(ActionEvent event) {
        System.out.println("Register activity button clicked");

        for (javafx.scene.Node node : gridPane.getChildren()) {
            if (node instanceof AnchorPane anchorPane) {
                Object ud = anchorPane.getUserData();
                if (ud instanceof ActivityController ac) {
                    if (ac.isSelected()) {
                        Activity activity = ac.getActivity();
                        if (!student.getRegisteredActivities().contains(activity)) {
                            student.addActivity(activity);    // 🟢 Thêm vào danh sách đã đăng ký
                            ac.markAsRegistered();            // ✅ Khóa tick + cập nhật trạng thái
                        }
                    }
                }
            }
        }

        System.out.println("Tổng số hoạt động đã đăng ký: " + student.getRegisteredActivities().size());
    }

}
