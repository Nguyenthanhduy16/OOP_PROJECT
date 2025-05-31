package screen.student.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import model.Activity;
import model.Student;

public class ActivityController {
    private Activity activity;
    private Student student;
    
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
    
    private boolean registered = false;

    @FXML
    void btnAddToListClicked(ActionEvent event) {
        // Đánh dấu hoặc bỏ đánh dấu hoạt động để thêm vào danh sách
        if (registered) {
            // Nếu đã đăng ký, không cho thay đổi checkbox
            btnAddToList.setSelected(true); // Giữ tick
            return;
        }
        if (btnAddToList.isSelected()) {
            System.out.println("Added: " + activity.getName());
        } else {
            System.out.println("Removed: " + activity.getName());
        }
    }

	public void setData(Activity activities, Student student) {
		// TODO Auto-generated method stub
	    this.activity = activities;
	    this.student = student;
//	    // Đặt thông tin lên UI, ví dụ:
//	    lblName.setText(activity.getName());
        labelName.setText(activities.getName());
        labelLocation.setText(activities.getLocation());
        labelDate.setText(activities.getDate().toString());  // Giả sử activity.getDate() trả về java.time.LocalDate
        labelTieuChi.setText(activities.getTitle());        // Ví dụ tiêu chí đánh giá
        
        labelGrade.setText(String.valueOf(((Activity) activities).getScore()));
        if (student != null && student.getRegisteredActivities().contains(activity)) {
            markAsRegistered();
        }else {
            btnAddToList.setSelected(false);  // Nếu chưa đăng ký -> để checkbox trống
            btnAddToList.setDisable(false);   // Cho phép tick vào
        }
	}
	/** Trả về xem checkbox có được tick hay không */
    public boolean isSelected() {
        return btnAddToList.isSelected() && !registered;
    }
    /** Lấy về đối tượng Activity gốc */
    public Activity getActivity() {
        return activity;
    }

    public void markAsRegistered() {
        registered = true;
        btnAddToList.setSelected(true);
        btnAddToList.setDisable(true);  // Không cho chỉnh checkbox nữa
    }

    public void allowSelection() {
    	btnAddToList.setDisable(false); // Cho tick
    	btnAddToList.setSelected(false); // Bỏ tick nếu có
    }
}
