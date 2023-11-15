package com.developerscambodia.devcoursesservice.section.web;

import com.developerscambodia.devcoursesservice.course.Course;
import com.developerscambodia.devcoursesservice.feign.VideoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder


public record SectionDto(
        String uuid,
        String name,
       Course course
       // List<VideoResponse> videos
) {
}
