package com.developerscambodia.devcoursesservice.course;

import com.developerscambodia.devcoursesservice.course.web.CourseDto;
import com.developerscambodia.devcoursesservice.course.web.CreateCourseDto;
import com.developerscambodia.devcoursesservice.section.Section;
import com.developerscambodia.devcoursesservice.section.SectionMapper;
import com.developerscambodia.devcoursesservice.section.SectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
   private final SectionMapper sectionMapper;




    //Star
//    @Override
//    public CreateCourseDto createNewCourse(CreateCourseDto createCourseDto) {
//
//        Course course = courseMapper.createCourseDtoToCourse(createCourseDto);
//        course.setUuid(UUID.randomUUID().toString());
//        courseRepository.save(course);
//
//
//        return courseMapper.fromCreateCourseDtoToCourse(course);
//    }

    //end


    @Override
    public void createNewCourse(CreateCourseDto createCourseDto) {

        Course course = courseMapper.createCourseDtoToCourse(createCourseDto);

        // Set a universally unique identifier (UUID) for the course
        course.setUuid(UUID.randomUUID().toString());
        // Create a new Course object by using a mapper to convert the CreateCourseDto
        courseRepository.save(course);
    }

    @Override
    public Page<CourseDto> findListCourses(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);

        // Retrieve a paginated list of courses from the repository using the created PageRequest.
        Page<Course> coursePage = courseRepository.findAll(pageable);

        // Map the Page<Course> to a Page<CourseDto> using the courseMapper.
        return coursePage.map(courseMapper::courseToCourseDto);
    }


    @Override
    public void removeCourseByUuid(String uuid) {
        Course course = courseRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Course not found with UUID: " + uuid));

        courseRepository.delete(course);

    }

    @Override
    public CourseDto editCourseByUuid(String uuid, CourseDto updatedCourseDto) {
        Course existingCourse = courseRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Course not found with UUID: " + uuid));

        // Update the existing course with the new data from updatedCourseDto
        existingCourse.setTitle(updatedCourseDto.title());
        existingCourse.setDescription(updatedCourseDto.description());
        existingCourse.setPrice(updatedCourseDto.price());
        existingCourse.setIsPaid(updatedCourseDto.isPaid());
        existingCourse.setDiscount(updatedCourseDto.discount());

        // Update other properties as needed...

        // Save the updated course to the repository
        Course updatedCourse = courseRepository.save(existingCourse);

        // Convert the updated Course object to CourseDto using the mapper
        CourseDto updatedCourseDtoResponse = courseMapper.courseToCourseDto(updatedCourse);

        return updatedCourseDtoResponse;
    }

    @Override
    public Optional<Course> findCourseByUuidWithSections(String courseUuid) {
        // Retrieve a course from the repository based on its UUID.
        Optional<Course> optionalCourse = courseRepository.findByUuid(courseUuid);

        // Check if the course with the given UUID exists.
        if (optionalCourse.isPresent()) {
            // If the course exists, retrieve its sections from the section repository.
            Course course = optionalCourse.get();
            List<Section> sections = sectionRepository.findByCourseUuid(courseUuid);

            // Set the retrieved sections to the course.
            course.setSections(sections);
            return Optional.of(course);
        } else {
            // If the course does not exist, return an empty Optional.
            return Optional.empty();
        }
    }

    @Override
    public List<Course> getCourseByCategoryUuid(String categoryUuid) {
        //Calls the findByCategoryUuid method from the course repository
        return courseRepository.findByCategoryUuid(categoryUuid);
    }

    @Override
    public BigDecimal calculateDiscountedPrice(Course course) {
        // Check if the course is not null, it is paid, and has a discount
        if (course != null && Boolean.TRUE.equals(course.getIsPaid()) && course.getDiscount() != null) {
            // Calculate the discount multiplier based on the percentage discount
            BigDecimal discountMultiplier = BigDecimal.valueOf(1 - (course.getDiscount().doubleValue() / 100.0));

            // Multiply the original price by the discount multiplier to get the discounted price
            return course.getPrice().multiply(discountMultiplier);
        } else {
            // If any of the conditions fail, return the original price or zero if the course is null
            return course != null ? course.getPrice() : BigDecimal.ZERO;
        }
    }


}



