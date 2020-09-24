package xiaoxueqi.cloudcomputing.service;

import xiaoxueqi.cloudcomputing.entity.User;

public interface UserService {

    boolean register(String username, String password);

    User login(String username, String password);

    boolean changePassword(int id, String password);

    boolean judge(int id, String password);

}
