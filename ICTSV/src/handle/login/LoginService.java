package handle.login;

import model.Admin;
import model.Student;
import model.User;

public class LoginService {
    private LoginHandle loginHandle;

    public LoginService(LoginHandle loginHandle) {
        this.loginHandle = loginHandle;
    }

    public User authenticate(String username, String password, String selectedRole) {
        for (User user : loginHandle.getUsers()) {
            if (user.getUserName().equals(username) && user.getPassWord().equals(password)) {
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

