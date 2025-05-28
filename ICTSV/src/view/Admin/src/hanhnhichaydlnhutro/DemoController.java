package hanhnhichaydlnhutro;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.Set;
import java.util.stream.Collectors;

public class DemoController {

    // FXML-injected fields
    @FXML private AnchorPane paneFullPane;
    @FXML private AnchorPane paneStudentManage;
    @FXML private AnchorPane paneViewActivity;
    @FXML private AnchorPane panStuNActList;
    @FXML private AnchorPane StudentPane;
    @FXML private AnchorPane ActPane;
    @FXML private AnchorPane SearchAct;
    @FXML private AnchorPane CreateAct;
    @FXML private Label textUserName;
    @FXML private Label textID;
    @FXML private Label textRole;
    @FXML private Button buttonViewActivity;
    @FXML private Button buttonViewStudent;
    @FXML private Button buttonLogout;
    @FXML private Button btnSearch;
    @FXML private Button btnSearch2;
    @FXML private Button BtnAddActToStu;
    @FXML private Button btnRemoveActToStu;
    @FXML private Button AddActButton;
    @FXML private Button RemoveActButton;
    @FXML private TextField Search;
    @FXML private TextField Search2;
    @FXML private TextField ActName;
    @FXML private TextField Score;
    @FXML private TextField Time;
    @FXML private TableView<Student> StudentTable;
    @FXML private TableView<Activity> ActivitiesTable;
    @FXML private TableColumn<Student, String> StuName;
    @FXML private TableColumn<Student, String> StuID;
    @FXML private TableColumn<Student, Boolean> colSelect;
    @FXML private TableColumn<Activity, String> Act;
    @FXML private TableColumn<Activity, String> Category;
    @FXML private TableColumn<Activity, String> Duration;
    @FXML private TableColumn<Activity, Boolean> colSelectAct;
    @FXML private ComboBox<String> ActivitiesCategory;
    @FXML private ComboBox<String> ActCateDropbox;
    @FXML private ScrollPane StudentScrollPane;
    @FXML private ScrollPane ActivitiesScrollPan;

    // Data for TableViews
    private ObservableList<Student> studentData = FXCollections.observableArrayList();
    private ObservableList<Activity> activityData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize TableView columns for StudentTable
        StuName.setCellValueFactory(new PropertyValueFactory<>("name"));
        StuID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSelect.setCellValueFactory(new PropertyValueFactory<>("selected"));
        colSelect.setCellFactory(CheckBoxTableCell.forTableColumn(colSelect));

        // Initialize TableView columns for ActivitiesTable
        Act.setCellValueFactory(new PropertyValueFactory<>("name"));
        Category.setCellValueFactory(new PropertyValueFactory<>("category"));
        Duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colSelectAct.setCellValueFactory(new PropertyValueFactory<>("selected"));
        colSelectAct.setCellFactory(CheckBoxTableCell.forTableColumn(colSelectAct));

        // Ensure TableViews are wrapped in ScrollPanes
        StudentScrollPane.setContent(StudentTable);
        ActivitiesScrollPan.setContent(ActivitiesTable);

        // Populate sample data
        loadSampleStudentData();
        loadSampleActivityData();

        // Initialize category dropdowns
        initCategoryDropDown();

        // Set initial visibility
        paneStudentManage.setVisible(true);
        paneViewActivity.setVisible(false);
        CreateAct.setVisible(false);

