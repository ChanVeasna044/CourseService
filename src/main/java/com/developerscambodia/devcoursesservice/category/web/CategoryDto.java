package com.developerscambodia.devcoursesservice.category.web;

import com.developerscambodia.devcoursesservice.course.Course;

import java.util.List;

public record CategoryDto(

         String uuid,
        String name, List<Course> courses

) {
}
