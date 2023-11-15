package com.developerscambodia.devcoursesservice.section;

import com.developerscambodia.devcoursesservice.section.web.CreateSectionDto;
import com.developerscambodia.devcoursesservice.section.web.SectionDto;
import com.developerscambodia.devcoursesservice.section.web.UpdateSectionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SectionMapper {

    Section createSectionDtoToSection(CreateSectionDto createSectionDto);

    CreateSectionDto sectionToCreateSection(Section section);

    Section sectionDtoToSection(SectionDto sectionDto);

    SectionDto sectionToSectionDto(Section section);

   // Section updateSectionDtoToSection(UpdateSectionDto updateSectionDto);

    UpdateSectionDto sectionToUpdateSectionDto(Section section);




}
