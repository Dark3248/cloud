package xiaoxueqi.cloudcomputing.dto;

import java.util.List;

public class CreateContainerRequest {

    String image;
    String name;
    List<Integer> ports;
    int isTerminalOpen;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPorts() {
        return ports;
    }

    public void setPorts(List<Integer> ports) {
        this.ports = ports;
    }

    public int getIsTerminalOpen() {
        return isTerminalOpen;
    }

    public void setIsTerminalOpen(int isTerminalOpen) {
        this.isTerminalOpen = isTerminalOpen;
    }

    @Override
    public String toString() {
        return "CreateContainerRequest{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", ports=" + ports +
                ", isTerminalOpen=" + isTerminalOpen +
                '}';
    }
}
