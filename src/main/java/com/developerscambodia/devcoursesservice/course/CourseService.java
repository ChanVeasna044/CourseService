package com.developerscambodia.devcoursesservice.course;

import com.developerscambodia.devcoursesservice.course.web.CourseDto;
import com.developerscambodia.devcoursesservice.course.web.CreateCourseDto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CourseService {



    void createNewCourse(CreateCourseDto createCourseDto);
    Page<CourseDto> findListCourses(int page, int size);
//    CourseDto findCourseByUuidWithSections(String uuid);



    void removeCourseByUuid(String uuid);

    CourseDto editCourseByUuid(String uuid, CourseDto updatedCourseDto);
    Optional<Course> findCourseByUuidWithSections(String courseUuid);

    List<Course> getCourseByCategoryUuid(String categoryUuid);

    BigDecimal calculateDiscountedPrice(Course course);


}
