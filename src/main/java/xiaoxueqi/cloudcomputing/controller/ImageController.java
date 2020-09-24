package xiaoxueqi.cloudcomputing.controller;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.PullImageResultCallback;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.model.Image;
import org.springframework.web.bind.annotation.*;
import xiaoxueqi.cloudcomputing.entity.DockerClientSingleton;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/images")
public class ImageController {

    DockerClient client = DockerClientSingleton.getInstance();

    /**
     * 获取虚拟机中所有镜像
     *
     * @return 镜像列表
     */
    @GetMapping("/all")
    public List<Image> getAllImages() {
        return client.listImagesCmd().exec();
    }

    /**
     * 从docker hub中pull镜像
     *
     * @param imageName 镜像名
     * @param tag 镜像的tag
     */
    @PostMapping("/pull")
    public String pullImage(@RequestParam String imageName,
                            @RequestParam String tag) throws InterruptedException {

        if(client.listImagesCmd().withImageNameFilter(imageName + ":" + tag).exec().size() == 1)
            return "already exist";
        if(imageName == null || imageName.length() == 0)
            return "fail";
        try {
            client.pullImageCmd(imageName).withTag(tag).exec(new PullImageResultCallback()).awaitCompletion();
        } catch (NotFoundException e) {
            e.printStackTrace();
            return "not found";
        }
        return "success";
    }

}
