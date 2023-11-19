package com.developerscambodia.devcoursesservice.section.web;

import com.developerscambodia.devcoursesservice.video.Video;
import lombok.Builder;

import java.util.List;



@Builder

public record SectionDto(
        String uuid,
        String name,
        String courseUuid,
        List<Video> videos

) {
}