package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import model.Activity;
import model.Student;

public class StudentController implements Initializable 
{
	// TODO: chưa có kỳ học
	
	
	// TODO: nhận tên user từ file json dưới đây là temp
	//Lấy tên và dữ liệu từ file json
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
    private GridPane viewRegistedActivityGridPane;
    
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
    
    // Thuộc tính alert này dùng để hiện thông báo thôi :))))
    private Alert alert;
    
    
    // StudentController.java
    public void initData(Student loggedStudent) {
        this.username = loggedStudent.getUserName();   // hoặc getter bạn đang dùng
        displayStudentName();                          // cập-nhật nhãn tên sinh viên
        registeredActivityDisplay();                   // nạp dữ liệu đã đăng ký
    }

    // Thực hiện việc trả về danh sách các sự kiện đã đăng ký
    public ObservableList <Activity> getRegisteredActivityList ()
    {
    	ObservableList <Activity> listData = FXCollections.observableArrayList();
    	// TODO: try-catch dưới đây thực hiện việc nhận dữ liệu của các sự kiện mà sinh viên đã đăng ký
    	try
    	{
//    		while(something.next())
//    		{
//    			Activity temp;
//    			temp = new Activity(something.getTitle, something.getName, something.getStatus, something.getScore);
//    			listData.add(temp);
//    		}
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	return listData;
    }
    
    // Hàng và cột trong grid
    int row = 0;
    int column = 0;
    
    // Phương thức này thực hiện việc reset lại datalist và thêm vào mọi activity đã đăng ký vô
    public void registeredActivityDisplay ()
    {
    	activityListData.clear();
    	activityListData.addAll(getRegisteredActivityList());
    	
    	// Clear bỏ đi các chỉ số row và column trước đó
    	viewRegistedActivityGridPane.getRowConstraints().clear();
    	viewRegistedActivityGridPane.getColumnConstraints().clear();
    	// Lặp qua từng đối tượng trong list và thêm nó vào activity controller (FXML)
    	for (int i = 0; i < activityListData.size(); i ++)
    	{
    		try
    		{
    			// Cái FXMLLoadder này giống kiểu scanner, dùng nó để load các thứ thuộc về FXML
    			FXMLLoader load = new FXMLLoader();
    			load.setLocation(getClass().getResource("Activity.fxml"));;
        		AnchorPane pane = load.load();
        		StudentRegisteredActivityController actController = load.getController();
        		actController.setData(activityListData.get(i));
        		
        		if (column == 3)
        		{
        			column = 0;
        			row += 1;
        		}
        		
        		// Thêm vào grid pane
        		viewRegistedActivityGridPane.add(pane, column++, row);
        		
    		}
    		catch (Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    }
    
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
    			//new view.login.main.Main().setVisible(true);
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
    
    // Hàm bên dưới thực hiện việc khởi tạo mỗi khi student đăng nhập
    @Override
    public void initialize (URL location, ResourceBundle resources)
    {
    	displayStudentName();
    	registeredActivityDisplay();
    }

   /* @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/StudentLayout.fxml"));
        Scene scene = new Scene(root);
        
        stage.setTitle("Student Management Page");
        stage.setMinWidth(1600);
        stage.setMinHeight(1000);
        stage.setScene(scene);
        stage.show();
    }*/
    
}