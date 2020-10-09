package xiaoxueqi.cloudcomputing.service;

import xiaoxueqi.cloudcomputing.entity.User;

import java.util.List;

public interface UserService {

    boolean register(String username, String password);

    User login(String username, String password);

    boolean changePassword(int id, String password);

    boolean judge(int id, String password);

    List<User> getAllUsers(int id);

    List<User> getAllUsers2(int id);

}
