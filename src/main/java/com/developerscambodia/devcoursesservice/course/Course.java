package com.developerscambodia.devcoursesservice.course;

import com.developerscambodia.devcoursesservice.category.Category;
import com.developerscambodia.devcoursesservice.feign.InstructorResponse;
import com.developerscambodia.devcoursesservice.feign.RatingResponse;
import com.developerscambodia.devcoursesservice.section.Section;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.CascadeType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "courseDB")
@Builder

public class Course {

    @Id
    private String id;

    private String uuid;
    private String title;
    private String description;

    private String thumbnail;
    private Boolean isPaid;
    private BigDecimal price;
    private Integer discount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isDrafted;
    private String code;
    private RatingResponse rating;
    private InstructorResponse instructorId;


    private List<Section> sections;
    private  String categoryUuid;
    private String contents;
}


