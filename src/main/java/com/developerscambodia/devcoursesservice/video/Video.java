package com.developerscambodia.devcoursesservice.video;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("video")
public class Video {

    private String id;
    private String uuid;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
