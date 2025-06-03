package screen.student.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Activity;
import model.Admin;
import model.Student;

public class StudentController implements Initializable 
{
	private Student student;
	
	public void setStudent (Student student)
	{
		this.student = student;
	}
	// TODO: chưa có kỳ học
	private Admin admin;
	
	// TODO: nhận tên user từ file json dưới đây là temp
	private String username = new String("Tên của sv ở đây");
	
	// List chứa danh sách các hoạt động của sinh viên
	private ObservableList <Activity> activityListData = FXCollections.observableArrayList();
	
	// Các thuộc tính FXML
	
	@FXML
    private GridPane viewRegistedActivityGridPane;
	
	@FXML
    private Button viewActivityPaneButton;
	
	@FXML
    private Button viewRegisterPaneButton;
	
	@FXML
    private Button viewScorePaneButton;
	
	@FXML
    private AnchorPane registerActivityPane;
	
	@FXML
    private AnchorPane viewScorePane;
	
    @FXML
    private AnchorPane Student_controller;
    
    @FXML
    private Button logout_btn;
    
    @FXML
    private RadioButton viewSemester1;

    @FXML
    private RadioButton viewSemester2;

    @FXML
    private RadioButton viewSemester3;

    @FXML
    private RadioButton viewSemester4;
    
    @FXML
    private TitledPane viewSemesterList;

    @FXML
    private AnchorPane paneThanhDieuHuong;

    @FXML
    private TextField searchTextField;

    @FXML
    private ToggleGroup semesterViewActivity;

    @FXML
    private ToggleGroup semesterViewActivity1;

    @FXML
    private Label studentName;
    
    @FXML
    private AnchorPane viewActivityPane;
    
    @FXML
    private GridPane gridPane;
    
    @FXML
    private ScrollPane viewActivityScrollPane;

    @FXML
    void activityRegisterButtonClick(MouseEvent event) {
    	
    }

    @FXML
    void exitAppButtonClicked(ContextMenuEvent event) {

    }

    @FXML
    void registerPageButtonClicked(MouseEvent event) {

    }

    @FXML
    void registerSearchButtonClicked(MouseEvent event) {

    }

    @FXML
    void viewActivityPageButtonClicked(MouseEvent event) {

    }

    @FXML
    void viewScorePageButtonClicked(MouseEvent event) {

    }
    
