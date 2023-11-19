package com.developerscambodia.devcoursesservice.video;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    @Override
    public void createNewVideo(Video video) {
        video.setCreateAt(LocalDateTime.now());
        video.setUpdateAt(LocalDateTime.now());

        // Use the repository to save the video
        videoRepository.save(video);
    }

    @Override
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

//    @Override
//    public Video findByUuid(String uuid) {
//        return videoRepository.
//    }


}
