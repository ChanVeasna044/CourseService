package com.developerscambodia.devcoursesservice.course;

import com.developerscambodia.devcoursesservice.course.web.CourseDto;
import com.developerscambodia.devcoursesservice.course.web.CreateCourseDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Course courseDtoToCourse(CourseDto courseDto);


    CourseDto courseToCourseDto(Course course);
    Course createCourseDtoToCourse(CreateCourseDto createCourseDto);

    CreateCourseDto fromCreateCourseDtoToCourse(Course course);


}