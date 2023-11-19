package com.developerscambodia.devcoursesservice.section;

import com.developerscambodia.devcoursesservice.course.Course;
import com.developerscambodia.devcoursesservice.section.web.CreateSectionDto;
import com.developerscambodia.devcoursesservice.section.web.SectionDto;
import com.developerscambodia.devcoursesservice.section.web.UpdateSectionDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SectionService {

    CreateSectionDto createSection(CreateSectionDto createSectionDto);


    SectionDto findSectionByUuid(String sectionUuid);

    UpdateSectionDto editSectionByUuid(String sectionUuid, UpdateSectionDto updatedSectionDto);
    void removeSectionByUuid(String uuid);
//    List<Section> getAllSectionsWithCourseUuid();


    Page<SectionDto> findListSections( String courseUuid,int page, int size);
//    List<Section> getSectionsByCourseId(String courseId);
//    List<Section> getSectionsByCourseUuid(String courseUuid);
//    Optional<Course> findCourseWithSectionsByUuid(String uuid);


}
