package xiaoxueqi.cloudcomputing.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ContainerDao {

    int createContainer(String name, String image, int uid, String username, String port, String cid);

    int createContainer2(String name, String image, int uid, String username, String port, String cid, int courseId);

    int removeContainer(String cid);

    List<String> getCidByUid(int uid);

    List<String> getCidByCourse(int cid);

    String searchUsernameByCid(String cid);

    List<String> getCidByUidAndCourse(int uid, int courseId);

}
