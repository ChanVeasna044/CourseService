package com.developerscambodia.devcoursesservice.section;

import com.developerscambodia.devcoursesservice.course.Course;
import com.developerscambodia.devcoursesservice.feign.VideoResponse;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "sectionDB")
@Builder

public class Section {
    @Id
    private String uuid;
    private String name;
    private Boolean isDeleted;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private List<VideoResponse> videos;

    @DBRef
    private Course course;





}