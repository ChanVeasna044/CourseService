package com.developerscambodia.devcoursesservice.section.web;

import com.developerscambodia.devcoursesservice.course.Course;
import lombok.Builder;

@Builder
public record CreateSectionDto(
        String uuid,
        String name,
        String courseUuid
) {
}
