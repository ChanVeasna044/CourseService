package com.developerscambodia.devcoursesservice.section;

import com.developerscambodia.devcoursesservice.section.web.CreateSectionDto;
import com.developerscambodia.devcoursesservice.section.web.SectionDto;
import com.developerscambodia.devcoursesservice.section.web.UpdateSectionDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SectionService {

    CreateSectionDto createSection(CreateSectionDto createSectionDto);


    SectionDto findSectionByUuid(String sectionUuid);

    UpdateSectionDto editSectionByUuid(String sectionUuid, UpdateSectionDto updatedSectionDto);
    void removeSectionByUuid(String uuid);



    Page<SectionDto> findListSections( int page, int size);


}
