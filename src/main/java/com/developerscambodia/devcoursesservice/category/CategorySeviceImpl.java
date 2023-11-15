package com.developerscambodia.devcoursesservice.category;

import com.developerscambodia.devcoursesservice.category.web.CategoryDto;
import com.developerscambodia.devcoursesservice.course.Course;
import com.developerscambodia.devcoursesservice.course.CourseMapper;
import com.developerscambodia.devcoursesservice.course.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategorySeviceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
//    private final CourseRepository courseRepository;
//    private final CourseMapper courseMapper;
    @Override
    public CategoryDto createNewCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.categoryDtoToCategory(categoryDto);

        // Generate a UUID for the new category
        String uuid = UUID.randomUUID().toString();
        category.setUuid(uuid);

        // Save the category to the database
        categoryRepository.save(category);

        // Return the CategoryDto representation of the saved category
        return categoryMapper.categoryToCategoryDto(category);
    }

    public List<CategoryDto> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.categoriesToCategoryDtos(categories);
    }

    @Override
    public CategoryDto findCategoryByUuid(String uuid) {
        Category category = categoryRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Course not found with UUID: " + uuid));

        return categoryMapper.categoryToCategoryDto(category);
    }

    @Override
    public void removeCategoryByUuid(String uuid) {
        // Check if the category exists
        Category category = categoryRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Category with UUID: " + uuid + " not found"));

        // Delete the category from the database
        categoryRepository.delete(category);
    }

}
