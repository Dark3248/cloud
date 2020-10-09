package xiaoxueqi.cloudcomputing.entity;

import com.github.dockerjava.api.model.Container;

import java.util.List;

public class User {

    int id;
    String username;
    String password;
    //权限，0为管理员，1为用户
    Byte type;
    //用户拥有的容器
    List<Container> containers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", containers=" + containers +
                '}';
    }
}
