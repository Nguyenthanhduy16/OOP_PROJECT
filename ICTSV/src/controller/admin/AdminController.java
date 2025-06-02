package controller.admin;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminController {

    // FXML injected fields
    @FXML
    private TableColumn<Activity, String> Act;

    @FXML
    private ComboBox<String> ActCategorySelected;
    @FXML
    private ComboBox<String> ActSemesterSelected;
    @FXML
    private AnchorPane ActPane;

    @FXML
    private TableColumn<Activity, String> ActPlace;

    @FXML
    private TableColumn<Activity, String> ActScore;

    @FXML
    private TableColumn<Activity, Boolean> ActSelected;

    @FXML
    private TableColumn<Activity, String> ActTime;

    @FXML
    private ComboBox<String> ActivitiesCategorySelected;

    @FXML
    private TableColumn<Activity, String> ActivitiesCatergory;

    @FXML
    private TableColumn<Activity, String> ActivitiesName;

    @FXML
    private TextField ActivitiesPlace;

    @FXML
    private ScrollPane ActivitiesScrollPan;

    @FXML
    private TableView<Activity> ActivitiesTable;

    @FXML
    private TextField ActivitiesTime;
    @FXML
    private TextField Semester;

    @FXML
    private Button AddActButton;
    @FXML
    private Button AddActivities;

    @FXML
    private Button BtnAddActToStu;

    @FXML
    private TableColumn<Activity, String> Category;

    @FXML
    private TableColumn<Activity, String> Duration;

    @FXML
    private Button RemoveActButton;

    @FXML
    private TextField Search2;

    @FXML
    private TextField SearchAct;

    @FXML
    private AnchorPane SearchActivities;

    @FXML
    private TextField SearchStu;

    @FXML
    private TableColumn<Student, String> StuID;

    @FXML
    private TableColumn<Student, String> StuName;

    @FXML
    private AnchorPane StudentPane;

    @FXML
    private ScrollPane StudentScrollPane;

    @FXML
    private TableView<Student> StudentTable;

    @FXML
    private Button btnRemoveActToStu;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnSearch1;

    @FXML
    private Button btnSearch2;

    @FXML
    private Button buttonLogout;

    @FXML
    private Button buttonViewActivity;

    @FXML
    private Button buttonViewStudent;

    @FXML
    private TableColumn<Student, Boolean> colSelect;

    @FXML
    private TableColumn<Activity, Boolean> colSelectAct;

    @FXML
    private AnchorPane panStuNActList;

    @FXML
    private AnchorPane paneFullPane;

    @FXML
    private AnchorPane paneStudentManage;

    @FXML
    private AnchorPane paneViewActivity;

    @FXML
    private Label textID;

    @FXML
    private Label textRole;

    @FXML
    private Label textUserName;

    // Data models
    public static class Student {
        private final StringProperty name;
        private final StringProperty id;
        private final BooleanProperty selected;

        public Student(String name, String id) {
            this.name = new SimpleStringProperty(name);
            this.id = new SimpleStringProperty(id);
            this.selected = new SimpleBooleanProperty(false);
        }

        public String getName() {
            return name.get();
        }

        public StringProperty nameProperty() {
            return name;
        }

        public String getId() {
            return id.get();
        }

        public StringProperty idProperty() {
            return id;
        }

        public boolean isSelected() {
            return selected.get();
        }

        public BooleanProperty selectedProperty() {
            return selected;
        }
    }

    public static class Activity {
        private final StringProperty name;
        private final StringProperty category;
        private final StringProperty duration;
        private final StringProperty place;
        private final StringProperty score;
        private final StringProperty time;
        private final BooleanProperty selected;

        public Activity(String name, String category, String duration, String place, String score, String time) {
            this.name = new SimpleStringProperty(name);
            this.category = new SimpleStringProperty(category);
            this.duration = new SimpleStringProperty(duration);
            this.place = new SimpleStringProperty(place);
            this.score = new SimpleStringProperty(score);
            this.time = new SimpleStringProperty(time);
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

        public String getDuration() {
            return duration.get();
        }

        public StringProperty durationProperty() {
            return duration;
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

        public boolean isSelected() {
            return selected.get();
        }

        public BooleanProperty selectedProperty() {
            return selected;
        }
    }

    // Observable lists for tables
    private ObservableList<Student> studentList;
    private ObservableList<Activity> activityList;

    @FXML
    public void initialize() {
        // Thiết lập các cột bảng Student
        StuName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        StuID.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        colSelect.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        colSelect.setCellFactory(column -> new TableCell<>() {
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

        // Thiết lập các cột bảng Activity
        Act.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ActivitiesName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        Category.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        ActivitiesCatergory.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        Duration.setCellValueFactory(cellData -> cellData.getValue().durationProperty());
        ActPlace.setCellValueFactory(cellData -> cellData.getValue().placeProperty());
        ActScore.setCellValueFactory(cellData -> cellData.getValue().scoreProperty());
        ActTime.setCellValueFactory(cellData -> cellData.getValue().timeProperty());

        // Cột colSelectAct - hiển thị checkbox
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

        // ✅ Cột ActSelected - bổ sung tickbox như yêu cầu
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

        // Khởi tạo dữ liệu mẫu
        studentList = FXCollections.observableArrayList(
                new Student("Nguyen Van A", "STU001"),
                new Student("Tran Thi B", "STU002")
        );
        activityList = FXCollections.observableArrayList(
                new Activity("Chìa khóa học tốt", "Học tập", "2025-06-30", "Room 101", "10", "08:00"),
                new Activity("Ngày hội thể thao", "Văn hóa, chính trị, thể thao", "2025-07-15", "Stadium", "15", "09:00")
        );

        StudentTable.setItems(studentList);
        ActivitiesTable.setItems(activityList);

        // Ẩn các bảng không cần thiết ban đầu
        paneStudentManage.setVisible(true);
        paneViewActivity.setVisible(false);
        ActPane.setVisible(false);
        SearchActivities.setVisible(false);
        panStuNActList.setVisible(false);

        // Khởi tạo danh sách lựa chọn trong ComboBox
        ActCategorySelected.setItems(FXCollections.observableArrayList("Tất cả", "Học tập", "Văn hóa, chính trị, thể thao", "Ý thức"));
        ActSemesterSelected.setItems(FXCollections.observableArrayList("2024.2", "2024.1", "2023.2", "2023.1"));
        ActivitiesCategorySelected.setItems(FXCollections.observableArrayList("Tất cả", "Học tập", "Văn hóa, chính trị, thể thao", "Ý thức"));
    }
}