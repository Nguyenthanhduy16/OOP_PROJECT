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
    private AnchorPane paneFullPane;
    @FXML
    private AnchorPane paneStudentManage;
    @FXML
    private AnchorPane paneViewActivity;
    @FXML
    private Label textUserName;
    @FXML
    private Label textID;
    @FXML
    private Label textRole;
    @FXML
    private Button buttonViewActivity;
    @FXML
    private Button buttonViewStudent;
    @FXML
    private Button buttonLogout;
    @FXML
    private TextField SearchStu;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField SearchAct;
    @FXML
    private Button btnSearch1;
    @FXML
    private TextField Search2;
    @FXML
    private Button btnSearch2;
    @FXML
    private Button BtnAddActToStu;
    @FXML
    private Button btnRemoveActToStu;
    @FXML
    private Button AddActButton;
    @FXML
    private Button RemoveActButton;
    @FXML
    private ComboBox<String> ActCategorySelected;
    @FXML
    private ComboBox<String> ActivitiesCategorySelected;
    @FXML
    private TableView<Student> StudentTable;
    @FXML
    private TableColumn<Student, String> StuName;
    @FXML
    private TableColumn<Student, String> StuID;
    @FXML
    private TableColumn<Student, Boolean> colSelect;
    @FXML
    private TableView<Activity> ActivitiesTable;
    @FXML
    private TableColumn<Activity, String> Act;
    @FXML
    private TableColumn<Activity, String> Category;
    @FXML
    private TableColumn<Activity, String> Duration;
    @FXML
    private TableColumn<Activity, Boolean> colSelectAct;

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
        private final BooleanProperty selected;

        public Activity(String name, String category, String duration) {
            this.name = new SimpleStringProperty(name);
            this.category = new SimpleStringProperty(category);
            this.duration = new SimpleStringProperty(duration);
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
        // Initialize table columns
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
                    checkBox.setSelected(item);
                    checkBox.setOnAction(event -> getTableView().getItems().get(getIndex()).selectedProperty().set(checkBox.isSelected()));
                    setGraphic(checkBox);
                }
            }
        });

        Act.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        Category.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        Duration.setCellValueFactory(cellData -> cellData.getValue().durationProperty());
        colSelectAct.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        colSelectAct.setCellFactory(column -> new TableCell<>() {
            private final CheckBox checkBox = new CheckBox();

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    checkBox.setSelected(item);
                    checkBox.setOnAction(event -> getTableView().getItems().get(getIndex()).selectedProperty().set(checkBox.isSelected()));
                    setGraphic(checkBox);
                }
            }
        });

        // Initialize sample data
        studentList = FXCollections.observableArrayList(
                new Student("Nguyen Van A", "STU001"),
                new Student("Tran Thi B", "STU002")
        );
        activityList = FXCollections.observableArrayList(
                new Activity("Chìa khóa học tốt", "Học tập", "2025-06-30"),
                new Activity("Ngày hội thể thao", "Văn hóa, chính trị, thể thao", "2025-07-15")
        );

        StudentTable.setItems(studentList);
        ActivitiesTable.setItems(activityList);

        // Set initial pane visibility
        paneStudentManage.setVisible(true);
        paneViewActivity.setVisible(false);

        // Initialize combo box
        ActCategorySelected.setItems(FXCollections.observableArrayList("Tất cả", "Học tập", "Văn hóa, chính trị, thể thao", "Ý thức"));
        ActivitiesCategorySelected.setItems(FXCollections.observableArrayList("Tất cả", "Học tập", "Văn hóa, chính trị, thể thao", "Ý thức"));
    }

    @FXML
    private void handleViewActivity(ActionEvent event) {
        paneStudentManage.setVisible(false);
        paneViewActivity.setVisible(true);
    }

    @FXML
    private void handleViewStudent(ActionEvent event) {
        paneStudentManage.setVisible(true);
        paneViewActivity.setVisible(false);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        // Close the current window
        Stage stage = (Stage) buttonLogout.getScene().getWindow();
        stage.close();
        // TODO: Implement actual logout logic (e.g., redirect to login screen)
    }

    @FXML
    private void handleSearchStudent(ActionEvent event) {
        String searchText = SearchStu.getText().toLowerCase();
        ObservableList<Student> filteredList = FXCollections.observableArrayList();
        for (Student student : studentList) {
            if (student.getName().toLowerCase().contains(searchText) || student.getId().toLowerCase().contains(searchText)) {
                filteredList.add(student);
            }
        }
        StudentTable.setItems(filteredList);
    }

    @FXML
    private void handleSearchActivity(ActionEvent event) {
        String searchText = SearchAct.getText().toLowerCase();
        ObservableList<Activity> filteredList = FXCollections.observableArrayList();
        for (Activity activity : activityList) {
            if (activity.getName().toLowerCase().contains(searchText) || activity.getDuration().toLowerCase().contains(searchText)) {
                filteredList.add(activity);
            }
        }
        ActivitiesTable.setItems(filteredList);
    }

    @FXML
    private void handleSearchActivity2(ActionEvent event) {
        String searchText = Search2.getText().toLowerCase();
        String selectedCategory = ActCategorySelected.getValue();
        ObservableList<Activity> filteredList = FXCollections.observableArrayList();
        for (Activity activity : activityList) {
            if ((selectedCategory == null || selectedCategory.equals("Tất cả") || activity.getCategory().equals(selectedCategory)) &&
                    (activity.getName().toLowerCase().contains(searchText) || activity.getDuration().toLowerCase().contains(searchText))) {
                filteredList.add(activity);
            }
        }
        ActivitiesTable.setItems(filteredList);
    }

    @FXML
    private void handleAddActivityToStudent(ActionEvent event) {
        Student selectedStudent = null;
        Activity selectedActivity = null;

        for (Student student : studentList) {
            if (student.isSelected()) {
                selectedStudent = student;
                break;
            }
        }

        for (Activity activity : activityList) {
            if (activity.isSelected()) {
                selectedActivity = activity;
                break;
            }
        }

        if (selectedStudent != null && selectedActivity != null) {
            // TODO: Implement logic to associate activity with student in the database
            showAlert(Alert.AlertType.INFORMATION, "Success", "Added activity " + selectedActivity.getName() + " to student " + selectedStudent.getName());
        } else {
            showAlert(Alert.AlertType.WARNING, "Selection Error", "Please select one student and one activity.");
        }
    }

    @FXML
    private void handleRemoveActivityFromStudent(ActionEvent event) {
        Student selectedStudent = null;
        Activity selectedActivity = null;

        for (Student student : studentList) {
            if (student.isSelected()) {
                selectedStudent = student;
                break;
            }
        }

        for (Activity activity : activityList) {
            if (activity.isSelected()) {
                selectedActivity = activity;
                break;
            }
        }

        if (selectedStudent != null && selectedActivity != null) {
            // TODO: Implement logic to remove activity from student in the database
            showAlert(Alert.AlertType.INFORMATION, "Success", "Removed activity " + selectedActivity.getName() + " from student " + selectedStudent.getName());
        } else {
            showAlert(Alert.AlertType.WARNING, "Selection Error", "Please select one student and one activity.");
        }
    }

    @FXML
    private void handleAddActivity(ActionEvent event) {
        // TODO: Show the activity creation pane or open a dialog
        showAlert(Alert.AlertType.INFORMATION, "Add Activity", "Activity creation form to be implemented.");
    }

    @FXML
    private void handleRemoveActivity(ActionEvent event) {
        Activity selectedActivity = null;

        for (Activity activity : activityList) {
            if (activity.isSelected()) {
                selectedActivity = activity;
                break;
            }
        }

        if (selectedActivity != null) {
            activityList.remove(selectedActivity);
            // TODO: Implement logic to remove activity from the database
            showAlert(Alert.AlertType.INFORMATION, "Success", "Removed activity " + selectedActivity.getName());
        } else {
            showAlert(Alert.AlertType.WARNING, "Selection Error", "Please select an activity to remove.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}