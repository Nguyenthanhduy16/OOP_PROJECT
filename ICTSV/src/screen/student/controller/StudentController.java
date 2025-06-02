package screen.student.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
    	khoiTaoDuLieu();
    	displayScoreCharts();
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
    // Ve bieu do 1
    @FXML
    private void handleSemesterSelection(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        String selectedSemester = selectedItem.getText(); // Ví dụ: "2023.1"
        semesterMenuButton.setText(selectedSemester);     // Cập nhật nút

        updateBarChart(selectedSemester);                 // Cập nhật biểu đồ
    }
    @FXML
    private BarChart<String, Number> barChart; // ID của bar chart trong FXML
    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    private MenuButton semesterMenuButton;
    @FXML
    private AnchorPane chartSumContainer;
    @FXML
    private AnchorPane chartSemesterContainer;
    private final List<String> semesterOrder = List.of(
    	    "Kì 2023.1",
    	    "Kì 2023.2",
    	    "Kì 2024.1",
    	    "Kì 2024.2"
    	);
    private final Map<String, Map<String, Integer>> scoreDataBySemester = new HashMap<>();
    public void displayScoreCharts() {
        lineChart.getData().clear();
        lineChart.setTitle("Điểm rèn luyện các học kì");
        XYChart.Series<String, Number> lineSeries = new XYChart.Series<>();
        lineSeries.setName("Tổng điểm");
        // Giới hạn trục Y
        NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();
        yAxis.setAutoRanging(true);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(100);
        yAxis.setTickUnit(10);
        
        // Đảm bảo trục X hiển thị đúng thứ tự và đủ các kỳ
        CategoryAxis xAxis = (CategoryAxis) lineChart.getXAxis();
        xAxis.setCategories(FXCollections.observableArrayList(semesterOrder));
        
        
        for (String semester : semesterOrder) {
            Map<String, Integer> criteria = scoreDataBySemester.get(semester);
            int total = 0;
            if (criteria != null) {
                total = criteria.values().stream().mapToInt(Integer::intValue).sum();
            }
            lineSeries.getData().add(new XYChart.Data<>(semester, total));
        }

        lineChart.getData().add(lineSeries);

        chartSumContainer.getChildren().clear();
        chartSumContainer.getChildren().add(lineChart);
        AnchorPane.setTopAnchor(lineChart, 70.0);
        AnchorPane.setLeftAnchor(lineChart, 10.0);
        AnchorPane.setRightAnchor(lineChart, 10.0); 

        // Hiển thị biểu đồ bar cho kỳ đầu tiên
        updateBarChart(semesterMenuButton.getText());
    }

    private void updateBarChart(String selectedSemester) {
        barChart.getData().clear();
        barChart.setTitle("Biểu đồ điểm rèn luyện");

        XYChart.Series<String, Number> barSeries = new XYChart.Series<>();
        barSeries.setName("Điểm tiêu chí");

        Map<String, Integer> criteria = scoreDataBySemester.get(selectedSemester);
        if (criteria != null) {
            for (Map.Entry<String, Integer> entry : criteria.entrySet()) {
                XYChart.Data<String, Number> data = new XYChart.Data<>(entry.getKey(), entry.getValue());
                barSeries.getData().add(data);
            }
        }

        barChart.getData().add(barSeries);

        chartSemesterContainer.getChildren().clear();
        chartSemesterContainer.getChildren().add(barChart);
        AnchorPane.setTopAnchor(barChart, 10.0);
        AnchorPane.setLeftAnchor(barChart, 150.0);
        AnchorPane.setRightAnchor(barChart, 150.0);

        // Gán màu cho từng cột sau khi hiển thị
        Platform.runLater(() -> {
            for (int i = 0; i < barSeries.getData().size(); i++) {
                XYChart.Data<String, Number> data = barSeries.getData().get(i);
                final int colorIndex = i % 4;
                if (data.getNode() != null) {
                    data.getNode().setStyle("-fx-bar-fill: " + getColorNameForIndex(colorIndex) + ";");
                }
            }
        });
    }

    private String getColorNameForIndex(int index) {
        String[] colors = {"#FF6F61", "#6B8E23", "#4682B4", "#FFA07A"};
        return colors[index % colors.length];
    }


    // Hàm bên dưới thực hiện việc khởi tạo mỗi khi student đăng nhập
    @Override
    public void initialize (URL location, ResourceBundle resources)
    {
    	this.student = new Student();
    	displayStudentName();
    	registeredActivityDisplay();


        registerActivityPane.setVisible(true);
        viewActivityPane.setVisible(false);
        viewScorePane.setVisible(false);
    }    
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
    public void switchForm(ActionEvent event) {
        if (event.getSource() == viewRegisterPaneButton) {
            registerActivityPane.setVisible(true);
            viewActivityPane.setVisible(false);
            viewScorePane.setVisible(false);
        } else if (event.getSource() == viewActivityPaneButton) {
            registerActivityPane.setVisible(false);
            viewActivityPane.setVisible(true);
            viewScorePane.setVisible(false);
        } else if (event.getSource() == viewScorePaneButton) {
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
    private void khoiTaoDuLieu() {
        // Dữ liệu điểm các tiêu chí theo học kỳ
        Map<String, Integer> semester1 = Map.of(
            "Học tập", 10,
            "Tổ chức kỷ luật", 25,
            "Chính trị, văn hóa", 30,
            "Ý thức", 25
        );
        Map<String, Integer> semester2 = Map.of(
            "Học tập", 20,
            "Tổ chức kỷ luật", 25,
            "Chính trị, văn hóa", 25,
            "Ý thức", 25
        );
        Map<String, Integer> semester3 = Map.of(
            "Học tập", 15,
            "Tổ chức kỷ luật", 10,
            "Chính trị, văn hóa", 15,
            "Ý thức", 25
        );
        Map<String, Integer> semester4 = Map.of(
            "Học tập", 20,
            "Tổ chức kỷ luật", 5,
            "Chính trị, văn hóa", 15,
            "Ý thức", 15
        );
        scoreDataBySemester.put("Kì 2023.1", semester1);
        scoreDataBySemester.put("Kì 2023.2", semester2);
        scoreDataBySemester.put("Kì 2024.2", semester4);
        scoreDataBySemester.put("Kì 2024.1", semester3);

        // Gán tên mặc định cho menu
        semesterMenuButton.setText("Kì 2023.1");
    }


}

