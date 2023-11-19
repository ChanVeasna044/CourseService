package com.developerscambodia.devcoursesservice.course.web;

import com.developerscambodia.devcoursesservice.category.Category;
import com.developerscambodia.devcoursesservice.feign.RatingResponse;
import com.developerscambodia.devcoursesservice.section.Section;
import com.developerscambodia.devcoursesservice.section.web.SectionDto;
import lombok.Builder;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record CourseDto(

        String uuid,
        String title,

         String description,
        BigDecimal price,
        Integer discount,
         Boolean isPaid,
        String thumbnail,
        String categoryUuid,

        LocalDateTime createdAt,

        RatingResponse rating





) {



}
