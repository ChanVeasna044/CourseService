package com.developerscambodia.devcoursesservice.course.web;

import com.developerscambodia.devcoursesservice.category.Category;
import com.developerscambodia.devcoursesservice.section.Section;
import lombok.Builder;

import java.util.List;

@Builder
public record CreateCourseDto(
        String uuid,
        String title,
        List<Section> sections,
//        Category category,
        String description,
        String thumbnail,
        String contents

) {
}
