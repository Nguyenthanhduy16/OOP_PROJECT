import java.util.ArrayList;



public class Student extends User{
	private ArrayList<Activity> registeredActivities = new ArrayList<Activity>();
	public void searchActivity(String name) {
		boolean found = false;
		for(Activity activity: registeredActivities ) {
			if(activity != null && activity.getName == name) {
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
			System.out.println(activity.getName + " is ready in your registered activity");
		}else {
			registeredActivities.add(activity);
			System.out.println(activity.getName + " has been added to your registered activity");
		}
	}
	public void cancelRegisteredActivity(Activity activity) {
		if(registeredActivities.contains(activity)) {
			registeredActivities.remove(activity);
		}else {
			System.out.println(activity.getName + " was not found in your registered activity");
		}
	}
	public void viewRegisteredActivity() {
		for(int i = 0 ; i < registeredActivities.size(); i++) {
			System.out.println((i + 1) + ". " + registeredActivities.get(i).toString());
		}
	}
	public int totalScore() {
		int total = 0;
		for(Activity activity: registeredActivities) {
			total += activity.getScore();
		}
		return total;
	}
}
