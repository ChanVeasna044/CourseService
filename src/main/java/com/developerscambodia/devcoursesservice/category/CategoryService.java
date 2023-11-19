package com.developerscambodia.devcoursesservice.category;

import com.developerscambodia.devcoursesservice.category.web.CategoryDto;
import com.developerscambodia.devcoursesservice.course.Course;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    CategoryDto createNewCategory(CategoryDto categoryDto);

    List<CategoryDto> findAllCategories();


    CategoryDto findCategoryByUuid(String uuid);

    void removeCategoryByUuid(String uuid);

    Optional<Category> findCategoryByUuidWithCourse(String categoryUuid);



}
