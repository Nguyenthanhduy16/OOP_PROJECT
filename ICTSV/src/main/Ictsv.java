package main;
import model.Activity;
import model.Admin;
import model.Student;

public class Ictsv {

	public static void main(String[] args) {
		Admin admin = new Admin("2000", "Admin01", "01234");
		Admin admin2 = new Admin("2005", "Admin02", "19025");

		Activity activity1 = new Activity("HocTap", "Example1", false, 10);
		Activity activity2 = new Activity("YThuc", "Example2", false, 20);
        Activity activity3 = new Activity("XaHoi", "Tinh Nguyen", false, 20);
        Activity activity4 = new Activity("KyLuat", "Phap Luat", false, 15);

		admin.addNewActivity(activity1);
		admin.addNewActivity(activity2);
        admin.addNewActivity(activity3);
        
		// Truyền admin vào student
		Student student1 = new Student("2023", "Student1", "1234"/*, admin*/);

		Admin.addStudent(student1);
		
		student1.print();
		admin.addActivityToStudent(student1, activity2);
		admin.addActivityToStudent(student1, activity3);
		admin2.addNewActivity(activity4);
        System.out.println("\nDanh sách sinh viên đăng ký:");
		student1.viewRegisteredActivity();
		
        System.out.println("\nDanh sách sinh viên thấy:");
		student1.print();
		admin.printActivity();
        System.out.println("\nTìm hoạt động dựa theo 1 phần của tên:");
        String keyword = "exa";
        admin.searchbyPartialName(keyword);
        
        System.out.println("\nLọc bằng tiêu chí:");
        String title1 = "HocTap";
        admin.filterbyTitle(title1);
        
        System.out.println("\nXoá toàn bộ hoạt động trong danh sách:");
        admin.removeAll();
        System.out.println("\nDanh sách của Admin:");
		admin.printActivity();
        System.out.println("\nDanh sách của Student:");
		student1.viewRegisteredActivity();

		String StuID = "2024";
		System.out.println("\nTìm kiếm sinh viên:");
		admin.searchStudent(StuID);
	}

}
