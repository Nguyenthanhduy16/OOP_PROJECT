package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class StudentController {

    @FXML
    private ScrollPane PaneviewActivityScroll;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonRegisterActivity;

    @FXML
    private Button buttonRegisterActivityButton;

    @FXML
    private Button buttonRegisterActivitySearch;

    @FXML
    private Button buttonViewActivity;

    @FXML
    private Button buttonViewScore;

    @FXML
    private Button buttonViewSearch;

    @FXML
    private CheckBox checkBoxSelectRegisterActivity;

    @FXML
    private TextField inputRegisterSearch;

    @FXML
    private TextField inputViewSearch;

    @FXML
    private AnchorPane paneFullPane;

    @FXML
    private AnchorPane paneRegister;

    @FXML
    private AnchorPane paneRegisterActivityFullPane;

    @FXML
    private AnchorPane paneRegisterActivityLists;

    @FXML
    private AnchorPane paneRegisterActivityPane;

    @FXML
    private ScrollPane paneRegisterActivityScrollPane;

    @FXML
    private AnchorPane paneViewActivity;

    @FXML
    private AnchorPane paneViewActivityFull;

    @FXML
    private AnchorPane paneViewActivityList;

    @FXML
    private Label scoreChinhTri;

    @FXML
    private BarChart<?, ?> scoreDetailBySemes;

    @FXML
    private Label scoreFinalSemes;

    @FXML
    private Label scoreFirstSemes;

    @FXML
    private BarChart<?, ?> scoreFullGraph;

    @FXML
    private Label scoreHocTap;

    @FXML
    private ComboBox<?> scoreKyHoc;

    @FXML
    private Label scoreKyLuat;

    @FXML
    private Label scoreSecondSemes;

    @FXML
    private Label scoreThirdSemes;

    @FXML
    private Label scoreYThuc;

    @FXML
    private Label textRegisterActivityCategory;

    @FXML
    private Label textRegisterActivityLocation;

    @FXML
    private Label textRegisterActivityScore;

    @FXML
    private Label textRegisterActivityTime;

    @FXML
    private Label textRegisterActivityTitle;

    @FXML
    private Label textUserName;

    @FXML
    private Label viewActivityCategory;

    @FXML
    private CheckBox viewActivityCheckBox;

    @FXML
    private Label viewActivityDate;

    @FXML
    private Label viewActivityLocation;

    @FXML
    private Label viewActivityScore;

    @FXML
    private Label viewActivityTitle;

    @FXML
    private Button viewCancelActivity;

    @FXML
    private ComboBox<?> viewKyHoc;

}
