package xiaoxueqi.cloudcomputing.service;

import java.util.List;

public interface ContainerService {

    void createContainer(String name, String image, int uid, String port, String cid);

    boolean removeContainer(String id);

    List<String> getContainersByUid(int id);

    String getUsernameByCid(String cid);

}
