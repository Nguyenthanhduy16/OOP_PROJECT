package model;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Student extends User{
	private final int HocTap = 30;
	private final int KyLuat = 25;
	private final int XaHoi = 25;
	private final int YThuc = 20;
	private Admin admin;
	
	public Student() {super();}

	public Student(String userID, String userName, String passWord, Admin admin) {
	    super(userID, userName, passWord);
	    this.admin = admin;
	}
	
	private ArrayList<Activity> registeredActivities = new ArrayList<Activity>();
	public void searchActivity(String name) {
		boolean found = false;
		for(Activity activity: registeredActivities ) {
			if(activity != null && activity.getName() == name) {
	            System.out.println("Found: " + activity.toString());
	            found = true;
	            break;
			}
			if(!found) {
				System.out.println("No activity has name: " + name);
			}
		}
	}
	public void addActivity(Activity activity) {
		if(registeredActivities.contains(activity)) {
			System.out.println(activity.getName() + " is ready in your registered activity");
		}else {
			registeredActivities.add(activity);
			System.out.println(activity.getName() + " has been added to your registered activity");
		}
	}
	public void cancelRegisteredActivity(Activity activity) {
		if(registeredActivities.contains(activity)) {
			registeredActivities.remove(activity);
		}else {
			System.out.println(activity.getName() + " was not found in your registered activity");
		}
	}
	public void viewRegisteredActivity() {
		for(int i = 0 ; i < registeredActivities.size(); i++) {
			System.out.println((i + 1) + ". " + registeredActivities.get(i).toString());
		}
	}
	public int totalScore() {
		int total1 = 0;
		int total2 = 0;
		int total3 = 0;
		int total4 = 0;
		for(Activity activity: registeredActivities) {
			if(activity.getTitle().equals("HocTap")) {
				total1 += activity.getScore();
				if(total1 > HocTap) total1 = HocTap; 
			}
			if(activity.getTitle().equals("KyLuat")) {
				total2 += activity.getScore();
				if(total2 > KyLuat) total2 = KyLuat; 
			}
			if(activity.getTitle().equals("XaHoi")) {
				total3 += activity.getScore();
				if(total3 > XaHoi) total3 = XaHoi; 
			}
			if(activity.getTitle().equals("YThuc")) {
				total4 += activity.getScore();
				if(total4 > YThuc) total4 = YThuc; 
			}
		}
		return total1 + total2 + total3 + total4;
	}
	public void print() {
	    for(Activity activity: Admin.getListActivities()) {
	        if(!registeredActivities.contains(activity))
	            System.out.println(activity.getName() + " " + activity.getTitle() + " " + activity.getScore());
	    }
	}
    public ArrayList<Activity> getRegisteredActivities() {
        return registeredActivities;
    }
}
