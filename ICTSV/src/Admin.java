import java.util.ArrayList;

public class Admin extends User {

    private ArrayList<Activity> listActivities = new ArrayList<Activity>();

    public void showMenu() {
        System.out.println("Admin Menu:");
        System.out.println("1. View all activities");
        System.out.println("2. Add new activity");
        System.out.println("3. Remove outdated activity");
        System.out.println("4. Print all activities");
        System.out.println("5. Log out");
    }

    // Thêm hoạt động mới cho sinh viên
    public void addNewActivity(String name, String title, boolean status, int points) {
        boolean found = false;

        for (Activity activity : listActivities) {
            if (activity != null && activity.getName()== name) {
                System.out.println("Activity with name " + name + " already exists.");
                found = true;
                break;
            }
        }

        if (!found) {
            Activity newActivity = new Activity(title, name, status, points);
            listActivities.add(newActivity);
            System.out.println("Added activity: " + title + " with name " + name + " have " + points + " points.");
        }
    }

    // Xóa hoạt động đã hết hạn
    public void removeOutdatedActivity(Activity activity) {
    	if(listActivities.contains(activity)) {
			listActivities.remove(activity);
		}else {
			System.out.println(activity.getName() + " was not found in your registered activity");
		}
        printActivity();
    }


    // In tất cả các hoạt động và điểm tương ứng
    public void printActivity() {
        System.out.println("All Activities:");
        for (Activity activity : listActivities) {
            System.out.println("- " + activity.getName() + " (" + activity.getScore() + " points)");
        }
    }

    // Optional: Truy cập danh sách nếu cần
    public ArrayList<Activity> getListActivities() {
        return listActivities;
    }
}
