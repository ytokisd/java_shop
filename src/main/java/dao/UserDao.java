package dao;


import model.User;

public interface UserDao {
    User getByUsername(String username);
    void saveUser(User user);
}
