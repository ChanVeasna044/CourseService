package com.developerscambodia.devcoursesservice.section;

import com.developerscambodia.devcoursesservice.course.CourseRepository;
import com.developerscambodia.devcoursesservice.section.web.CreateSectionDto;
import com.developerscambodia.devcoursesservice.section.web.SectionDto;
import com.developerscambodia.devcoursesservice.section.web.UpdateSectionDto;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;
    private final MongoTemplate mongoTemplate;
    private final CourseRepository courseRepository;
    @Override
    public CreateSectionDto createSection(CreateSectionDto createSectionDto) {
        Section section = sectionMapper.createSectionDtoToSection(createSectionDto);
        section.setUuid(UUID.randomUUID().toString());
        sectionRepository.save(section);
        return sectionMapper.sectionToCreateSection(section);
    }


    @Override
    public SectionDto findSectionByUuid(String uuid) {
        Section section= sectionRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("section not found with UUID: " + uuid));



        return sectionMapper.sectionToSectionDto(section);
    }

    @Override
    public UpdateSectionDto editSectionByUuid(String uuid, UpdateSectionDto updatedSectionDto) {
        //Section existingSection = sectionRepository.findByUuid(sectionUuid);


        Section existingSection = sectionRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Section not found with UUID: " + uuid));

        // Update the existing course with the new data from updatedCourseDto
        existingSection.setName(updatedSectionDto.name());
       // existingCourse.setDescription(updatedCourseDto.description());

        // Update other properties as needed...

        // Save the updated Section to the repository
        Section updatedSection = sectionRepository.save(existingSection);

        // Convert the updated Section object to CourseDto using the mapper
        UpdateSectionDto updateSectionDto = sectionMapper.sectionToUpdateSectionDto(updatedSection);

        return updatedSectionDto;
    }

    @Override
    public void removeSectionByUuid(String uuid) {
        Section existingSection = sectionRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("Section not found with UUID: " + uuid));

        sectionRepository.delete(existingSection);
    }
//
//    @Override
//    public List<Section> getAllSectionsWithCourseUuid() {
//        return sectionRepository.findAllSectionsWithCourseUuid();
//    }

//    @Override
//    public Page<SectionDto> findListSections(int page, int size) {
//        PageRequest pageable = PageRequest.of(page, size);
//        Page<Section> sectionPage = sectionRepository.findAll(pageable);
//
//        // Convert Page<Section> to Page<SectionDto> using map function
//        Page<SectionDto> sectionDtoPage = sectionPage.map(section -> {
//            SectionDto sectionDto = sectionMapper.sectionToSectionDto(section);
//
//            return sectionDto;
//        });
//
//        return sectionDtoPage;
//    }

    @Override
    public Page<SectionDto> findListSections(String courseUuid, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Section> sectionPage;

        if (courseUuid != null && !courseUuid.isEmpty()) {
            sectionPage = sectionRepository.findByCourseUuid(courseUuid, pageable);
        } else {
            sectionPage = sectionRepository.findAll(pageable);
        }

        // Convert Page<Section> to Page<SectionDto> using map function
        Page<SectionDto> sectionDtoPage = sectionPage.map(section -> sectionMapper.sectionToSectionDto(section));

        return sectionDtoPage;
    }

//    @Override
//    public List<Section> getSectionsByCourseId(String courseId) {
//        return sectionRepository.findByCourseId(courseId);
//    }
//
//    @Override
//    public List<Section> getSectionsByCourseUuid(String courseUuid) {
//        return sectionRepository.findByCourseUuid(courseUuid);
//    }


}