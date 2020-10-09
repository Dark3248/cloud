package xiaoxueqi.cloudcomputing.service;

import xiaoxueqi.cloudcomputing.entity.Course;
import xiaoxueqi.cloudcomputing.entity.User;

import java.util.List;

public interface CourseService {

    boolean createCourse(Course course);

    boolean addStudents(int cid, List<Integer> students);

    List<Course> getAllCourses();

    Course getCourseById(int id);

    List<Integer> getStudentsByCid(int cid);

    List<Course> getCoursesByUid(int uid);

}
