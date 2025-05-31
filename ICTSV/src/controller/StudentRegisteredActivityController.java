package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import model.Activity;

public class StudentRegisteredActivityController implements Initializable 
{
	// TODO: lấy data từ phần activity và nhét vào các biến ở bên dưới
	
	
	
    @FXML
    private Label activity1Category;

    @FXML
    private Label activity1Date;

    @FXML
    private Label activity1Location;

    @FXML
    private Label activity1Score;

    @FXML
    private CheckBox activity1SelectButton;
    
    @FXML
    private Label activity1Title;
    
    private Activity activity;
    
    public void setData (Activity activity)
    {
    	// TODO: hỏi lại phần này, activity title với activity name khác nhau thế nào ?
    	this.activity = activity;
    	// activity1Title.setText(activity.getTitle());
    	// activity1Catgory.setText(activity.getName());
    	// activity1Date.setText(THỜI GIAN SỰ KIỆN);
    	// activity1Location.setText(ĐỊA ĐIỂM SỰ KIỆN);
    	activity1Score.setText(String.valueOf(activity.getScore()));
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		
		
	}

}