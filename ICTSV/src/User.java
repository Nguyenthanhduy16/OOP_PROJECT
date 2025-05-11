public abstract class User {
	private String userID;
	private String userName;
	private String passWord;

	public User(String userID, String userName, String passWord) {
		this.userID = userID;
		this.userName = userName;
		this.passWord = passWord;
	}

	public boolean Login(String inputUserName, String inputPassWord) {
		return this.userName.equals(inputName) && this.passWord.equals(inputPassWord);
	}

	public String getUserID() {
		return userID;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}