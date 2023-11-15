package com.developerscambodia.devcoursesservice.section;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends MongoRepository<Section,String> {
    Optional<Section> findByUuid(String uuid);



//    List<Section> findByCourse_Id(String courseId);

Page<Section> findSectionsByCourseUuid(String courseUuid, PageRequest pageable);



    List<Section> findCourseByUuid(String uuid);

}
