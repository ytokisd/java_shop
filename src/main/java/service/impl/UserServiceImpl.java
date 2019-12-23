package service.impl;


import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import service.UserService;
import shared.ResponseStatus;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public ResponseStatus<User> login(String username, String password) {
        if(username == null || password == null)
            return new ResponseStatus<>(false, "empty username/password", null) ;
        //go to db, if there user with such login/password
        User user = userDao.getByUsername(username);
        if(user == null)
            return new ResponseStatus<>(false, "no user with such username ", null) ;

        if(!password.equals(user.getPassword()))
            return new ResponseStatus<>(false, "Passwords didn't match", null) ;

        return new ResponseStatus<>(true, "Success", user);
    }

    @Override
    public ResponseStatus<User> register(String username, String password, String phoneNumber) {
        if(username == null || password == null)
            return new ResponseStatus<>(false, "empty username/password", null);

        User user = userDao.getByUsername(username);
        if(user != null)
            return new ResponseStatus<>(false, "user with such username already exists", null) ;
        User u = new User(username, password);
        u.setPhoneNumber(phoneNumber);
        userDao.saveUser(u);

        return new ResponseStatus<>(true, "Success", u);
    }
}
