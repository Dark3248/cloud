package xiaoxueqi.cloudcomputing.controller;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xiaoxueqi.cloudcomputing.dto.CreateContainerRequest;
import xiaoxueqi.cloudcomputing.entity.DockerClientSingleton;
import xiaoxueqi.cloudcomputing.service.ContainerService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/containers")
public class ContainerController {

    /**
     * 获取client
     *
     */
    DockerClient client = DockerClientSingleton.getInstance();

    @Autowired
    ContainerService containerService;

    /**
     * 获取虚拟机中的所有容器
     *
     * @return 容器列表
     */
    @GetMapping("/all")
    public List<Container> getAllContainers() {
        return client.listContainersCmd()
                .withShowAll(true)
                .exec();
    }

    /**
     * 根据用户id获取虚拟机中的容器
     *
     * @param id 用户id
     * @return 容器列表
     */
    @GetMapping("/{id}")
    public List<Container> getContainers(@PathVariable int id) {
        List<String> cid = containerService.getContainersByUid(id);
        if(cid.size() == 0)
            return null;
        return client.listContainersCmd()
                .withShowAll(true)
                .withIdFilter(cid)
                .exec();
    }

    /**
     * 根据课程id获取虚拟机中的容器
     *
     * @param id 课程id
     * @return 容器列表
     */
    @GetMapping("/course/{id}")
    public List<Container> getCourseContainers(@PathVariable int id) {
        List<String> cid = containerService.getContainersByCourse(id);
        if(cid.size() == 0)
            return null;
        return client.listContainersCmd()
                .withShowAll(true)
                .withIdFilter(cid)
                .exec();
    }

    /**
     * 根据课程id和学生id获取虚拟机中的容器
     *
     * @param cid 课程id uid 学生id
     * @return 容器列表
     */
    @GetMapping("/course/{cid}/{uid}")
    public List<Container> getCourseContainers(@PathVariable int cid,
                                               @PathVariable int uid) {
        List<String> list = containerService.getContainersByUidAndCourse(uid, cid);
        if(list.size() == 0)
            return null;
        return client.listContainersCmd()
                .withShowAll(true)
                .withIdFilter(list)
                .exec();
    }

    /**
     * 创建容器
     *
     * @param request 发送的请求，包括 image, name, ports
     * @return CreateContainerResponse 创建容器的结果
     */
    @PostMapping("/create/{id}")
    public CreateContainerResponse createContainer(@RequestBody CreateContainerRequest request,
                                                   @PathVariable int id) {
        CreateContainerResponse response;
        //如果参数为空，则返回null
        if(request.getImage() == null || request.getName() == null || request.getPorts() == null) {
            return null;
        }

        List<ExposedPort> exposedPorts = new ArrayList<>();
        StringBuilder port_string = new StringBuilder();
        List<PortBinding> portBindings = new ArrayList<>();

        //绑定端口号：虚拟机随机端口 -> 需要暴露的端口
        for(Integer port : request.getPorts()) {
            //需要暴露的端口号
            ExposedPort exposedPort = ExposedPort.tcp(port);
            exposedPorts.add(exposedPort);
            //随机映射
            portBindings.add(new PortBinding(Ports.Binding.empty(), exposedPort));

            port_string.append(port).append(",");
        }

        //执行命令
        if(request.getIsTerminalOpen() == 1) {
            response = client.createContainerCmd(request.getImage())
                    .withName(request.getName())
                    .withHostConfig(new HostConfig().withPortBindings(portBindings).withPrivileged(true))
                    .withExposedPorts(exposedPorts)
                    .withEntrypoint("/sbin/init")
                    .exec();
        } else {
            response = client.createContainerCmd(request.getImage())
                    .withName(request.getName())
                    .withHostConfig(new HostConfig().withPortBindings(portBindings))
                    .withExposedPorts(exposedPorts)
                    .exec();
        }

        //向数据库中插入容器信息
        containerService.createContainer(request.getName(), request.getImage(), id, port_string.toString(), response.getId());

        return response;
    }

    /**
     * 启动容器
     *
     * @param id 容器id
     * @return true: 启动成功
     */
    @GetMapping("/start/{id}")
    public boolean startContainer(@PathVariable String id) {
        client.startContainerCmd(id).exec();
        return true;
    }

    /**
     * 停止容器
     *
     * @param id 容器id
     * @return true: 停止成功
     */
    @GetMapping("/stop/{id}")
    public boolean stopContainer(@PathVariable String id) {
        client.stopContainerCmd(id).exec();
        return true;
    }

    /**
     * 删除容器
     *
     * @param id 容器id
     * @return true: 删除成功, false: 删除失败
     */
    @GetMapping("/remove/{id}")
    public boolean removeContainer(@PathVariable String id) {
        System.out.println(id);
        if(containerService.removeContainer(id)) {
            client.removeContainerCmd(id).exec();
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/log/{id}")
    public void func(@PathVariable String id) {

    }

}
