package xiaoxueqi.cloudcomputing.controller;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xiaoxueqi.cloudcomputing.dto.AddStudentsRequest;
import xiaoxueqi.cloudcomputing.dto.CreateContainerRequest;
import xiaoxueqi.cloudcomputing.entity.Container;
import xiaoxueqi.cloudcomputing.entity.Course;
import xiaoxueqi.cloudcomputing.entity.DockerClientSingleton;
import xiaoxueqi.cloudcomputing.service.ContainerService;
import xiaoxueqi.cloudcomputing.service.CourseService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    ContainerService containerService;

    DockerClient client = DockerClientSingleton.getInstance();

    /**
     * 创建课程
     *
     * @param course String name, String info, String teacher
     */
    @PostMapping("/create")
    public String createCourse(@RequestBody Course course) {
        if(courseService.createCourse(course)) {
            return "success";
        }
        return "fail";
    }

    /**
     * 添加学生
     *
     * @param cid 课程id
     * @param request 学生id list
     */
    @PostMapping("/addStudents/{cid}")
    public String addStudent(@PathVariable int cid,
                             @RequestBody AddStudentsRequest request) {
        if(courseService.addStudents(cid, request.getStudents()))
            return "success";
        return "fail";
    }

    /**
     * 获取全部课程
     *
     * @return 课程列表
     */
    @GetMapping("/getAll")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    /**
     * 获取某课程
     *
     * @param cid 课程id
     * @return 课程信息
     */
    @GetMapping("/{cid}")
    public Course getCourse(@PathVariable int cid) {
        return courseService.getCourseById(cid);
    }


    /**
     * 为课程中的学生创建容器
     *
     * @param request 容器信息
     * @param courseId 课程id
     * @return success
     */
    @PostMapping("/createContainer/{courseId}")
    public String createContainer(@RequestBody CreateContainerRequest request,
                                  @PathVariable int courseId) {
        List<Integer> students = courseService.getStudentsByCid(courseId);
        for(Integer i : students) {
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
                        .withName(request.getName() + "_" + i)
                        .withHostConfig(new HostConfig().withPortBindings(portBindings).withPrivileged(true))
                        .withExposedPorts(exposedPorts)
                        .withEntrypoint("/sbin/init")
                        .exec();
            } else {
                response = client.createContainerCmd(request.getImage())
                        .withName(request.getName() + "_" + i)
                        .withHostConfig(new HostConfig().withPortBindings(portBindings))
                        .withExposedPorts(exposedPorts)
                        .exec();
            }

            //向数据库中插入容器信息
            containerService.createContainer2(request.getName() + "_" + i, request.getImage(), i, port_string.toString(), response.getId(), courseId);
        }

        return "success";
    }

    /**
     * 通过学生id获取课程
     *
     * @param uid 学生id
     * @return 课程列表
     */
    @GetMapping("/student/{uid}")
    public List<Course> getCourseByUid(@PathVariable int uid) {
        return courseService.getCoursesByUid(uid);
    }

}
