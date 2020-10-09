package xiaoxueqi.cloudcomputing.dto;

import java.util.List;

public class AddStudentsRequest {

    List<Integer> students;

    public List<Integer> getStudents() {
        return students;
    }

    public void setStudents(List<Integer> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "AddStudentsRequest{" +
                "students=" + students +
                '}';
    }
}
