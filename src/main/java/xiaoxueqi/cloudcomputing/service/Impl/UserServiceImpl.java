package xiaoxueqi.cloudcomputing.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoxueqi.cloudcomputing.dao.CourseDao;
import xiaoxueqi.cloudcomputing.dao.UserDao;
import xiaoxueqi.cloudcomputing.entity.User;
import xiaoxueqi.cloudcomputing.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    CourseDao courseDao;

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

    @Override
    public List<User> getAllUsers(int id) {
        List<User> all = userDao.getAllUsers();
        List<Integer> exist = courseDao.getStudentsByCid(id);
        Set<Integer> set = new HashSet<>(exist);
        List<User> res = new ArrayList<>();
        for(User user : all) {
            if(!set.isEmpty() && set.contains(user.getId()))
                continue;
            res.add(user);
        }
        return res;
    }

    @Override
    public List<User> getAllUsers2(int id) {
        List<User> all = userDao.getAllUsers();
        List<Integer> exist = courseDao.getStudentsByCid(id);
        Set<Integer> set = new HashSet<>(exist);
        List<User> res = new ArrayList<>();
        for(User user : all) {
            if(!set.isEmpty() && set.contains(user.getId()))
                res.add(user);
        }
        return res;
    }
}
