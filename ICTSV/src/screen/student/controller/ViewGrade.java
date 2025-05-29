package screen.student.controller;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import model.Activity;
import model.Admin;
import model.Student;

public class ViewGrade {
	private Admin admin;
	private Student student;
    @FXML	
    private ProgressBar progressYThuc;

    @FXML
    private Label studentName;

    @FXML
    private ToggleGroup semesterViewActivity;

    @FXML
    private ProgressBar progressKyLuat;

    @FXML
    private TextField searchTextField;

    @FXML
    private AnchorPane paneThanhDieuHuong;

    @FXML
    private ToggleGroup semesterViewActivity1;

    @FXML
    private AnchorPane Student_controller;

    @FXML
    private ProgressBar progressHocTap;

    @FXML
    private ProgressBar progressXaHoi;

    @FXML
    private ProgressBar progressTongDiem;

	@FXML
	public void initialize() {
	    final String ITEM_FXML_FILE_PATH = "/screen/student/view/ShowGrade.fxml";
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
	            progressHocTap.setProgress(0);
	            progressKyLuat.setProgress(0);
	            progressXaHoi.setProgress(0);
	            progressYThuc.setProgress(0);
	            progressTongDiem.setProgress(0);
                
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
    public void setStudent(Student student) {
        this.student = student;
        studentName.setText(student.getUserName());

        List<Activity> activities = student.getRegisteredActivities();

        double totalHocTap = 0;
        double totalKyLuat = 0;
        double totalXaHoi = 0;
        double totalYThuc = 0;

        for (Activity a : activities) {
            totalHocTap += a.getHocTap();
            totalKyLuat += a.getKyLuat();
            totalXaHoi += a.getXaHoi();
            totalYThuc += a.getYThuc();
        }

        // Giới hạn tối đa 100 và chuyển sang tỷ lệ cho progressBar (0.0 - 1.0)
        double maxScore = 100.0;
        progressHocTap.setProgress(Math.min(totalHocTap, maxScore) / maxScore);
        progressKyLuat.setProgress(Math.min(totalKyLuat, maxScore) / maxScore);
        progressXaHoi.setProgress(Math.min(totalXaHoi, maxScore) / maxScore);
        progressYThuc.setProgress(Math.min(totalYThuc, maxScore) / maxScore);

        double tong = totalHocTap + totalKyLuat + totalXaHoi + totalYThuc;
        double tongMax = 4 * maxScore;
        progressTongDiem.setProgress(tong / tongMax);
    }
    @FXML
    void registerSearchButtonClicked(ActionEvent event) {

    }

    @FXML
    void showActivity(ActionEvent event) {

    }

    @FXML
    void showGrade(ActionEvent event) {

    }

    @FXML
    void logOut(ActionEvent event) {

    }

    @FXML
    void btnRegisterActivity(ActionEvent event) {

    }

}
