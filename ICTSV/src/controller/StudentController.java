package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import entity.Activity;
import entity.Admin;
import entity.Student;
import handle.entity.UserHandle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

public class StudentController implements Initializable 
{
	private Student student;
	public void setStudent (Student student)
	{
		this.student = student;
	    //lấy danh sách hoạt động từ đúng userID của student này
	    List<Activity> registered = UserHandle.getActivitiesByStudentId(student.getUserID());
	    if (registered != null) {
	        student.setRegisteredActivitiesJson(registered);
	    }
	}
	private Admin admin;
	public void setAdmin(Admin admin) { this.admin = admin; }
	
	// TODO: nhận tên user từ file json dưới đây là temp
	private String username = new String("Tên của sv ở đây");
	
	// List chứa danh sách các hoạt động của sinh viên
	private ObservableList <Activity> activityListData = FXCollections.observableArrayList();
	
	// Các thuộc tính FXML
	
	@FXML
    private AnchorPane editAvatarPane;

	@FXML
    private TextField registeredActivitySearchText;
	
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

    
    // Tìm kiếm các hoạt động đã đăng ký
    @FXML
    private TextField searchTextField;
    private List<Activity> allActivities;

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
    
    
    // Ảnh đại diện
    @FXML
    private ImageView avatar;
    
    @FXML
    private Button acceptPicture;
    
    @FXML
    private ImageView currentAvatar;

	@FXML
    private Button editAvatarButton;
	
	@FXML
    private Button insertPicture;
	
	@FXML
    private Button removeStudentButton;
	
	@FXML
    private RadioButton viewSemesterAll;
	
    @FXML
    private Button searchButton;
    
    @FXML
    private TextField searchField;
    
    @FXML
    void activityRegisterButtonClick(MouseEvent event) {
    	
    }

    @FXML
    void exitAppButtonClicked(ContextMenuEvent event) {

    }

    @FXML
    void registerPageButtonClicked(MouseEvent event) {
        // Nạp lại toàn bộ hoạt động mới nhất từ nguồn (file JSON, DB,...)
        allActivities = handle.entity.ActivityHandle.loadActivities();

        // Hiển thị lại các hoạt động
        registeredActivityDisplay(allActivities);
    }

    @FXML
    void viewActivityPageButtonClicked(MouseEvent event) {
    	
    }
    
    @FXML
    void registerSearchButtonClicked(MouseEvent event) {
    	String keyword = registeredActivitySearchText.getText().trim();

        if (keyword.isBlank()) {
            displayRegisteredActivity();      // Hiển thị toàn bộ hoạt động nếu ô trống
        } else {
            searchRegisteredActivity(keyword);
        }
    }

    @FXML
    void viewScorePageButtonClicked(MouseEvent event) {
    	khoiTaoDuLieu();
    	displayScoreCharts();
    }
    
    @FXML
    private void cancelActivity(ActionEvent event) 
    {
    	System.out.println("Cancel activity button clicked");
        boolean hasCancel = false;

        // quét đúng bảng “Đã đăng ký” 
        for (Node node : viewRegistedActivityGridPane.getChildren()) {
            if (node instanceof AnchorPane anchor) {
                Object ud = anchor.getUserData();
                if (ud instanceof ActivityController ac && ac.isSelected()) {

                    Activity activity = ac.getActivity();

       // xoá nếu đã tồn tại trong danh sách 
                    if (student.getRegisteredActivities().remove(activity)) {
                    	handle.entity.UserHandle.removeActivityFromStudent(student.getUserID(), activity.getName());
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

        // vẽ lại cả hai bảng để đồng bộ UI 
        displayRegisteredActivity();     			  // bảng “Đã đăng ký”
        registeredActivityDisplay(allActivities);     // bảng “Đăng ký”
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
                            student.addActivity(activity);               // Cập nhật trên RAM/UI
                            UserHandle.addActivityToStudent(student.getUserID(), activity);   //  Ghi vào file
                            ac.markAsRegistered();
                        }
                    }
                }
            }
        }

