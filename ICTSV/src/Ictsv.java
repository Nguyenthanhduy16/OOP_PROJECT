
public class Ictsv {

	public static void main(String[] args) {
		Admin admin = new Admin("2000", "Admin01", "01234");

		Activity activity1 = new Activity("HocTap", "Example1", false, 10);
		Activity activity2 = new Activity("YThuc", "Example2", false, 20);

		admin.addNewActivity(activity1);
		admin.addNewActivity(activity2);

		// Truyền admin vào student
		Student student1 = new Student("2023", "Student1", "1234", admin);

		student1.print();
	}

}
