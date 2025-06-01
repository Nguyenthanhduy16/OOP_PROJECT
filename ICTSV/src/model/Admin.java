package model;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Admin extends User {

    public Admin(String userID, String userName, String passWord) {
		super(userID, userName, passWord);
		// TODO Auto-generated constructor stub
	}

	public Admin() {
		// TODO Auto-generated constructor stub
		super();
	}

	public static ArrayList<Activity> listActivities = new ArrayList<Activity>();
	public static ArrayList<Student> listStudents = new ArrayList<Student>();

	// Thêm sinh viên vào danh sách
	public static void addStudent(Student student) {
	    if (!listStudents.contains(student)) {
	        listStudents.add(student);
	    }else {
	        System.out.println("Student " + student.getUserName() + " is already in the system.");
	    }
	}

    public void showMenu() {
        System.out.println("Admin Menu:");
        System.out.println("1. Add new activity");
        System.out.println("2. Remove outdated activity");
        System.out.println("3. Print all activities");
        System.out.println("4. Add activity to a student");
        System.out.println("5. Remove activity from a student");
        System.out.println("6. Search activity by partial name");
        System.out.println("7. Filter by Title");
        System.out.println("8. Remove all activities");
        System.out.println("9. Search student");

    }

    //1. Thêm hoạt động mới
    public void addActivity(Activity activity) {
    	if(listActivities.contains(activity)) {
    		System.out.println(activity.getName() + " has already been added.");
		}else {
			listActivities.add(activity);
		}
    }

    //2. Xóa hoạt động đã hết hạn của ds Admin đồng thời xoá luôn trong danh sách đăng ký của Student
    public void removeOutdatedActivity(Activity activity) {
    	if(listActivities.contains(activity)) {
			listActivities.remove(activity);
			for (Student student : listStudents) {
	            student.cancelRegisteredActivity(activity);
			}
		}else {
			System.out.println(activity.getName() + " was not found in your activities.");
		}
    }

    //3. In tất cả các hoạt động và điểm tương ứng
    public void printActivity() {
        System.out.println("All Activities:");
        for (Activity activity : listActivities) {
        	activity.toPrint();
        }
    }

    // Truy cập danh sách
    public static ArrayList<Activity> getAllActivities() {
        return listActivities;
    }
    
//    //4. Thêm trực tiếp hoạt động cho sinh viên
//    public void addActivityToStudent(Student student, Activity activity) {
//        if (!listActivities.contains(activity)) {
//            System.out.println("Activity " + activity.getName() + " is not in the list.");
//            return;
//        }
//        student.registerActivity(activity);
//    }
    
    //5. Trực tiếp xóa hoạt động đã đăng ký của sinh viên
    public void removeActivityFromStudent(Student student, Activity activity) {
        student.cancelRegisteredActivity(activity);
    }
    
    //6. Tìm kiếm hoạt động theo Tên hoặc 1 phần của tên
    public void searchbyPartialName(String partialName) {
        String lowerPartial = partialName.toLowerCase();
        boolean found = false;
        for (Activity activity : listActivities) {
            if (activity.getName().toLowerCase().contains(lowerPartial)) {
            	activity.toPrint();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No activities found containing: " + partialName);
        }
    }
    
    
    //7. Lọc các hoạt động dựa theo tiêu chí
    public void filterbyTitle(String title) {
        boolean found = false;
        for (Activity activity : listActivities) {
            if (activity.getTitle().equals(title)) {
                activity.toPrint();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No activities found with title: " + title);
        }
    }
    
    //8. Xoá tất cả các hoạt động
    public void  removeAll() {
    	// tạo ra 1 bản sao danh sách hiện tại để duyệt khi xoá danh sách cũ 
    	ArrayList<Activity> tempList = new ArrayList<>(listActivities);

        for (Activity activity : tempList) {
            removeOutdatedActivity(activity);
        }
        System.out.println("All activities have been removed.");
    }
    
    //9. Tìm kiếm sinh viên dựa vào ID
    public void searchStudent(String userID) {
    	boolean found = false;

        for (Student student : listStudents) {
            if (student.getUserID().equals(userID)) {
                System.out.println("Found student: UserID: " + student.getUserID() + "UserName: " + student.getUserName());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No student found with ID: " + userID);
        }
    }

}