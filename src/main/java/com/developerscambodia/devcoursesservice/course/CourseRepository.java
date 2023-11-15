package com.developerscambodia.devcoursesservice.course;

import com.developerscambodia.devcoursesservice.section.Section;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends MongoRepository<Course,String> {
    Page<Course> findAll(Pageable pageable);

    Optional<Course> findByUuid(String uuid);
   // List<Course> findByCategoryName(String categoryName);
  //  Optional<Course> findBySectionsUuid( String sectionUuid);
//   Optional<Course> findBySectionsUuid(String sectionUuid);
    List<Section> findSectionsByUuid(String uuid);



}
