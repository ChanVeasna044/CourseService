package com.developerscambodia.devcoursesservice.course.web;

import com.developerscambodia.devcoursesservice.category.Category;
import com.developerscambodia.devcoursesservice.feign.RatingResponse;
import com.developerscambodia.devcoursesservice.section.Section;
import com.developerscambodia.devcoursesservice.section.web.SectionDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record CourseDto(

        String uuid,
        String title,
        List<Section> sections,
         Category category,
         String description,
        BigDecimal price,
        String thumbnail,

        LocalDateTime createdAt,

        RatingResponse rating



   //  List<Section>sectionId








) {

}
