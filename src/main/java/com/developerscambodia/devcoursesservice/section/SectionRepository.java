package com.developerscambodia.devcoursesservice.section;

import com.developerscambodia.devcoursesservice.course.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends MongoRepository<Section,String> {

    List<Section> findByCourse(Course course);
    Optional<Section> findByUuid(String uuid);
    List<Section> findByCourseId(String courseId);

    List<Section> findByCourseUuid(String courseUuid);





    Page<Section> findByCourseUuid(String courseUuid, Pageable pageable);




//    List<Section> findByCourse_Id(String courseId);

Page<Section> findSectionsByCourseUuid(String courseUuid, PageRequest pageable);



    List<Section> findCourseByUuid(String uuid);

}
