package com.example.qcassistant.unit.entities;

import com.example.qcassistant.domain.dto.LanguageDto;
import com.example.qcassistant.domain.dto.destination.DestinationAddDto;
import com.example.qcassistant.domain.dto.destination.DestinationEditDto;
import com.example.qcassistant.domain.entity.BaseEntity;
import com.example.qcassistant.domain.entity.destination.Language;
import com.example.qcassistant.repository.LanguageRepository;
import com.example.qcassistant.service.LanguageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class LanguageServiceTests {

    private LanguageService languageService;

    private LanguageRepository languageRepository;

    @Autowired
    public LanguageServiceTests(LanguageService languageService, LanguageRepository languageRepository) {
        this.languageService = languageService;
        this.languageRepository = languageRepository;
    }

    @Test
    public <T> void getAllLanguages_ReturnsCorrectType(){
        List<T> result = (List<T>) this.languageService.getAllLanguages();

        Assertions.assertEquals(result.size(),
                this.languageRepository.count());
        Assertions.assertEquals(result.get(0).getClass(), LanguageDto.class);
    }

    @Test
    public void getLanguageById_ReturnsCorrectEntity(){
        Language english = this.languageRepository
                .findFirstByName("English").get();

        LanguageDto fromService = this.languageService
                .getLanguageById(english.getId());

        Assertions.assertEquals(english.getName(),
                fromService.getName());
    }

    @Test
    public void getEntities_ReturnsCorrectCount(){
        List<Language> fromService = this
                .languageService.getEntities();

        List<Language> fromRepository = this
                .languageRepository.findAll();

        Assertions.assertEquals(fromService.size(),
                fromRepository.size());
    }

    @Test
    public void testAddLanguageWithBlankName() {
        LanguageDto languageDto = new LanguageDto()
                .setName("  ");

        Assertions.assertThrows(RuntimeException.class, () ->
                languageService.addLanguage(languageDto));
    }

    @Test
    public void testAddLanguageWithExistingName() {
        LanguageDto languageDto = new LanguageDto()
                .setName("English");


        Assertions.assertThrows(RuntimeException.class, () ->
                languageService.addLanguage(languageDto));
    }

    @Test
    public void testEditLanguageWithBlankName() {
        LanguageDto languageDto = new LanguageDto()
                .setName("  ");

        Assertions.assertThrows(RuntimeException.class, () ->
                languageService.editLanguage(languageDto));
    }
}