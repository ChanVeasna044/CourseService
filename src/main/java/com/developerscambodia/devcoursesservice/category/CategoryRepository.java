package com.developerscambodia.devcoursesservice.category;

import com.developerscambodia.devcoursesservice.course.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category,String> {
    Optional<Category> findByUuid(String uuid);
    List<Category> findByCoursesUuid(String courseUuid);



}
