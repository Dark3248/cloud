package xiaoxueqi.cloudcomputing.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xiaoxueqi.cloudcomputing.entity.User;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    User searchByUsername(String username);

    User searchById(int id);

    int register(String username, String password);

    int changePassword(int id, String password);

    List<User> getAllUsers();

}
