package xiaoxueqi.cloudcomputing.entity;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;

/**
 * 单例模式
 * 创建一个DockerClient
 */
public class DockerClientSingleton {

    //DockerClient的配置
    private static DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
            .withDockerHost("tcp://10.251.253.189:2375")
            .build();

    //DockerClient初始化
    private static DockerClient client = DockerClientBuilder.getInstance(config).build();

    private DockerClientSingleton() {

    }

    public static DockerClient getInstance() {
        return client;
    }

}
