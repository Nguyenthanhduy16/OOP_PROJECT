import java.util.ArrayList;

public class Admin extends User {

    public ArrayList<Activity> listActivities = new ArrayList<Activity>();

    public void showMenu() {
        System.out.println("Admin Menu:");
        System.out.println("1. View all activities");
        System.out.println("2. Add new activity");
        System.out.println("3. Remove outdated activity");
        System.out.println("4. Print all activities");
        System.out.println("5. Log out");
    }

    // Thêm hoạt động mới cho sinh viên
    public void addNewActivity(Activity activity) {
    	if(listActivities.contains(activity)) {
    		System.out.println(activity.getName() + " has already been added.");
		}else {
			listActivities.add(activity);
		}
    }

    // Xóa hoạt động đã hết hạn
    public void removeOutdatedActivity(Activity activity) {
    	if(listActivities.contains(activity)) {
			listActivities.remove(activity);
		}else {
			System.out.println(activity.getName() + " was not found in your activities.");
		}
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
