package screen.student.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import model.Activity;

public class ActivityController {
    private Activity activity;
    @FXML
    private CheckBox btnAddToList;

    @FXML
    private Label labelTieuChi;

    @FXML
    private Label labelGrade;

    @FXML
    private Label labelName;

    @FXML
    private Label labelLocation;

    @FXML
    private Label labelDate;

    @FXML
    void btnAddToListClicked(ActionEvent event) {
        // Đánh dấu hoặc bỏ đánh dấu hoạt động để thêm vào danh sách
        if (btnAddToList.isSelected()) {
            System.out.println("Added: " + activity.getName());
        } else {
            System.out.println("Removed: " + activity.getName());
        }
    }

	public void setData(Activity activity) {
		// TODO Auto-generated method stub
        this.activity = activity;
        labelName.setText(activity.getName());
        labelLocation.setText(activity.getLocation());
        labelDate.setText(activity.getDate().toString());  // Giả sử activity.getDate() trả về java.time.LocalDate
        labelTieuChi.setText(activity.getTitle());        // Ví dụ tiêu chí đánh giá
        labelGrade.setText(String.valueOf(activity.getScore()));
	}

}
