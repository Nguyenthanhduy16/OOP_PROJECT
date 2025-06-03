package model;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import handle.model.ActivityHandle;
import handle.model.UserHandle;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Student extends User{
	private final int HocTap = 30;
	private final int KyLuat = 25;
	private final int XaHoi = 25;
	private final int YThuc = 20;
	private Admin admin;

	public void setAdmin(Admin admin) {
		// TODO Auto-generated method stub
		this.admin = admin;
	} 
	public Student() {super();} // Contructor cần thiết cho Json đọc dữ liệu không được xóa

	public Student(String userID, String userName, String passWord, Admin admin) {
	    super(userID, userName, passWord);
	    this.admin = admin;
	}
	

	
	// In ra tất cả hoạt động từ activity.json
    public void printAllActivities() {
        List<Activity> activities = ActivityHandle.loadActivities();
        for (Activity a : activities) {
            System.out.println(a.getTitle() + " - " + a.getName() + " - Score: " + a.getScore());
        }
    }
	
    // Tìm hoạt động theo tên (name) trong activity.json
    public List<Activity> searchActivitiesByName(String keyword) {
        List<Activity> allActivities = ActivityHandle.loadActivities();
        List<Activity> matched = new ArrayList<>();
        for (Activity a : allActivities) {
            if (a.getName().toLowerCase().contains(keyword.toLowerCase())) {
                matched.add(a);
            }
        }
        return matched;
    }
	
    
    // Xem các hoạt động đã đăng ký
    public void viewRegisteredActivities() {
        List<Activity> registered = UserHandle.getRegisteredActivities(this.getUserID());
        int total = 0;
        System.out.println("Registered Activities:");
        for (Activity a : registered) {
            System.out.println(a.getTitle() + " - " + a.getName() +
                               " - Score: " + a.getScore() +
                               " - Status: " + a.isStatus());
            if (a.isStatus()) {
                total += a.getScore();
            }
        }
        System.out.println("Total Score: " + total);
    }
	
 // Thêm một hoạt động từ activity.json vào danh sách đã đăng ký
    public void addActivity(Activity activity) {
        List<Activity> registered = UserHandle.getRegisteredActivities(this.getUserID());

        // Kiểm tra trùng lặp
        for (Activity a : registered) {
            if (a.getName().equalsIgnoreCase(activity.getName())) {
                System.out.println("Activity already registered.");
                return;
            }
        }

        activity.setStatus(true);
        registered.add(activity);
        UserHandle.updateRegisteredActivities(this.getUserID(),registered);
        System.out.println("Added activity: " + activity.getName());
    }
    
    // Xóa một hoạt động đã đăng ký
    public void removeActivity(String title) {
    	title = title.trim(); //loại bỏ khoảng trắng dư thừa
    	
    	List<Activity> registered = UserHandle.getRegisteredActivities(this.getUserID());
        Activity toRemove = null;

        for (Activity a : registered) {
            if (a.getName().equalsIgnoreCase(title)) {
                toRemove = a;
                break;
            }
        }

        if (toRemove == null) {
            System.out.println("Activity not found in registered list: " + title);
            return;
        }

        toRemove.setStatus(false);
        registered.remove(toRemove);
        UserHandle.updateRegisteredActivities(this.getUserID(),registered);
        System.out.println("Removed activity: " + title);
    }
	
    public int totalScore() {
		int total1 = 0;
		int total2 = 0;
		int total3 = 0;
		int total4 = 0;
		
	    List<Activity> registered = UserHandle.getRegisteredActivities(this.getUserID());
	    
		for(Activity activity: registered) {
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
	public ArrayList<Activity> getRegisteredActivities() {
		return (ArrayList<Activity>) UserHandle.getRegisteredActivities(this.getUserID());
    }

}		
	
	/*
	
	//Tìm kiếm hoạt động theo tên
	public void searchAndRegisterActivity(String name, ArrayList<Activity> allActivities) {
	    boolean found = false;
	    for (Activity activity : allActivities) {
	        if (activity != null && activity.getName().equalsIgnoreCase(name)) {
	            System.out.println("Found: " + activity);
	            registeredActivities.add(activity); // Đăng ký
	            found = true;
	            break;
	        }
	    }
	    if (!found) {
	        System.out.println("No activity has name: " + name);
	    }
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
	
	public void registerActivity(Activity activity) {
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
	    for(Activity activity: activities) {
	        if(!registeredActivities.contains(activity))
	            System.out.println(activity.getName() + " " + activity.getTitle() + " " + activity.getScore());
	    }
	}
*/