        System.out.println("Tổng số hoạt động đã đăng ký: " + student.getRegisteredActivities().size());
    }
    // Thuộc tính alert dùng để hiện thông báo 
    private Alert alert;
    
    private String currentSemes = "2024.2";
    // Thực hiện việc sort các activity theo kỳ học khi chọn kỳ học trong phần xem các hoạt động đã đăng ký
    public void switchSemesterInViewRegisteredAct (ActionEvent e)
    {
    	RadioButton rbutton = (RadioButton) e.getSource();
    	if (rbutton == viewSemesterAll || "Tất cả".equalsIgnoreCase(rbutton.getText().trim())) 
    	{
    	        redraw(student.getRegisteredActivities());
    	        return;
    	}

    	String semesterChosen = rbutton.getText().trim();
    	List<Activity> filtered = student.getRegisteredActivities().stream().filter(a -> semesterChosen.equalsIgnoreCase(a.getSemester())).collect(Collectors.toList());
    	redraw(filtered);
    }
    
    // Phương thức redraw vẽ lại các hoạt động filter theo kỳ
    private void redraw (List <Activity> list)
    {
    	// Loại bỏ hiện tại
    	viewRegistedActivityGridPane.getChildren().clear();
    	final String ITEM_FXML = "/view/ActivityLayout.fxml";
    	int column = 0, row = 1;
    	
    	try 
    	{
            for (Activity act : list) 
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ITEM_FXML));
                AnchorPane pane  = loader.load();

                ActivityController ctrl = loader.getController();
                ctrl.setData(act, student);
                ctrl.markNotRegistered();      // nút “Huỷ đăng ký”
                ctrl.changeDisplay(2);

                pane.setUserData(ctrl);

                if (column == 3) { column = 0; row++; }
                viewRegistedActivityGridPane.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(20, 10, 10, 10));
            }
        } 
    	catch (IOException ex) 
    	{
            ex.printStackTrace();
        }
    }
    
    // StudentController.java
    public void initData(Student loggedStudent) {
    	this.student = loggedStudent;
        this.username = loggedStudent.getUserName();
        
        // Ảnh đại diện
        avatar.setImage(loadAvatar(student.getUserName()));
    	double SIZE = 150;
    	avatar.setFitWidth(SIZE);
    	avatar.setFitHeight(SIZE);
    	avatar.setPreserveRatio(false);
        double r = 75;
        Circle clip = new Circle(r, r, r);
        avatar.setClip(clip);
        
        System.out.println("Admin in controller: " + admin);
        System.out.println("Activities in controller: " + (admin != null ? admin.getAllActivities().size() : "admin is null"));
        
        displayStudentName();          
        allActivities = handle.entity.ActivityHandle.loadActivities();// cập-nhật nhãn tên sinh viên
        if (student == null) {
            System.out.println("Student is null in StudentController");
        } else {
            registeredActivityDisplay(allActivities);
            searchButton.setOnAction(event -> {
                String searchText = searchField.getText();
                searchActivities(searchText);
            });
        }
    }
    
    // Tìm kiếm các activities trong trang đăng ký hoạt động
    public void searchActivities(String searchText) {
        // Lọc theo tên hoạt động, học kỳ, tiêu chí (title), có thể mở rộng thêm
        List<Activity> filtered = allActivities.stream()
            .filter(a -> 
                (a.getTitle() != null && a.getTitle().toLowerCase().contains(searchText.toLowerCase())) ||
                (a.getSemester() != null && a.getSemester().toLowerCase().contains(searchText.toLowerCase())) ||
                (a.getName() != null && a.getName().toLowerCase().contains(searchText.toLowerCase()))
            )
            .collect(Collectors.toList());
        
        // Xóa các node hiện tại khỏi gridPane
        gridPane.getChildren().clear();
        // Hiển thị lại các activity đã lọc
        registeredActivityDisplay(filtered);
    }
    
 // Ve bieu do 1
    @FXML
    private void handleSemesterSelection(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        String selectedSemester = selectedItem.getText(); // Ví dụ: "2023.1"
        semesterMenuButton.setText(selectedSemester);     // Cập nhật nút
        
        updateBarChart(selectedSemester);                 // Cập nhật biểu đồ
    }
    
    // TODO: bổ xung mấy cái bên dưới
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
    
    // Hàm bên dưới thực hiện việc khởi tạo mỗi khi student đăng nhập
    @Override
    public void initialize (URL location, ResourceBundle resources)
    {
    	displayStudentName();
    	// Chỉ thực hiện setup giao diện tĩnh, không phụ thuộc dữ liệu
        registerActivityPane.setVisible(true);
        viewActivityPane.setVisible(false);
        viewScorePane.setVisible(false);

    }
    
    // Danh sách các kỳ học
    private final List<String> semesterOrder = List.of(
    	    "2023.1",
    	    "2023.2",
    	    "2024.1",
    	    "2024.2"
    	);
    
    // TODO: Cập nhật dữ liệu và hiển thị lên biểu đồ
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
                total = Math.min(100, criteria.values().stream().mapToInt(Integer::intValue).sum());
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
        
        // Danh sách tiêu chí cố định
        String[] allCriteria = {"Học tập",  "Kỷ luật", "Ý thức",  "Xã hội"};
        // Map điểm tối đa cho từng tiêu chí
        Map<String, Integer> maxScores = new HashMap<>();
        maxScores.put("Ý thức", 20);
        maxScores.put("Kỷ luật", 25);
        maxScores.put("Học tập", 30);
        maxScores.put("Xã hội", 25);
        Map<String, Integer> criteria = scoreDataBySemester.get(selectedSemester);
        for (int i = 0; i < allCriteria.length; i++) {
            String criterion = allCriteria[i];
            int value = (criteria != null && criteria.containsKey(criterion)) ? criteria.get(criterion) : 0;
            int maxValue = maxScores.getOrDefault(criterion, 100); // fallback max = 100 nếu không có
            // Giới hạn điểm hiển thị
            int displayedValue = Math.min(value, maxValue);
            XYChart.Data<String, Number> data = new XYChart.Data<>(criterion, displayedValue);
            barSeries.getData().add(data);
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
    
    // Hàng và cột trong grid
    int row = 0;
    int column = 0;
    
    // Phương thức này thực hiện việc reset lại datalist và thêm vào mọi activity đã đăng ký vô
    public void registeredActivityDisplay(List<Activity> activities) {
        final String ITEM_FXML_FILE_PATH = "/view/ActivityLayout.fxml";
        
        gridPane.getChildren().clear();
        
        int column = 0;
        int row    = 1;

        try {
            for (Activity activity : activities) {

                /* Bỏ điều kiện loại trừ: hiển thị cả những cái đã đăng ký  */
                // if (!student.getRegisteredActivities().contains(activity))

                FXMLLoader fxmlLoader = new FXMLLoader(
                        getClass().getResource(ITEM_FXML_FILE_PATH));
                AnchorPane anchorPane = fxmlLoader.load();

                ActivityController controller = fxmlLoader.getController();
                controller.setData(activity, student);   // phương thức này đã tự tick
                                                         // + disable nếu SV đã đăng ký

                anchorPane.setUserData(controller);

                if (column == 3) { column = 0; row++; }
                gridPane.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(20, 10, 10, 10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
    // Hiển thị các hoạt động sinh viên đã đăng ký
    final String ITEM_FXML = "/view/ActivityLayout.fxml";
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

        	    pane.setUserData(ctrl);              //  thêm dòng này

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
    
    // Phương thức thực hiện việc tìm kiếm các hoạt động đã đăng ký 
    public void searchRegisteredActivity (String text)
    {
        viewRegistedActivityGridPane.getChildren().clear();
        int column = 0;
        int row = 1;

        try 
        {
            for (Activity act : student.getRegisteredActivities()) 
            {
                // So sánh từ khóa với tiêu đề hoạt động (có thể mở rộng thêm nếu muốn)
                if ((act.getTitle() != null && act.getTitle().contains(text)) ||
                	(act.getName() != null && act.getName().contains(text)) ||
                	(act.getTitle() != null && act.getTitle().contains(text))) 
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(ITEM_FXML));
                    AnchorPane pane = loader.load();

                    ActivityController ctrl = loader.getController();
                    ctrl.setData(act, student);
                    ctrl.markNotRegistered();
                    ctrl.changeDisplay(2);

                    pane.setUserData(ctrl);  // Gán controller để sau này còn dùng
                    if (column == 3) 
                    {
                        column = 0;
                        row++;
                    }

                    viewRegistedActivityGridPane.add(pane, column++, row);
                    GridPane.setMargin(pane, new Insets(20, 10, 10, 10));
                }
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    // Làm việc với avatar
    private static final Path AVATAR_DIR = Paths.get("src", "avatar");

    private Image loadAvatar(String userName) {
        Path personal = AVATAR_DIR.resolve(userName + ".jpg");
        if (Files.exists(personal)) 
        {
            return new Image(personal.toUri().toString());
        }

        Path defFile = AVATAR_DIR.resolve("default.jpg");
        if (Files.exists(defFile)) 
        {
            return new Image(defFile.toUri().toString());
        }
        
        // Ảnh null phòng khi ảnh default bị lỗi
        return new Image("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAA1JREFUGFdj+P///38ACfsD/Q1E8HAAAAAASUVORK5CYII=");
    }
    
    
    private File selectedAvatarFile;
    // Phương thức thực hiện việc nhận ảnh
    @FXML
    void changeAvatar(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Chọn ảnh đại diện đi người anh em");
    	fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
    	
    	File file = fileChooser.showOpenDialog(insertPicture.getScene().getWindow());
    	if (file != null)
    	{
    		// Hiện tạm thời
    		selectedAvatarFile = file;
    		Image tempImage = new Image(file.toURI().toString());
            currentAvatar.setImage(tempImage);
    	}
    }
    
    // Xác nhận thay avatar
    @FXML
    void confirmChange(ActionEvent event) {
    	if (selectedAvatarFile != null)
    	{
    		try
    		{
    			Files.createDirectories(AVATAR_DIR);
    			Path dest = AVATAR_DIR.resolve(student.getUserName() + ".jpg");
    			Files.copy(selectedAvatarFile.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
    			Image image = new Image(dest.toUri().toString());
                avatar.setImage(image);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Đã thay ảnh đại diện thành công!");
                alert.showAndWait();
    		}
    		catch (IOException e)
    		{
    			e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Không thể lưu ảnh!", ButtonType.OK);
                alert.showAndWait();
    		}
    	}
    }
    
    
    @FXML
    // Phương thức thực hiện việc đổi qua lại giữa các pane
    public void switchForm (ActionEvent event)
    {
    	// Xem trang đăng ký hoạt động
    	if (event.getSource() == viewRegisterPaneButton)
    	{
    		registerActivityPane.setVisible(true);
    		viewActivityPane.setVisible(false);
    		viewScorePane.setVisible(false);
    		editAvatarPane.setVisible(false);
    	}
    	// Xem trang các hoạt động đã đăng ký
    	else if (event.getSource() == viewActivityPaneButton)
    	{
    		registerActivityPane.setVisible(false);
    		viewActivityPane.setVisible(true);
    		viewScorePane.setVisible(false);
    		editAvatarPane.setVisible(false);
    		displayRegisteredActivity();
    	}
    	// Xem trang điểm
    	else if (event.getSource() == viewScorePaneButton)
    	{
    		registerActivityPane.setVisible(false);
    		viewActivityPane.setVisible(false);
    		editAvatarPane.setVisible(false);
    		viewScorePane.setVisible(true);
    	}
    	// Xem ảnh đại diện
    	else if (event.getSource() == editAvatarButton)
    	{
    		// Load ảnh hiện tại
    		currentAvatar.setImage(avatar.getImage());
        	double SIZE = 400;
        	currentAvatar.setFitWidth(SIZE);
        	currentAvatar.setFitHeight(SIZE);
        	currentAvatar.setPreserveRatio(false);
            Rectangle clip = new Rectangle(SIZE, SIZE);
            currentAvatar.setClip(clip);
            
            
    		registerActivityPane.setVisible(false);
    		viewActivityPane.setVisible(false);
    		viewScorePane.setVisible(false);
    		editAvatarPane.setVisible(true);
    	}
    }
    
    // Cảnh báo người dùng khi thoát chương trình, đồng thời nếu như mà student thoát thì thoát ra or show lại cửa sổ login 
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
    
    
    private void khoiTaoDuLieu() {
        scoreDataBySemester.clear(); // Dọn dẹp dữ liệu cũ

        List<Activity> activities = student.getRegisteredActivities();

        for (Activity activity : activities) {
            String semester = activity.getSemester();
            String criterion = activity.getTitle(); // Giả sử tiêu chí như "Ý thức", "Học tập" nằm ở title
            int score = activity.getScore();

            // Nếu chưa có học kỳ này thì khởi tạo
            scoreDataBySemester.putIfAbsent(semester, new HashMap<>());

            Map<String, Integer> criteriaMap = scoreDataBySemester.get(semester);
            // Cộng dồn điểm cho tiêu chí
            criteriaMap.put(criterion, criteriaMap.getOrDefault(criterion, 0) + score);
        }

        // Gán mặc định học kỳ đầu tiên (nếu có)
        if (!scoreDataBySemester.isEmpty()) {
            String firstSemester = scoreDataBySemester.keySet().iterator().next();
            semesterMenuButton.setText(firstSemester);
            updateBarChart(firstSemester); // Tự động hiển thị biểu đồ 
        }
    }


}