    @FXML
    private void cancelActivity(ActionEvent event) 
    {
    	System.out.println("Cancel activity button clicked");

        boolean hasCancel = false;

        /* ① ‒ quét đúng bảng “Đã đăng ký” */
        for (Node node : viewRegistedActivityGridPane.getChildren()) {
            if (node instanceof AnchorPane anchor) {
                Object ud = anchor.getUserData();
                if (ud instanceof ActivityController ac && ac.isSelected()) {

                    Activity activity = ac.getActivity();

                    /* xoá khi đang TỒN TẠI trong danh sách */
                    if (student.getRegisteredActivities().remove(activity)) {
                        hasCancel = true;
                    }
                }
            }
        }

        if (!hasCancel) {
            new Alert(AlertType.INFORMATION, "Bạn chưa chọn hoạt động nào để huỷ!",
                      ButtonType.OK).showAndWait();
            return;
        }

        /* ③ ‒ vẽ lại cả hai bảng để đồng bộ UI */
        displayRegisteredActivity();     // bảng “Đã đăng ký”
        registeredActivityDisplay();     // bảng “Đăng ký”
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
    // Thuộc tính alert này dùng để hiện thông báo thôi :))))
    private Alert alert;
    
    
    // StudentController.java
    public void initData(Student loggedStudent) {
    	this.student = loggedStudent;
        this.username = loggedStudent.getUserName();   // hoặc getter bạn đang dùng
        displayStudentName();                          // cập-nhật nhãn tên sinh viên
        registeredActivityDisplay();                   // nạp dữ liệu đã đăng ký
    }
    // Hàm bên dưới thực hiện việc khởi tạo mỗi khi student đăng nhập
    @Override
    public void initialize (URL location, ResourceBundle resources)
    {
    	displayStudentName();
    	if (student != null) {            // ⬅️ an toàn
            registeredActivityDisplay();
        }

    }
    
    
    
//    // Thực hiện việc trả về danh sách các sự kiện đã đăng ký
//    public ObservableList <Activity> getRegisteredActivityList ()
//    {
//    	ObservableList <Activity> listData = FXCollections.observableArrayList();
//    	// TODO: try-catch dưới đây thực hiện việc nhận dữ liệu của các sự kiện mà sinh viên đã đăng ký
//    	try
//    	{
////    		while(something.next())
////    		{
////    			Activity temp;
////    			temp = new Activity(something.getTitle, something.getName, something.getStatus, something.getScore);
////    			listData.add(temp);
////    		}
//    	}
//    	catch (Exception e)
//    	{
//    		e.printStackTrace();
//    	}
//    	return listData;
//    }
    
    // Hàng và cột trong grid
    int row = 0;
    int column = 0;
    
    // Phương thức này thực hiện việc reset lại datalist và thêm vào mọi activity đã đăng ký vô
    public void registeredActivityDisplay ()
    {
    	gridPane.getChildren().clear();
	    final String ITEM_FXML_FILE_PATH = "/screen/student/view/ActivityLayout.fxml";

	    int column = 0;
	    int row = 1;

	    try {
	    	List<Activity> activities = admin.getAllActivities();
	    	for (Activity activity : activities) {
	    		if (!student.getRegisteredActivities().contains(activity) && student != null) {
		    	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ITEM_FXML_FILE_PATH));
		    	    AnchorPane anchorPane = fxmlLoader.load();
		    	    ActivityController controller = fxmlLoader.getController();
		    	    
					controller.setData(activity, student);
					controller.changeDisplay(1);
					

					anchorPane.setUserData(controller);
		            // Đưa AnchorPane vào grid
		            if (column == 3) 
		            { 
		                column = 0;
		                row++;
		            }


					gridPane.add(anchorPane, column++, row);
		            GridPane.setMargin(anchorPane, new Insets(20, 10, 10, 10));
	    		}

	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }
    
    // TODO: sửa lại đoạn hiển thị chồng nhau
    final String ITEM_FXML = "/screen/student/view/ActivityLayout.fxml";
    public void displayRegisteredActivity ()
    {
    	viewRegistedActivityGridPane.getChildren().clear();
    	int column = 0;
    	int row = 1;

        try
        {
        	
        	for (Activity act : student.getRegisteredActivities()) {
        	    FXMLLoader loader = new FXMLLoader(getClass().getResource(ITEM_FXML));
        	    AnchorPane pane = loader.load();

        	    ActivityController ctrl = loader.getController();
        	    ctrl.setData(act, student);
        	    ctrl.markNotRegistered();
        	    ctrl.changeDisplay(2);

        	    pane.setUserData(ctrl);              // 👈 thêm dòng này

        	    if (column == 3) { column = 0; row++; }
        	    viewRegistedActivityGridPane.add(pane, column++, row);
        	    GridPane.setMargin(pane, new Insets(20,10,10,10));
        	}
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    
    @FXML
    // Phương thức thực hiện việc đổi qua lại giữa các pane
    public void switchForm (ActionEvent event)
    {
    	if (event.getSource() == viewRegisterPaneButton)
    	{
    		
    		registerActivityPane.setVisible(true);
    		viewActivityPane.setVisible(false);
    		viewScorePane.setVisible(false);
    	}
    	else if (event.getSource() == viewActivityPaneButton)
    	{
    		registerActivityPane.setVisible(false);
    		viewActivityPane.setVisible(true);
    		viewScorePane.setVisible(false);
    		displayRegisteredActivity();
    	}
    	else if (event.getSource() == viewScorePaneButton)
    	{
    		registerActivityPane.setVisible(false);
    		viewActivityPane.setVisible(false);
    		viewScorePane.setVisible(true);
    	}
    }
    
    // Cảnh báo người chơi khi thoát chương trình, đồng thời nếu như mà student thoát thì show lại cửa sổ login
    public void logout ()
    {
    	try 
    	{
    		alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Chú ý!!!");
    		alert.setHeaderText(null);
    		alert.setContentText("Bạn có chắc muốn thoát chương trình không ?");
    		Optional<ButtonType> option = alert.showAndWait();
    		
    		if (option.get().equals(ButtonType.OK))
    		{
    			// Thoát hẳn
    			Platform.exit();
    		}
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    // Phương thức để hiển thị tên sv
    public void displayStudentName ()
    {
    	String student_name = username;
    	student_name = student_name.substring(0, 1).toUpperCase() + student_name.substring(1);
    	studentName.setText(student_name);
    }
    


}