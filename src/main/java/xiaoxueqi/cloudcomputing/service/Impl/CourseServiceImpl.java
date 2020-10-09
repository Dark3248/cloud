package xiaoxueqi.cloudcomputing.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoxueqi.cloudcomputing.dao.CourseDao;
import xiaoxueqi.cloudcomputing.entity.Course;
import xiaoxueqi.cloudcomputing.entity.User;
import xiaoxueqi.cloudcomputing.service.CourseService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseDao courseDao;

    @Override
    public boolean createCourse(Course course) {
        if(courseDao.searchByCourseName(course.getName()) != null) {
            return false;
        }
        return courseDao.createCourse(course) > 0;
    }

    @Override
    public boolean addStudents(int cid, List<Integer> students) {
        if(courseDao.searchByCourseId(cid) == null) {
            return false;
        }
        for(Integer user : students) {
            courseDao.addStudent(cid, user);
        }
        return true;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    @Override
    public Course getCourseById(int id) {
        return courseDao.searchByCourseId(id);
    }

    @Override
    public List<Integer> getStudentsByCid(int cid) {
        return courseDao.getStudentsByCid(cid);
    }

    @Override
    public List<Course> getCoursesByUid(int uid) {
        List<Integer> courses = courseDao.getCoursesByUid(uid);
        List<Course> res = new ArrayList<>();
        for(Integer i : courses) {
            res.add(courseDao.searchByCourseId(i));
        }
        return res;
    }
}
