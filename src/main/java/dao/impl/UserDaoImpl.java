package dao.impl;


import dao.UserDao;
import model.User;
import model.UserRole;

import java.util.HashMap;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    private static Map<String,User> users = new HashMap<>();
    static {
        users.put("admin", new User("admin", "admin", UserRole.ADMIN));
    }


    public User getByUsername(String username) {
        return users.get(username);
    }

    @Override
    public void saveUser(User user) {
        users.put(user.getUsername(), user);
    }
}
