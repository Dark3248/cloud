package xiaoxueqi.cloudcomputing.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoxueqi.cloudcomputing.dao.UserDao;
import xiaoxueqi.cloudcomputing.entity.User;
import xiaoxueqi.cloudcomputing.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    /**
     *
     * @param username 注册的用户名
     * @param password 注册的密码
     * @return false 数据库中存在该用户名, true 创建成功
     */
    @Override
    public boolean register(String username, String password) {
        if(userDao.searchByUsername(username) != null) {
            return false;
        }
        return userDao.register(username, password) != 0;
    }

    /**
     *
     * @param username 登录的用户名
     * @param password 登录的密码
     * @return null 密码不正确或用户名不存在, not null 登录成功
     */
    @Override
    public User login(String username, String password) {
        User user = userDao.searchByUsername(username);
        if(user == null)
            return null;
        if(!user.getPassword().equals(password))
            return null;
        return user;
    }

    @Override
    public boolean changePassword(int id, String password) {
        return userDao.changePassword(id, password) > 0;
    }

    @Override
    public boolean judge(int id, String password) {
        return userDao.searchById(id).getPassword().equals(password);
    }
}
