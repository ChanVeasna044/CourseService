package com.developerscambodia.devcoursesservice.feign;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class VideoResponse {

    private String id;
    private String uuid;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
