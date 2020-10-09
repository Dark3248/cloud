package xiaoxueqi.cloudcomputing.service;

import java.util.List;

public interface ContainerService {

    void createContainer(String name, String image, int uid, String port, String cid);

    void createContainer2(String name, String image, int uid, String port, String cid, int courseId);

    boolean removeContainer(String id);

    List<String> getContainersByUid(int id);

    List<String> getContainersByCourse(int id);

    String getUsernameByCid(String cid);

    List<String> getContainersByUidAndCourse(int uid, int courseId);

}
