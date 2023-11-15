package com.developerscambodia.devcoursesservice.category;

import com.developerscambodia.devcoursesservice.category.web.CategoryDto;
import com.developerscambodia.devcoursesservice.course.Course;
import com.developerscambodia.devcoursesservice.course.web.CreateCourseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto categoryToCategoryDto(Category category);
    List<CategoryDto> categoriesToCategoryDtos(List<Category> categories);
   Category categoryDtoToCategory(CategoryDto categoryDto);


}
