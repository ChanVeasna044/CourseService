package com.developerscambodia.devcoursesservice.course;

import com.developerscambodia.devcoursesservice.section.Section;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends MongoRepository<Course,String> {
//    Page<Course> findAll(Pageable pageable);

    Optional<Course> findByUuid(String uuid);

    List<Course> findByCategoryUuid(String categoryUuid);







}
