package com.developerscambodia.devcoursesservice.course.web;

import com.developerscambodia.devcoursesservice.base.BaseApi;
import com.developerscambodia.devcoursesservice.base.BaseError;
import com.developerscambodia.devcoursesservice.course.Course;
import com.developerscambodia.devcoursesservice.course.CourseMapper;
import com.developerscambodia.devcoursesservice.course.CourseRepository;
import com.developerscambodia.devcoursesservice.course.CourseService;
import com.developerscambodia.devcoursesservice.section.Section;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    private final CourseService courseService;
    private final CourseRepository courseRepository;

    @PostMapping
    public ResponseEntity<String> createCourse(@RequestBody CreateCourseDto createCourseDto) {
        courseService.createNewCourse(createCourseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Course created successfully");
    }
@GetMapping
public BaseApi<Page<CourseDto>> getListOfCourses(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

    Page<CourseDto> courseDtoPage = courseService.findListCourses(page, size);

    BaseApi<Page<CourseDto>> course = BaseApi.<Page<CourseDto>>builder()
            .message("Find List Success")
            .code(HttpStatus.OK.value())
            .status(true)
            .timeStamp(LocalDateTime.now())
            .data(courseDtoPage)
            .build();

    return course;
}

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> removeCourseByUuid(@PathVariable String uuid) {
            courseService.removeCourseByUuid(uuid);
            return new ResponseEntity<>("Course with UUID " + uuid + " has been successfully removed", HttpStatus.OK);

    }


    @PutMapping("/{uuid}")
    public BaseApi<?> editCourseByUuid(@PathVariable String uuid, @RequestBody CourseDto updatedCourseDto) {
        CourseDto updatedCourse = courseService.editCourseByUuid(uuid, updatedCourseDto);
            return BaseApi.builder()
                    .status(true)
                    .code(HttpStatus.OK.value())
                    .message("Course Has Been update Successfully")
                    .timeStamp(LocalDateTime.now())
                    .data(updatedCourse)
                    .build();

    }

//    @GetMapping("/{uuid}")
//    public ResponseEntity<?> getCourseByUuidWithSections(@PathVariable String uuid) {
//        Optional<Course> optionalCourse = courseService.findCourseByUuidWithSections(uuid);
//
//        if (optionalCourse.isPresent()) {
//            return ResponseEntity.ok(optionalCourse.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


    @GetMapping("/{uuid}")
    public BaseApi<Course> getCourseByUuidWithSections(@PathVariable String uuid) {
        Optional<Course> optionalCourse = courseService.findCourseByUuidWithSections(uuid);

            Course course = optionalCourse.get();
            return BaseApi.<Course>builder()
                    .message("Course found successfully")
                    .code(HttpStatus.OK.value())
                    .status(true)
                    .timeStamp(LocalDateTime.now())
                    .data(course)
                    .build();

    }



    @GetMapping("/category/{categoryUuid}")
    public List<Course> getCategoryByCourseUuid(@PathVariable String categoryUuid) {
        return courseService.getCourseByCategoryUuid(categoryUuid);
    }

    @GetMapping("/{courseUuid}/discounted-price")
    public ResponseEntity<BigDecimal> getDiscountedPrice(@PathVariable String courseUuid) {
        // Assuming you have a method to retrieve a course by UUID from your repository
        Course course = courseRepository.findByUuid(courseUuid).orElse(null);

        if (course != null) {
            BigDecimal discountedPrice = courseService.calculateDiscountedPrice(course);
            return ResponseEntity.ok(discountedPrice);
        } else {
            // Handle course not found
            return ResponseEntity.notFound().build();
        }
    }





}






