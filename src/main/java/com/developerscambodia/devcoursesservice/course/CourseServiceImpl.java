package com.developerscambodia.devcoursesservice.course;

import com.developerscambodia.devcoursesservice.course.web.CourseDto;
import com.developerscambodia.devcoursesservice.course.web.CreateCourseDto;
import com.developerscambodia.devcoursesservice.section.Section;
import com.developerscambodia.devcoursesservice.section.SectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j

public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final MongoTemplate mongoTemplate;
   private final SectionRepository sectionRepository;



    @Override
    public CreateCourseDto createNewCourse(CreateCourseDto createCourseDto) {

        Course course = courseMapper.createCourseDtoToCourse(createCourseDto);
        course.setUuid(UUID.randomUUID().toString());
        courseRepository.save(course);


        return courseMapper.fromCreateCourseDtoToCourse(course);
    }

    @Override
    public Page<CourseDto> findListCourses(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Course> coursePage = courseRepository.findAll(pageable);

        // Convert Page<Course> to Page<CourseDto> using map function
        Page<CourseDto> courseDtoPage = coursePage.map(courseMapper::courseToCourseDto);

        return courseDtoPage;
    }

    /*@Override
    public CourseDto findCourseByUuid(String uuid) {
        Course course = courseRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Course not found with UUID: " + uuid));
        List<Section> sections = course.getSections();

        return courseMapper.courseToCourseDto(course);



    }*/






    @Override
    public CourseDto removeCourseByUuid(String uuid) {
        Course course = courseRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Course not found with UUID: " + uuid));

        courseRepository.delete(course);
        return null;
    }

    @Override
    public CourseDto editCourseByUuid(String uuid, CourseDto updatedCourseDto) {
        Course existingCourse = courseRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Course not found with UUID: " + uuid));

        // Update the existing course with the new data from updatedCourseDto
        existingCourse.setTitle(updatedCourseDto.title());
        existingCourse.setDescription(updatedCourseDto.description());

        // Update other properties as needed...

        // Save the updated course to the repository
        Course updatedCourse = courseRepository.save(existingCourse);

        // Convert the updated Course object to CourseDto using the mapper
        CourseDto updatedCourseDtoResponse = courseMapper.courseToCourseDto(updatedCourse);

        return updatedCourseDtoResponse;
    }

   /* @Override
    public Optional<Course> getCourseBySectionUuid(String sectionUuid) {
       // logger.info("Fetching course by section UUID: {}", sectionUuid);
        Optional<Course> courseOptional = courseRepository.findBySectionsUuid(sectionUuid);
       // courseOptional.ifPresent(course -> logger.info("Found course: {}", course));
        return courseOptional;
    }*/

    @Override
    public List<Section> listAllSectionsByCourseUuid(String uuid) {
        return courseRepository.findSectionsByUuid(uuid);
    }

    @Override
    public CourseDto findCourseByUuid(String uuid) {
        Course course = courseRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Course not found with UUID: " + uuid));
        return courseMapper.courseToCourseDto(course);
    }


//    @Override
//    public Optional<Course> getCourseBySectionUuid(String sectionUuid) {
//        return Optional.empty();
//    }
//    @Override
//
//    public List<Course> filterCoursesByCategory(String categoryName) {
//        // Create a query to filter courses by category name
//        Query query = new Query(Criteria.where("category.name").is(categoryName));
//
//        // Execute the query using MongoTemplate
//        //List<Course> courses = mongoTemplate.find(query, Course.class);
//
//        return mongoTemplate.find(query, Course.class);
//    }

//    @Override
//    public Optional<Course> getCourseBySectionUuid(String sectionUuid) {
//        return courseRepository.findBySectionsUuid(sectionUuid);
//    }



    @Override
    public List<Section> getSectionsByCourseUuid(String uuid) {
        Course course = courseRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Course not found with UUID: " + uuid));

        List<Section> sections = course.getSections();

        // Log the course and sections details
        log.info("Course found: {}", course);
        log.info("Sections associated with the course: {}", sections);

        return sections;
    }



}



