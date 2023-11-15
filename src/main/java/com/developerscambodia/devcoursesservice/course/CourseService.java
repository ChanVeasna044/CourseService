package com.developerscambodia.devcoursesservice.course;

import com.developerscambodia.devcoursesservice.course.web.CourseDto;
import com.developerscambodia.devcoursesservice.course.web.CreateCourseDto;
import com.developerscambodia.devcoursesservice.section.Section;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CourseService {



    CreateCourseDto createNewCourse(CreateCourseDto createCourseDto);
    Page<CourseDto> findListCourses(int page, int size);



    CourseDto removeCourseByUuid(String uuid);

    CourseDto editCourseByUuid(String uuid, CourseDto updatedCourseDto);



    List<Section> listAllSectionsByCourseUuid(String uuid);
    CourseDto findCourseByUuid(String uuid);
    List<Section> getSectionsByCourseUuid(String uuid);





}
