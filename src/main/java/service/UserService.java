package service;


import model.User;
import shared.ResponseStatus;

public interface UserService {

    /**
     * Used to login a user
     * @param username user name
     * @param password user password
     * @return outcome of login - success or not
     */
    ResponseStatus<User> login(String username, String password);

    ResponseStatus register(String username, String password, String phoneNumber);
}
