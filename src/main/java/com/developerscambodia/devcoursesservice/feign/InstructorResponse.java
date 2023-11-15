package com.developerscambodia.devcoursesservice.feign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document
@AllArgsConstructor
@NoArgsConstructor
public class InstructorResponse {
  private  String profileImages;
  private String fullName;
  private String gitLink;
  private String youtubeChannel;

}
