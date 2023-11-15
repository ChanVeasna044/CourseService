package com.developerscambodia.devcoursesservice.category.web;

import com.developerscambodia.devcoursesservice.base.BaseApi;
import com.developerscambodia.devcoursesservice.category.Category;
import com.developerscambodia.devcoursesservice.category.CategoryRepository;
import com.developerscambodia.devcoursesservice.category.CategoryService;
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
    public BaseApi<?> findCategoryByUuid(@PathVariable String uuid) {

        var category = categoryService.findCategoryByUuid(uuid);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Category Has Been Found Successfully")
                .timeStamp(LocalDateTime.now())
                .data(category)
                .build();
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