        // Bind button actions
        buttonViewActivity.setOnAction(e -> showActivityPane());
        buttonViewStudent.setOnAction(e -> showStudentPane());
        buttonLogout.setOnAction(e -> handleLogout());
        btnSearch.setOnAction(e -> handleSearch());
        btnSearch2.setOnAction(e -> handleSearchActivity());
        BtnAddActToStu.setOnAction(e -> handleAddActivityToStudent());
        btnRemoveActToStu.setOnAction(e -> handleRemoveActivityFromStudent());
        AddActButton.setOnAction(e -> CreateAct.setVisible(true));
        RemoveActButton.setOnAction(e -> handleRemoveActivity());
    }

    /**
     * Initialize the ActivitiesCategory and ActCateDropbox with categories
     */
    private void initCategoryDropDown() {
        // Clear existing items
        ActivitiesCategory.getItems().clear();
        ActCateDropbox.getItems().clear();

        // Add "Tất cả" option for ActivitiesCategory
        ActivitiesCategory.getItems().add("Tất cả");
        ActivitiesCategory.setOnAction(e -> {
            String selected = ActivitiesCategory.getSelectionModel().getSelectedItem();
            if (selected == null || selected.equals("Tất cả")) {
                ActivitiesTable.setItems(activityData);
            } else {
                ObservableList<Activity> filtered = FXCollections.observableArrayList(
                        activityData.stream()
                                .filter(a -> a.getCategory().equals(selected))
                                .collect(Collectors.toList())
                );
                ActivitiesTable.setItems(filtered);
            }
        });

        // Get unique categories from activity data
        Set<String> categories = activityData.stream()
                .map(Activity::getCategory)
                .collect(Collectors.toSet());

        // Add categories to both ComboBoxes
        ActivitiesCategory.getItems().addAll(categories);
        ActCateDropbox.getItems().addAll(categories);
        ActCateDropbox.getItems().add("Khác");

        // Set prompt text
        ActivitiesCategory.setPromptText("Chọn danh mục");
        ActCateDropbox.setPromptText("Chọn danh mục");

        // Allow custom category input for ActCateDropbox
        ActCateDropbox.setEditable(true);
        ActCateDropbox.setOnAction(e -> {
            String selected = ActCateDropbox.getSelectionModel().getSelectedItem();
            if (selected != null && selected.equals("Khác")) {
                ActCateDropbox.setValue(""); // Allow user to type custom category
            }
        });
    }

    private void loadSampleStudentData() {
        studentData.addAll(
                new Student("Nguyen Van A", "ST001", false),
                new Student("Tran Thi B", "ST002", false),
                new Student("Le Van C", "ST003", false)
        );
        StudentTable.setItems(studentData);
    }

    private void loadSampleActivityData() {
        activityData.addAll(
                new Activity("Hội thảo AI", "Học tập", "2 giờ", "5", false),
                new Activity("Chạy bộ từ thiện", "Văn hóa, chính trị, thể thao", "3 giờ", "3", false),
                new Activity("Workshop Java", "Học tập", "4 giờ", "4", false)
        );
        ActivitiesTable.setItems(activityData);
    }

    private void showActivityPane() {
        paneStudentManage.setVisible(false);
        paneViewActivity.setVisible(true);
        CreateAct.setVisible(false);
    }

    private void showStudentPane() {
        paneStudentManage.setVisible(true);
        paneViewActivity.setVisible(false);
        CreateAct.setVisible(false);
    }

    private void handleLogout() {
        System.out.println("User logged out");
        // Add actual logout logic here
    }

    private void handleSearch() {
        String query = Search.getText().trim().toLowerCase();
        if (query.isEmpty()) {
            StudentTable.setItems(studentData);
        } else {
            ObservableList<Student> filtered = FXCollections.observableArrayList(
                    studentData.stream()
                            .filter(s -> s.getName().toLowerCase().contains(query) ||
                                    s.getId().toLowerCase().contains(query))
                            .collect(Collectors.toList())
            );
            StudentTable.setItems(filtered);
        }
    }

    private void handleSearchActivity() {
        String query = Search2.getText().trim().toLowerCase();
        if (query.isEmpty()) {
            ActivitiesTable.setItems(activityData);
        } else {
            ObservableList<Activity> filtered = FXCollections.observableArrayList(
                    activityData.stream()
                            .filter(a -> a.getName().toLowerCase().contains(query) ||
                                    a.getCategory().toLowerCase().contains(query))
                            .collect(Collectors.toList())
            );
            ActivitiesTable.setItems(filtered);
        }
    }

    private void handleAddActivityToStudent() {
        Student selectedStudent = StudentTable.getSelectionModel().getSelectedItem();
        Activity selectedActivity = ActivitiesTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null && selectedActivity != null) {
            System.out.println("Added activity " + selectedActivity.getName() + " to student " + selectedStudent.getName());
        } else {
            System.out.println("Please select both a student and an activity");
        }
    }

    private void handleRemoveActivityFromStudent() {
        Student selectedStudent = StudentTable.getSelectionModel().getSelectedItem();
        Activity selectedActivity = ActivitiesTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null && selectedActivity != null) {
            System.out.println("Removed activity " + selectedActivity.getName() + " from student " + selectedStudent.getName());
        } else {
            System.out.println("Please select both a student and an activity");
        }
    }

    @FXML
    private void handleAddActivity() {
        String name = ActName.getText().trim();
        String category = ActCateDropbox.getValue() != null ? ActCateDropbox.getValue().trim() : "";
        String duration = Time.getText().trim();
        String scoreText = Score.getText().trim();

        try {
            double score = scoreText.isEmpty() ? 0 : Double.parseDouble(scoreText);
            if (!name.isEmpty() && !category.isEmpty() && !duration.isEmpty() && score >= 0) {
                activityData.add(new Activity(name, category, duration, String.valueOf(score), false));
                ActivitiesTable.refresh();
                initCategoryDropDown(); // Update categories
                clearAddActivityFields();
                CreateAct.setVisible(false);
            } else {
                System.out.println("Please fill in all fields with valid data");
            }
        } catch (NumberFormatException e) {
            System.out.println("Score must be a valid number");
        }
    }

    @FXML
    private void cancelAddActivity() {
        clearAddActivityFields();
        CreateAct.setVisible(false);
    }

    private void clearAddActivityFields() {
        ActName.clear();
        Time.clear();
        Score.clear();
        ActCateDropbox.setValue(null);
    }

    private void handleRemoveActivity() {
        Activity selectedActivity = ActivitiesTable.getSelectionModel().getSelectedItem();
        if (selectedActivity != null) {
            activityData.remove(selectedActivity);
            ActivitiesTable.refresh();
            initCategoryDropDown();
        } else {
            System.out.println("Please select an activity to remove");
        }
    }

    // Student class
    public static class Student {
        private final StringProperty name;
        private final StringProperty id;
        private final BooleanProperty selected;

        public Student(String name, String id, boolean selected) {
            this.name = new SimpleStringProperty(name);
            this.id = new SimpleStringProperty(id);
            this.selected = new SimpleBooleanProperty(selected);
        }
        public String getName() { return name.get(); }
        public StringProperty nameProperty() { return name; }
        public String getId() { return id.get(); }
        public StringProperty idProperty() { return id; }
        public boolean isSelected() { return selected.get(); }
        public BooleanProperty selectedProperty() { return selected; }
    }

    // Activity class
    public static class Activity {
        private final StringProperty name;
        private final StringProperty category;
        private final StringProperty duration;
        private final StringProperty score;
        private final BooleanProperty selected;

        public Activity(String name, String category, String duration, String score, boolean selected) {
            this.name = new SimpleStringProperty(name);
            this.category = new SimpleStringProperty(category);
            this.duration = new SimpleStringProperty(duration);
            this.score = new SimpleStringProperty(score);
            this.selected = new SimpleBooleanProperty(selected);
        }
        public String getName() { return name.get(); }
        public StringProperty nameProperty() { return name; }
        public String getCategory() { return category.get(); }
        public StringProperty categoryProperty() { return category; }
        public String getDuration() { return duration.get(); }
        public StringProperty durationProperty() { return duration; }
        public String getScore() { return score.get(); }
        public StringProperty scoreProperty() { return score; }
        public boolean isSelected() { return selected.get(); }
        public BooleanProperty selectedProperty() { return selected; }
    }
}
