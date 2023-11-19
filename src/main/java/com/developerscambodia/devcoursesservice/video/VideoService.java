package com.developerscambodia.devcoursesservice.video;

import com.developerscambodia.devcoursesservice.course.web.CreateCourseDto;

import java.util.List;

public interface VideoService {
    void createNewVideo(Video video);
    List<Video> getAllVideos();

  //  Video findByUuid(String uuid);
}
