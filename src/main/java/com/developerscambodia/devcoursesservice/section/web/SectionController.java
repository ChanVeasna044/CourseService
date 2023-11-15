package com.developerscambodia.devcoursesservice.section.web;

import com.developerscambodia.devcoursesservice.base.BaseApi;
import com.developerscambodia.devcoursesservice.section.Section;
import com.developerscambodia.devcoursesservice.section.SectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/sections")
public class SectionController {

    private final SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @PostMapping
    public BaseApi<?> createSection(@RequestBody CreateSectionDto createSectionDto) {
        CreateSectionDto createdSection = sectionService.createSection(createSectionDto);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Section Create Successfully")
                .timeStamp(LocalDateTime.now())
                .data(createdSection)
                .build();
    }

    @GetMapping
    public Page<SectionDto> findSections(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return sectionService.findListSections( page, size);
    }

    @GetMapping("/{uuid}")
    public BaseApi<?> findSectionByUuid(@PathVariable String uuid) {

        var section = sectionService.findSectionByUuid(uuid);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Section Has Been Found Successfully")
                .timeStamp(LocalDateTime.now())
                .data(section)
                .build();
    }


    @PutMapping("/{uuid}")
    public BaseApi<?> editCourseByUuid(@PathVariable String uuid, @RequestBody UpdateSectionDto updatedSectionDto) {
        UpdateSectionDto updatedSection = sectionService.editSectionByUuid(uuid, updatedSectionDto);
        if (updatedSection != null) {
            return BaseApi.builder()
                    .status(true)
                    .code(HttpStatus.OK.value())
                    .message("Section Has Been update Successfully")
                    .timeStamp(LocalDateTime.now())
                    .data(updatedSection)
                    .build();
        } else {
            return BaseApi.builder()
                    .status(false)
                    .code(HttpStatus.NOT_FOUND.value())
                    .message("Section not found with UUID: " + uuid)
                    .timeStamp(LocalDateTime.now())
                    .build();
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<BaseApi<?>> removeSectionByUuid(@PathVariable String uuid) {
        try {
            sectionService.removeSectionByUuid(uuid);
            BaseApi<?> response = BaseApi.builder()
                    .status(true)
                    .code(HttpStatus.OK.value())
                    .message("Section has been deleted successfully")
                    .timeStamp(LocalDateTime.now())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Handle NotFoundException (HTTP 404 Not Found) appropriately
            BaseApi<?> errorResponse = BaseApi.builder()
                    .status(false)
                    .code(HttpStatus.NOT_FOUND.value())
                    .message("Section not found with UUID: " + uuid)
                    .timeStamp(LocalDateTime.now())
                    .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Handle other exceptions (HTTP 500 Internal Server Error) appropriately
            BaseApi<?> errorResponse = BaseApi.builder()
                    .status(false)
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Internal Server Error")
                    .timeStamp(LocalDateTime.now())
                    .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    }
//@GetMapping("/course/{uuid}")
//public List<Section> getSectionsByCourseId(@PathVariable String uuid) {
//    return sectionService.getSectionsByCourseUuid(uuid);
//}

//    @GetMapping("/course/{courseId}")
//    public List<Section> getSectionsByCourseId(@PathVariable String courseId) {
//        return sectionService.getSectionsByCourseId(courseId);
//    }




}