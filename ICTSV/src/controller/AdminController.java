package controller;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import entity.Admin;
import entity.Student;
import entity.Activity;

import java.util.Optional;
import java.util.Comparator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AdminController {
    // FXML injected fields
    // AnchorPanes
    @FXML
    private AnchorPane paneFullPane;
    @FXML
    private AnchorPane paneStudentManage;
    @FXML
    private AnchorPane paneViewActivity;
    @FXML
    private AnchorPane paneAddNewAct;
    @FXML
    private AnchorPane paneStudentList;
    @FXML
    private AnchorPane paneStudentDetail;
    @FXML
    private AnchorPane ActPane;
    @FXML
    private AnchorPane SearchActivities;
    @FXML
    private AnchorPane panStuNActList;
    @FXML
    private GridPane studentGridPane;
    
    @FXML
    private Button backToViewStudentList;

    // Labels
    @FXML
    private Label textUserName;
    @FXML
    private Label textID;
    @FXML
    private Label textRole;
    @FXML
    private Label detailNameLabel;
    @FXML
    private Label detailIDLabel;
    @FXML
    private Label detailTotalActivityLabel;

    // Buttons
    @FXML
    private Button buttonViewActivity;
    @FXML
    private Button buttonViewStudent;
    @FXML
    private Button buttonViewStuList;
    @FXML
    private Button buttonLogout;
    @FXML
    private Button AddActButton;
    @FXML
    private Button RemoveActButton;
    @FXML
    private Button BtnAddActToStu;
    @FXML
    private Button btnRemoveActToStu;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnSearch1;
    @FXML
    private Button btnSearch2;

    // TextFields
    @FXML
    private TextField NewActName;
    @FXML
    private TextField NewActScore;
    @FXML
    private TextField NewActTime;
    @FXML
    private TextField NewActPlace;
    @FXML
    private TextField NewActSemester;
    @FXML
    private TextField SearchAct;
    @FXML
    private TextField Search2;
    @FXML
    private TextField ActivitiesPlace;
    @FXML
    private TextField ActivitiesTime;

    // ComboBoxes
    @FXML
    private ComboBox<String> ActCategorySelected;
    @FXML
    private ComboBox<String> ActivitiesCategorySelected;
    @FXML
    private ComboBox<String> ActSemesterSelected;
    @FXML
    private ComboBox<String> NewActSemesterCombo;

    // TableViews
    @FXML
    private TableView<Activities> ActivitiesTable;
    @FXML
    private TableView<Activities> ActivitiesTable1;

    // Activity TableColumns
    @FXML
    private TableColumn<Activities, String> ActivitiesName;
    @FXML
    private TableColumn<Activities, String> ActivitiesCategory;
    @FXML
    private TableColumn<Activities, String> ActPlace;
    @FXML
    private TableColumn<Activities, String> ActScore;
    @FXML
    private TableColumn<Activities, String> ActTime;
    @FXML
    private TableColumn<Activities, Boolean> ActSelected;
    @FXML
    private TableColumn<Activities, Boolean> colSelectAct;
    @FXML
    private TableColumn<Activities, String> ActivitiesName1;
    @FXML
    private TableColumn<Activities, String> ActivitiesCategory1;
    @FXML
    private TableColumn<Activities, String> ActPlace1;
    @FXML
    private TableColumn<Activities, String> ActScore1;
    @FXML
    private TableColumn<Activities, String> ActTime1;
    
    @FXML
    private TextField genAccountID;

    @FXML
    private TextField genAccountPassWord;

    @FXML
    private TextField genAccountUserName;
    
    @FXML
    private Button createAccountButton;
    
    @FXML
    private ImageView studentImg;

    // ScrollPanes
    @FXML
    private ScrollPane ActivitiesScrollPan;
    
    // Thêm sinh viên
    @FXML
    private Button addStudentButton;
    
    @FXML
    private AnchorPane paneAddStudent;
    
    // Xóa sinh viên
    private entity.Student currentStudent;
    @FXML
    void removeCurrentStudent(ActionEvent event) {
    	// Nếu không thể xóa được
    	if (currentStudent == null)
    	{
    		Alert noti = new Alert(Alert.AlertType.WARNING);
    		noti.setTitle("Không thể xóa");
    		noti.setHeaderText(null);
    		noti.setContentText("Không thể xóa được do sinh viên đang null");
    		noti.show();
    		return;
    	}
    	
    	Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
    	confirm.setTitle("Xác nhận xóa ?");
    	confirm.setHeaderText(null);
    	confirm.setContentText("Bạn chắc muốn xóa sinh viên " + currentStudent.getUserName() + " MSSV: " + currentStudent.getUserID() + " ?");
    	if (confirm.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) return;
    	
    	boolean ok = handle.entity.UserHandle.removeUser(currentStudent.getUserID());
    	if (ok)
    	{
    		// Xóa avatar
    		java.nio.file.Path av = java.nio.file.Paths.get("src","avatar", currentStudent.getUserName() + ".jpg");
    		try 
    		{ 
    			java.nio.file.Files.deleteIfExists(av); 
    		} 
    		catch (Exception ignored) 
    		{
    			
    		}

            showAlert(Alert.AlertType.INFORMATION, "Đã xoá", "Đã xoá sinh viên thành công!");
            paneStudentDetail.setVisible(false);
            paneStudentList.setVisible(true);
            displayStudentList();
            currentStudent = null;
    	}
    	else 
    	{
            showAlert(Alert.AlertType.ERROR, "Lỗi",
                      "Không tìm thấy sinh viên trong dữ liệu!");
        }
    }


    // Data models

    public static class Activities {
        private final StringProperty name;
        private final StringProperty category;
        private final StringProperty place;
        private final StringProperty score;
        private final StringProperty time;
        private final StringProperty semester;
        private final BooleanProperty selected;

        public Activities(String name, String category, String place, String score, String time, String semester) {
            this.name = new SimpleStringProperty(name);
            this.category = new SimpleStringProperty(category);
            this.place = new SimpleStringProperty(place);
            this.score = new SimpleStringProperty(score);
            this.time = new SimpleStringProperty(time);
            this.semester = new SimpleStringProperty(semester);
            this.selected = new SimpleBooleanProperty(false);
        }
        
        public String getName() {
            return name.get();
        }

        public StringProperty nameProperty() {
            return name;
        }

        public String getCategory() {
            return category.get();
        }

        public StringProperty categoryProperty() {
            return category;
        }

        public String getPlace() {
            return place.get();
        }

        public StringProperty placeProperty() {
            return place;
        }

        public String getScore() {
            return score.get();
        }

        public StringProperty scoreProperty() {
            return score;
        }

        public String getTime() {
            return time.get();
        }

        public StringProperty timeProperty() {
            return time;
        }

        public String getSemester() {
            return semester.get();
        }

//        public StringProperty semesterProperty() {
//            return semester;
//        }

        public boolean isSelected() {
            return selected.get();
        }

        public BooleanProperty selectedProperty() {
            return selected;
        }
    }
    
    @FXML
    void backToStudentList(ActionEvent event) {
    	paneViewActivity.setVisible(false);
        paneAddNewAct.setVisible(false);
        paneStudentList.setVisible(true);
        paneStudentDetail.setVisible(false);
        displayStudentList();
    }

    // Observable lists for tables
    private ObservableList<Activities> activityList;

    // Thêm Admin duy nhất cho toàn bộ controller
    private  Admin admin;

    @FXML
    public void initialize() {
        // Đọc thông tin Admin từ file data.json
        Admin adminInfo = null;
        java.util.List<entity.User> users = handle.entity.UserHandle.loadUsers();
        for (entity.User u : users) {
            if (u instanceof entity.Admin) {
                adminInfo = (entity.Admin) u;
                break;
            }
        }
        if (adminInfo != null) {
            textUserName.setText(adminInfo.getUserName());
            textID.setText(adminInfo.getUserID());
        }
        admin = new Admin();
        reloadActivityList();
        // Thiết lập các cột bảng Activity
        ActivitiesName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ActivitiesCategory.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        ActPlace.setCellValueFactory(cellData -> cellData.getValue().placeProperty());
        ActScore.setCellValueFactory(cellData -> cellData.getValue().scoreProperty());
        ActTime.setCellValueFactory(cellData -> cellData.getValue().timeProperty());

        // Cột checkbox
        colSelectAct.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        colSelectAct.setCellFactory(column -> new TableCell<>() {
            private final CheckBox checkBox = new CheckBox();

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    checkBox.setSelected(item != null && item);
                    checkBox.setOnAction(event ->
                            getTableView().getItems().get(getIndex()).selectedProperty().set(checkBox.isSelected()));
                    setGraphic(checkBox);
                }
            }
        });


        // Cột ActSelected - bổ sung tickbox
        ActSelected.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        ActSelected.setCellFactory(column -> new TableCell<>() {
            private final CheckBox checkBox = new CheckBox();

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    checkBox.setSelected(item != null && item);
                    checkBox.setOnAction(event ->
                            getTableView().getItems().get(getIndex()).selectedProperty().set(checkBox.isSelected()));
                    setGraphic(checkBox);
                }
            }
        });


        // Khi vào thì ở luôn giao diện quản lý hoạt động
        paneViewActivity.setVisible(true);
        paneAddNewAct.setVisible(false);
        ActPane.setVisible(true);
        paneStudentList.setVisible(false);
        paneStudentDetail.setVisible(false);

        // Khởi tạo danh sách lựa chọn trong ComboBox
        ActCategorySelected.setItems(FXCollections.observableArrayList("Tất cả tiêu chí", "Học tập", "Xã hội", "Kỷ luật", "Ý thức"));
        ActCategorySelected.getSelectionModel().select("Tất cả tiêu chí");
        ActivitiesCategorySelected.setItems(FXCollections.observableArrayList( "Học tập", "Xã hội", "Kỷ luật", "Ý thức"));
        ActivitiesCategorySelected.setValue("Chọn loại");
        ActSemesterSelected.setItems(FXCollections.observableArrayList("Tất cả các kỳ","2023.1", "2023.2", "2024.1", "2024.2"));
        ActSemesterSelected.setValue("Tất cả các kỳ");
        NewActSemesterCombo.setItems(FXCollections.observableArrayList("2023.1", "2023.2", "2024.1", "2024.2"));
        NewActSemesterCombo.setValue("Chọn kỳ học");
        
        // Thêm listener để tự động lọc khi chọn dropdown
        ActCategorySelected.setOnAction(event -> handleSearchActivity(null));
        ActSemesterSelected.setOnAction(event -> handleSearchActivity(null));
        
        // Gán cho bảng biết các thuộc tính
        ActivitiesName1.setCellValueFactory(c -> c.getValue().nameProperty());
        ActivitiesCategory1.setCellValueFactory(c -> c.getValue().categoryProperty());
        ActPlace1.setCellValueFactory(c -> c.getValue().placeProperty());
        ActScore1.setCellValueFactory(c -> c.getValue().scoreProperty());
        ActTime1.setCellValueFactory(c -> c.getValue().timeProperty());
        
    }

    // Hàm này để luôn đọc lại dữ liệu từ file json qua Admin
    private void reloadActivityList() {
        activityList = FXCollections.observableArrayList();
        java.util.List<entity.Activity> allActs = new java.util.ArrayList<>(admin.getAllActivities());
        // Sắp xếp theo thời gian giảm dần (dùng LocalDate)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        allActs.sort((a, b) -> {
            try {
                LocalDate dateA = LocalDate.parse(a.getDate(), formatter);
                LocalDate dateB = LocalDate.parse(b.getDate(), formatter);
                return dateB.compareTo(dateA);
            } catch (Exception e) {
                return 0;
            }
        });
        for (entity.Activity a : allActs) {
            if (a == null || a.getName() == null || a.getTitle() == null) continue;
            activityList.add(new Activities(
                a.getName(),          // name
                a.getTitle(),         // category
                a.getLocation(),      // place
                String.valueOf(a.getScore()), // score
                a.getDate(),          // time
                a.getSemester()       // semester
            ));
        }
        System.out.println("Danh sách hoạt động sau khi đọc file:");
        for (Activities act : activityList) {
            System.out.println(act.getName() + " - " + act.getCategory());
        }
        ActivitiesTable.setItems(activityList);
    }

    @FXML
    private void handleViewActivity(ActionEvent event) {
        paneViewActivity.setVisible(true);
        paneAddNewAct.setVisible(false);
        paneStudentList.setVisible(false);
        paneStudentDetail.setVisible(false);
        paneAddStudent.setVisible(false);
    }

    @FXML
    private void handleViewAddNewAct(ActionEvent event) {
        paneViewActivity.setVisible(false);
        paneAddNewAct.setVisible(true);
        paneStudentList.setVisible(false);
        paneStudentDetail.setVisible(false);
        paneAddStudent.setVisible(false);
    }

    @FXML
    private void handleViewStudentList(ActionEvent event) {
        paneViewActivity.setVisible(false);
        paneAddNewAct.setVisible(false);
        paneStudentList.setVisible(true);
        paneStudentDetail.setVisible(false);
        paneAddStudent.setVisible(false);
        displayStudentList();
    }
    
    @FXML
    void handleAddStudentSwitch(ActionEvent event) {
    	paneViewActivity.setVisible(false);
        paneAddNewAct.setVisible(false);
        paneStudentList.setVisible(false);
        paneStudentDetail.setVisible(false);
        paneAddStudent.setVisible(true);
    }

    private Alert alert;
    @FXML
    private void handleLogout(ActionEvent event) {
        try
        {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Chú ý!!!");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc muốn thoát chương trình không ?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK))
            {
                // Ẩn đi cửa sổ của Admin
                buttonLogout.getScene().getWindow().hide();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSearchActivity(ActionEvent event) {
        String searchText = Search2.getText().toLowerCase();
        String selectedCategory = ActCategorySelected.getValue();
        String selectedSemester = ActSemesterSelected.getValue();
        ObservableList<Activities> filteredList = FXCollections.observableArrayList();
        for (Activities activity : activityList) {
            boolean matchesCategory = (selectedCategory == null
                    || selectedCategory.equals("Tất cả tiêu chí")
                    || activity.getCategory().equals(selectedCategory));
            boolean matchesName = activity.getName().toLowerCase().contains(searchText);
            boolean matchesSemester = (selectedSemester == null || selectedSemester.equals("Tất cả các kỳ") || activity.getSemester().equals(selectedSemester));
            if (matchesCategory && matchesName && matchesSemester) {
                filteredList.add(activity);
            }
        }
        ActivitiesTable.setItems(filteredList);
    }

    @FXML
    private void handleAddAct(ActionEvent event) {
        // Lấy tên từ NewActName
        String name = NewActName.getText().trim();
        if (name.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Thiếu thông tin", "Vui lòng nhập Tên hoạt động.");
            return;
        }

        // Lấy thể loại từ ComboBox
        String category = ActivitiesCategorySelected.getValue();
        if (category == null || category.equals("Chọn loại")) {
            showAlert(Alert.AlertType.WARNING, "Thiếu thông tin", "Vui lòng chọn Phân loại.");
            return;
        }

        // Lấy điểm từ NewActScore
        String scoreText = NewActScore.getText().trim();
        if (scoreText.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Thiếu thông tin", "Vui lòng nhập Score.");
            return;
        }
        // Kiểm tra điểm có phải số nguyên không
        try {
            Integer.parseInt(scoreText);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Sai định dạng", "Score phải là một số nguyên.");
            return;
        }

        // Lấy thời gian từ NewActTime
        String time = NewActTime.getText().trim();
        if (time.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Thiếu thông tin", "Vui lòng nhập Thời gian.");
            return;
        }

        // Lấy địa điểm từ NewActPlace
        String place = NewActPlace.getText().trim();
        if (place.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Thiếu thông tin", "Vui lòng nhập Địa điểm.");
            return;
        }

        String semester = NewActSemesterCombo.getValue();
        if (semester == null || semester.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Thiếu thông tin", "Vui lòng chọn Kỳ học.");
            return;
        }
        // Kiểm tra trùng tên và kỳ học
        for (Activities act : activityList) {
            if (act.getName().equalsIgnoreCase(name) && act.getSemester().equalsIgnoreCase(semester)) {
                showAlert(Alert.AlertType.WARNING, "Trùng hoạt động", "Hoạt động \"" + name + "\" kỳ \"" + semester + "\" đã tồn tại.");
                return;
            }
        }
        Activities newAct = new Activities(name, category, place, scoreText, time, semester);
        activityList.add(newAct);
        ActivitiesTable.setItems(activityList);
        // Ghi vào file activity.json qua Admin
        entity.Activity realAct = new entity.Activity(category, name, semester, true, Integer.parseInt(scoreText), time, place);
        new entity.Admin().addActivity(realAct);
        reloadActivityList();

        // Hiển thị bảng thông báo và hỏi lựa chọn người dùng
        Alert postAlert = new Alert(Alert.AlertType.CONFIRMATION);
        postAlert.setTitle("Thêm hoạt động thành công");
        postAlert.setHeaderText(null);
        postAlert.setContentText(
                "Bạn đã thêm hoạt động \"" + name + "\".\n\n" +
                        "Chọn \"Tiếp tục thêm\" để thêm hoạt động khác, hoặc \"Quay về\" để trở về danh sách quản lý."
        );

        ButtonType btnContinue = new ButtonType("Tiếp tục thêm", ButtonBar.ButtonData.OK_DONE);
        ButtonType btnBack     = new ButtonType("Quay về",        ButtonBar.ButtonData.CANCEL_CLOSE);
        postAlert.getButtonTypes().setAll(btnContinue, btnBack);

        Optional<ButtonType> result = postAlert.showAndWait();
        if (result.isEmpty() || result.get() == btnBack) {
            paneViewActivity.setVisible(true);
            paneAddNewAct.setVisible(false);
            NewActName.clear();
            ActivitiesCategorySelected.setValue("Chọn loại");
            NewActScore.clear();
            NewActTime.clear();
            NewActPlace.clear();
            NewActSemesterCombo.setValue(null);
        } else {
            // Nếu chọn "Tiếp tục thêm", chỉ cần clear form để họ nhập lần kế tiếp
            NewActName.clear();
            ActivitiesCategorySelected.setValue("Chọn loại");
            NewActScore.clear();
            NewActTime.clear();
            NewActPlace.clear();
            NewActSemesterCombo.setValue(null);
            // Giữ nguyên paneAddNewAct đang hiển thị
        }
    }

    @FXML
    private void handleRemoveAct(ActionEvent event) {
        // Tập hợp những hoạt động đang được tích chọn
        ObservableList<Activities> toRemove = FXCollections.observableArrayList();
        for (Activities act : activityList) {
            if (act.isSelected()) {
                toRemove.add(act);
            }
        }

        if (toRemove.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Chưa chọn hoạt động", "Vui lòng tích chọn các hoạt động cần xóa.");
            return;
        }

        // Hiển thị hộp thoại xác nhận
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Xác nhận xóa");
        confirm.setHeaderText(null);
        confirm.setContentText("Bạn có chắc muốn xóa " + toRemove.size() + " hoạt động đã chọn không?");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isEmpty() || result.get() != ButtonType.OK) {
            // Nếu người dùng bấm Cancel hoặc đóng dialog thì không làm gì cả
            return;
        }

        // Xóa khỏi danh sách và làm mới TableView
        activityList.removeAll(toRemove);
        ActivitiesTable.setItems(activityList);

        // Xóa khỏi file activity.json qua Admin
        for (Activities act : toRemove) {
            entity.Activity realAct = new entity.Activity(
                    act.getCategory(),
                    act.getName(),
                    act.getSemester(),
                    true,
                    Integer.parseInt(act.getScore()),
                    act.getTime(),
                    act.getPlace()
            );
            new entity.Admin().removeOutdatedActivity(realAct);
        }
//        reloadActivityList();

        // Hiển thị thông báo đã xóa thành công
        showAlert(Alert.AlertType.INFORMATION, "Đã xóa", "Đã xóa " + toRemove.size() + " hoạt động.");
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Hàm hiển thị danh sách sinh viên dạng grid
    public void displayStudentList() {
        studentGridPane.getChildren().clear();
        int column = 0;
        int row = 1;
        final String ITEM_FXML = "/view/StudentInfo.fxml";
        java.util.List<entity.User> users = handle.entity.UserHandle.loadUsers();
        try {
            for (entity.User u : users) {
                if (u instanceof entity.Student student) {
                    // Sắp xếp hoạt động của sinh viên theo thời gian giảm dần
                    student.getRegisteredActivities().sort((a, b) -> b.getDate().compareTo(a.getDate()));
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(ITEM_FXML));
                    AnchorPane pane = loader.load();
                    controller.StudentInfoController ctrl = loader.getController();
                    ctrl.setData(student);
                    ctrl.setOnInfoClicked(s -> showStudentDetail(s));
                    pane.setUserData(ctrl);
                    if (column == 3) { column = 0; row++; }
                    studentGridPane.add(pane, column++, row);
                    GridPane.setMargin(pane, new Insets(20, 10, 10, 10));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
	 private void showStudentDetail(Student student) 
	 {

		 this.currentStudent = student;
	     paneStudentList.setVisible(false);
	     paneStudentDetail.setVisible(true);

	     detailNameLabel.setText(student.getUserName());
	     detailIDLabel.setText(student.getUserID());

	     Image avatar;
	     String fileName = "/avatar/" + student.getUserName() + ".jpg";
	     java.io.InputStream is = getClass().getResourceAsStream(fileName);
	     if (is == null) 
	     {
	         is = getClass().getResourceAsStream("/avatar/default.jpg");
	     }
	     avatar = new Image(is);
	     studentImg.setImage(avatar);
	
	     final double SIZE = 150;
	     studentImg.setFitWidth(SIZE);
	     studentImg.setFitHeight(SIZE);
	     studentImg.setPreserveRatio(false);
	     studentImg.setClip(new Rectangle(SIZE, SIZE));
	     detailTotalActivityLabel.setText(String.valueOf(student.getRegisteredActivities().size()));
	

	     java.util.List<Activity> acts = new java.util.ArrayList<>(student.getRegisteredActivities());
	     java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
	     acts.sort((a, b) -> {
	         try {
	             return java.time.LocalDate.parse(b.getDate(), fmt)
	                                       .compareTo(java.time.LocalDate.parse(a.getDate(), fmt));
	         } catch (Exception e) {                // lỡ sai format
	             return 0;
	         }
	     });

	     ObservableList<Activities> rows = FXCollections.observableArrayList();
	     for (Activity a : acts) {
	         rows.add(new Activities(
	                 a.getName(),
	                 a.getTitle(),
	                 a.getLocation(),
	                 String.valueOf(a.getScore()),
	                 a.getDate(),
	                 a.getSemester()
	         ));
	     }
	     ActivitiesTable1.setItems(rows);

	 }

    
    // Thêm 1 account student nữa
    @FXML
    void genNewAccount(ActionEvent event) {
    	String studentID = genAccountID.getText();
    	String studentUserName = genAccountUserName.getText();
    	String studentPassword = genAccountPassWord.getText();
    	
    	// Phải điền đủ thông tin
    	if (studentID.isBlank() || studentUserName.isBlank() || studentPassword.isBlank()) {
            showAlert(Alert.AlertType.WARNING,
                      "Thiếu thông tin",
                      "Bạn phải nhập đầy đủ MSSV, tên đăng nhập và mật khẩu!");
            return;
        }

        // MSSV phải có 8 số
        if (!studentID.matches("\\d{8}")) {
            showAlert(Alert.AlertType.ERROR,
                      "Sai định dạng",
                      "MSSV phải gồm đúng 8 chữ số!");
            return;
        }
    	
    	boolean ok = handle.entity.UserHandle.addStudent(studentID, studentUserName, studentPassword);
    	if (ok) 
    	{
            showAlert(Alert.AlertType.INFORMATION,"Thành công","Đã tạo tài khoản sinh viên mới!");
            displayStudentList();
            genAccountID.clear();
            genAccountUserName.clear();
            genAccountPassWord.clear();
        } 
    	else 
    	{
            showAlert(Alert.AlertType.ERROR, "Trùng ID / UserName", "Mã số hoặc tên đăng nhập đã tồn tại!");
        }
    }
    
}