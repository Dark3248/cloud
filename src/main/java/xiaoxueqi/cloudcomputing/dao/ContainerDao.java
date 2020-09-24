package xiaoxueqi.cloudcomputing.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ContainerDao {

    int createContainer(String name, String image, int uid, String username, String port, String cid);

    int removeContainer(String cid);

    List<String> getCidByUid(int uid);

    String searchUsernameByCid(String cid);

}
