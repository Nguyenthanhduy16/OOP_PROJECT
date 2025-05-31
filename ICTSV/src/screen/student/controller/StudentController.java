package screen.student.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
	// TODO: chưa có kỳ học
	private Admin admin;
	
	// TODO: nhận tên user từ file json dưới đây là temp
	private String username = new String("Trịnh Trần Phương Tuấn");
	
	// List chứa danh sách các hoạt động của sinh viên
	private ArrayList<Activity> activityList; 
	
	//
	private ObservableList <Activity> activityListData = FXCollections.observableArrayList();
	
	// Các thuộc tính FXML
	
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
        this.username = loggedStudent.getUserName();   // hoặc getter bạn đang dùng
        displayStudentName();                          // cập-nhật nhãn tên sinh viên
        registeredActivityDisplay();                   // nạp dữ liệu đã đăng ký
    }
    // Hàm bên dưới thực hiện việc khởi tạo mỗi khi student đăng nhập
    @Override
    public void initialize (URL location, ResourceBundle resources)
    {
    	this.student = new Student();
    	displayStudentName();
    	registeredActivityDisplay();

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

					anchorPane.setUserData(controller);
		            // Đưa AnchorPane vào grid
		            if (column == 3) { 
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
    			// Ẩn đi student layout
    			logout_btn.getScene().getWindow().hide();
    			
    			// Gọi lại trang đăng nhập
    			new view.login.main.Main().setVisible(true);
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

