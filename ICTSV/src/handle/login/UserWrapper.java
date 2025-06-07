package handle.login;

import java.util.List;

import entity.User;

public class UserWrapper {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
