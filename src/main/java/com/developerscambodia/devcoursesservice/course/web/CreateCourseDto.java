package com.developerscambodia.devcoursesservice.course.web;

import com.developerscambodia.devcoursesservice.category.Category;
import com.developerscambodia.devcoursesservice.section.Section;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record CreateCourseDto(
        String uuid,
        String title,
        String categoryUuid,
        BigDecimal price,
        String description,
        String thumbnail,
        Integer discount,

        String contents


) {
}
