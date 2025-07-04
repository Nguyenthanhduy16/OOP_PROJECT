package handle.login;

import entity.Admin;
import entity.Student;
import entity.User;

public class LoginService {
    private LoginHandle loginHandle;

    public LoginService(LoginHandle loginHandle) {
        this.loginHandle = loginHandle;
    }

    public User authenticate(String username, String password, String selectedRole) {
        for (User user : loginHandle.loadUsers()) {  // <-- load lại từ file MỖI LẦN
            if (user. getUserID().equals(username) && user.getPassWord().equals(password)) {
                if (selectedRole.equalsIgnoreCase("admin") && user instanceof Admin) {
                    return user;
                } else if (selectedRole.equalsIgnoreCase("student") && user instanceof Student) {
                    return user;
                }
            }
        }
        return null;
    }

}

