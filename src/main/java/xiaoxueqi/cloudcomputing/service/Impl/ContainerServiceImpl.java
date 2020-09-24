package xiaoxueqi.cloudcomputing.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoxueqi.cloudcomputing.dao.ContainerDao;
import xiaoxueqi.cloudcomputing.dao.UserDao;
import xiaoxueqi.cloudcomputing.entity.User;
import xiaoxueqi.cloudcomputing.service.ContainerService;

import java.util.List;

@Service
public class ContainerServiceImpl implements ContainerService {

    @Autowired
    ContainerDao containerDao;

    @Autowired
    UserDao userDao;

    @Override
    public void createContainer(String name, String image, int uid, String port, String cid) {
        User user = userDao.searchById(uid);
        containerDao.createContainer(name, image, uid, user.getUsername(), port, cid);
    }

    @Override
    public boolean removeContainer(String id) {
        return containerDao.removeContainer(id) > 0;
    }

    @Override
    public List<String> getContainersByUid(int id) {
        return containerDao.getCidByUid(id);
    }

    @Override
    public String getUsernameByCid(String cid) {
        return containerDao.searchUsernameByCid(cid);
    }
}
