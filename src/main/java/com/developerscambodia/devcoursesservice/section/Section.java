package com.developerscambodia.devcoursesservice.section;

import com.developerscambodia.devcoursesservice.course.Course;
import com.developerscambodia.devcoursesservice.video.Video;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "sectionDB")
@Builder

public class Section {
    @Id
    private String id;

    private String uuid;
    private String name;
    private Boolean isDeleted;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private List<Video> videos;
    private String courseUuid;
//    @DBRef(lazy = false)
   private Course course;


}