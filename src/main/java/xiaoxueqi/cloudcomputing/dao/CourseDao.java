package xiaoxueqi.cloudcomputing.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xiaoxueqi.cloudcomputing.entity.Course;

import java.util.List;

@Mapper
@Repository
public interface CourseDao {

    int createCourse(Course course);

    Course searchByCourseName(String name);

    Course searchByCourseId(int id);

    int addStudent(int cid, int uid);

    List<Course> getAllCourses();

    List<Integer> getStudentsByCid(int cid);

    List<Integer> getCoursesByUid(int uid);

}
