public abstract class User {
	private String userID;
	private String userName;
	private String passWord;
	private String name;
	
	public User(String userID, String userName, String passWord, String name) {
		this.userID = userID;
		this.userName = userName;
		this.passWord = passWord;
		this.name = name;
	}
	
	public boolean Login (String inputUserID, String inputUserName, String inputPassWord, String inputName) {
		return this.userID.equals(inputUserID) && this.userName.equals(inputName) &&
				this.passWord.equals(inputPassWord) && this.name.equals(inputName);
	}
	
	public void addActivity () {
		
	}
	
	public void removeActivity() {
		
	}
	
	public void displayActivity() {
		
	}
}