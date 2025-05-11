import java.util.ArrayList;
import java.util.List;

public class Admin extends User {

    private List<Activity> listActivities;

    public Admin(String username, String password, String name) {
        super(username, password, name);
        this.listActivities = new ArrayList<>();
    }

    
    public void showMenu() {
        System.out.println("Admin Menu:");
        System.out.println("1. View all activities");
        System.out.println("2. Add new activity");
        System.out.println("3. Remove outdated activity");
        System.out.println("4. Print all activities");
        System.out.println("5. Log out");
    }

    // Thêm hoạt động mới cho sinh viên
    public void addNewActivity(String activityName, String title, int points) {
        Activity newActivity = new Activity(activityName, title, points);
        listActivities.add(newActivity);
        System.out.println("Added activity: " + activityName + " with title " + title + " have " + points + " points.");
    }

    // Xóa hoạt động đã hết hạn
    public void removeOutdatedActivity(Activity activity) {
        if (listActivities.size() == 0) {
            System.out.println("No activities to remove\n");
            return;
        }
        if (listActivities.remove(activity)) {
            System.out.println("The activity \"" + activity.getName() + "\" has been removed\n");
        } else {
            System.out.println("The activity \"" + activity.getName() + "\" does not exist in the list\n");
        }

        printActivity();
    }


    // In tất cả các hoạt động và điểm tương ứng
    public void printActivity() {
        System.out.println("All Activities:");
        for (Activity activity : listActivities) {
            System.out.println("- " + activity.getName() + " (" + activity.getPoints() + " points)");
        }
    }

    // Optional: Truy cập danh sách nếu cần
    public List<Activity> getListActivities() {
        return listActivities;
    }
}
