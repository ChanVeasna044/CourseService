package com.developerscambodia.devcoursesservice.category.web;

import com.developerscambodia.devcoursesservice.base.BaseApi;
import com.developerscambodia.devcoursesservice.category.Category;
import com.developerscambodia.devcoursesservice.category.CategoryRepository;
import com.developerscambodia.devcoursesservice.category.CategoryService;
import com.developerscambodia.devcoursesservice.course.Course;
import com.developerscambodia.devcoursesservice.course.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryRepository categoryRepository;

    private final CategoryService categoryService;

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto createdCategory = categoryService.createNewCategory(categoryDto);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

//    @GetMapping("/courses/{courseUuid}")
//    public List<Category> getCategoriesByCourseUuid(@PathVariable String courseUuid) {
//        return categoryService.getCourseByCategoryUuid(courseUuid);
//    }


    @GetMapping("")
    public BaseApi<List<CategoryDto>> findAllCategory() {
        List<CategoryDto> categories = categoryService.findAllCategories();
        BaseApi<List<CategoryDto>> response = BaseApi.<List<CategoryDto>>builder()
                .message("Categories found successfully")
                .code(200)
                .status(true)
                .timeStamp(LocalDateTime.now())
                .data(categories)
                .build();
        return response;
    }





    @GetMapping("/{uuid}")
    public ResponseEntity<Category> findCategoryByUuid(@PathVariable String uuid) {
        Optional<Category> category = categoryService.findCategoryByUuidWithCourse(uuid);

        if (category.isPresent()) {
            return ResponseEntity.ok(category.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> removeCategoryByUuid(@PathVariable String uuid) {
        try {
            categoryService.removeCategoryByUuid(uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }









}
