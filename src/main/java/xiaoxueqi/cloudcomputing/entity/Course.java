package xiaoxueqi.cloudcomputing.entity;

import java.util.List;

public class Course {

    int id;
    String name;
    String info;
    String teacher;
    List<User> students;
    List<Container> containers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", teacher='" + teacher + '\'' +
                ", students=" + students +
                ", containers=" + containers +
                '}';
    }
}
