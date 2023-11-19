package com.developerscambodia.devcoursesservice.category;

import com.developerscambodia.devcoursesservice.category.web.CategoryDto;
import com.developerscambodia.devcoursesservice.course.Course;
import com.developerscambodia.devcoursesservice.course.CourseMapper;
import com.developerscambodia.devcoursesservice.course.CourseRepository;
import com.developerscambodia.devcoursesservice.course.web.CourseDto;
import com.developerscambodia.devcoursesservice.section.Section;
import com.developerscambodia.devcoursesservice.section.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategorySeviceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final SectionRepository sectionRepository;
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

    @Override
    public Optional<Category> findCategoryByUuidWithCourse(String categoryUuid) {
        Optional<Category> optionalCategory = categoryRepository.findByUuid(categoryUuid);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            List<Course> courses = courseRepository.findByCategoryUuid(categoryUuid);

            // Fetch and set courses for the category
            category.setCourses(courses);

            // Fetch and set sections for each course
            for (Course course : courses) {
                List<Section> sections = sectionRepository.findByCourseUuid(course.getUuid());
                course.setSections(sections);
            }

            return Optional.of(category);
        } else {
            return Optional.empty();
        }
//        Optional<Category> optionalCategory = categoryRepository.findByUuid(categoryUuid);
//
//        if (optionalCategory.isPresent()) {
//            Category category = optionalCategory.get();
//            List<Course> courses = courseRepository.findByCategoryUuid(categoryUuid);
//
//            category.setCourses(courses);
//            return Optional.of(category);
//        } else {
//            return Optional.empty();
//        }
    }




}
