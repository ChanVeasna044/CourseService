package com.developerscambodia.devcoursesservice.category;

import com.developerscambodia.devcoursesservice.course.Course;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "categoryDB")
public class Category {


    private String id;
    private String uuid;
    private String name;
//    @DBRef
    private List<Course> courses;
}
