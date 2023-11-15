package com.developerscambodia.devcoursesservice.course.web;

import com.developerscambodia.devcoursesservice.base.BaseApi;
import com.developerscambodia.devcoursesservice.base.BaseError;
import com.developerscambodia.devcoursesservice.course.Course;
import com.developerscambodia.devcoursesservice.course.CourseMapper;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public BaseApi<?> createNewCourse(@RequestBody CreateCourseDto createCourseDto) {


       CreateCourseDto createdCourse = courseService.createNewCourse(createCourseDto);
        //var courseDto = courseMapper.courseToCourseDto(createdCourse);

        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Course Create Successfully")
                .timeStamp(LocalDateTime.now())
                .data(createdCourse)
                .build();

    }

    @GetMapping

    public BaseApi<Page<CourseDto>> findListCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<CourseDto> course = courseService.findListCourses(page, size);

        BaseApi<Page<CourseDto>> response = BaseApi.<Page<CourseDto>>builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Courses Retrieved Successfully")
                .timeStamp(LocalDateTime.now())
                .data(course)
                .build();

        return response;
    }

    @GetMapping("/{uuid}")
    public BaseApi<?> findCourseByUuid(@PathVariable String uuid) {

        var course = courseService.findCourseByUuid(uuid);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Course Has Been Found Successfully")
                .timeStamp(LocalDateTime.now())
                .data(course)
                .build();
    }

    @DeleteMapping("/{uuid}")
    public BaseApi<?> removeCourseByUuid(@PathVariable String uuid) {
        CourseDto deletedCourse = courseService.removeCourseByUuid(uuid);

        if (deletedCourse != null) {
            return BaseApi.builder()
                    .status(true)
                    .code(HttpStatus.OK.value())
                    .message("Course Has Been Deleted Successfully")
                    .timeStamp(LocalDateTime.now())
                    .data(deletedCourse)
                    .build();
        } else {
            return BaseApi.builder()
                    .status(false)
                    .code(HttpStatus.NOT_FOUND.value())
                    .message("Course not found with UUID: " + uuid)
                    .timeStamp(LocalDateTime.now())
                    .build();
        }
    }


    @PutMapping("/{uuid}")
    public BaseApi<?> editCourseByUuid(@PathVariable String uuid, @RequestBody CourseDto updatedCourseDto) {
        CourseDto updatedCourse = courseService.editCourseByUuid(uuid, updatedCourseDto);
        if (updatedCourse != null) {
            return BaseApi.builder()
                    .status(true)
                    .code(HttpStatus.OK.value())
                    .message("Course Has Been update Successfully")
                    .timeStamp(LocalDateTime.now())
                    .data(updatedCourse)
                    .build();
        } else {
            return BaseApi.builder()
                    .status(false)
                    .code(HttpStatus.NOT_FOUND.value())
                    .message("Course not found with UUID: " + uuid)
                    .timeStamp(LocalDateTime.now())
                    .build();
        }
    }




//    @GetMapping("/category/{categoryName}")
//    public List<Course> findListCoursesByCategory(@PathVariable String categoryName) {
//
//        return courseService.filterCoursesByCategory(categoryName);
//    }



    @GetMapping("/{uuid}/sections")
    public ResponseEntity<List<Section>> getSectionsByCourseUuid(@PathVariable String uuid) {
        List<Section> sections = courseService.getSectionsByCourseUuid(uuid);
        return new ResponseEntity<>(sections, HttpStatus.OK);
    }

}






