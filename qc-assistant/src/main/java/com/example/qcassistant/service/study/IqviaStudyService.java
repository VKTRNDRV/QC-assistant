package com.example.qcassistant.service.study;

import com.example.qcassistant.domain.dto.study.StudyDisplayDto;
import com.example.qcassistant.domain.dto.study.edit.IqviaStudyEditDto;
import com.example.qcassistant.domain.dto.study.add.IqviaStudyAddDto;
import com.example.qcassistant.domain.dto.study.info.IqviaStudyInfoDto;
import com.example.qcassistant.domain.dto.study.info.MedidataStudyInfoDto;
import com.example.qcassistant.domain.entity.BaseEntity;
import com.example.qcassistant.domain.entity.app.IqviaApp;
import com.example.qcassistant.domain.entity.sponsor.IqviaSponsor;
import com.example.qcassistant.domain.entity.study.IqviaStudy;
import com.example.qcassistant.domain.entity.study.MedidataStudy;
import com.example.qcassistant.repository.app.IqviaAppRepository;
import com.example.qcassistant.repository.sponsor.IqviaSponsorRepository;
import com.example.qcassistant.repository.study.IqviaStudyRepository;
import com.example.qcassistant.repository.study.environment.IqviaEnvironmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IqviaStudyService extends BaseStudyService{

    private IqviaStudyRepository studyRepository;
    private IqviaAppRepository appRepository;
    private IqviaSponsorRepository sponsorRepository;
    private IqviaEnvironmentRepository environmentRepository;

    @Autowired
    public IqviaStudyService(ModelMapper modelMapper,
                             IqviaStudyRepository studyRepository,
                             IqviaAppRepository appRepository,
                             IqviaSponsorRepository sponsorRepository,
                             IqviaEnvironmentRepository environmentRepository) {
        super(modelMapper);
        this.studyRepository = studyRepository;
        this.appRepository = appRepository;
        this.sponsorRepository = sponsorRepository;
        this.environmentRepository = environmentRepository;
    }

    public void addStudy(IqviaStudyAddDto studyAddDto) {
        validateStudyAdd(studyAddDto);
        studyAddDto.trimStringFields();
        IqviaStudy study = mapToEntity(studyAddDto);
        this.environmentRepository.save(study.getEnvironment());
        this.studyRepository.save(study);
    }

    private void validateStudyAdd(IqviaStudyAddDto studyAddDto) {
        studyAddDto.trimStringFields();
        super.validateNameNotBlank(studyAddDto.getName());
        validateUniqueName(studyAddDto.getName());
        validateAppsCount(studyAddDto.getEnvironment().getSiteApps(),
                studyAddDto.getEnvironment().getPatientApps());
    }


    private void validateUniqueName(String name){
        if(this.studyRepository.findFirstByName(name).isPresent()){
            throw new RuntimeException("Study \"" + name + "\" already present");
        }
    }

    private IqviaStudy mapToEntity(IqviaStudyAddDto studyAddDto) {
        IqviaStudy study = this.modelMapper.map(studyAddDto, IqviaStudy.class);
        IqviaSponsor sponsor = this.sponsorRepository
                .findFirstByName(studyAddDto.getSponsor()).orElseThrow();
        List<IqviaApp> siteApps = getAppsByName(
                studyAddDto.getEnvironment().getSiteApps());
        List<IqviaApp> patientApps = getAppsByName(
                studyAddDto.getEnvironment().getPatientApps());

        study.setSponsor(sponsor)
                .getEnvironment()
                .setSiteApps(siteApps)
                .setPatientApps(patientApps);
        return study;
    }

    public IqviaStudyEditDto getStudyEditById(Long id) {
        IqviaStudy study = this.studyRepository
                .findById(id).orElseThrow();
        IqviaStudyEditDto editDto = this.modelMapper
                .map(study, IqviaStudyEditDto.class);
        editDto.setManualFields(study);
        return editDto;
    }

    private List<IqviaApp> getAppsByName(List<String> appNames) {
        List<IqviaApp> apps = new ArrayList<>();
        for(String name : appNames){
            Optional<IqviaApp> app = this.appRepository.findFirstByName(name);
            app.ifPresent(apps::add);
        }

        return apps;
    }

    public void editStudy(IqviaStudyEditDto studyEditDto){
        validateStudyEdit(studyEditDto);
        studyEditDto.trimStringFields();
        IqviaStudy study = mapToEntity(studyEditDto);
        this.environmentRepository.save(study.getEnvironment());
        this.studyRepository.save(study);
    }

    private IqviaStudy mapToEntity(IqviaStudyEditDto studyEditDto) {
        IqviaStudy study = this.modelMapper.map(studyEditDto, IqviaStudy.class);
        List<IqviaApp> siteApps = getAppsByName(
                studyEditDto.getEnvironment().getSiteApps());
        List<IqviaApp> patientApps =
                getAppsByName(studyEditDto.getEnvironment().getPatientApps());
        IqviaSponsor sponsor = this.sponsorRepository
                .findFirstByName(studyEditDto.getSponsor()).orElseThrow();
        study.setSponsor(sponsor)
                .getEnvironment()
                .setSiteApps(siteApps)
                .setPatientApps(patientApps);
        return study;
    }

    private void validateStudyEdit(IqviaStudyEditDto studyEditDto) {
        studyEditDto.trimStringFields();
        super.validateNameNotBlank(studyEditDto.getName());
        if(!this.studyRepository.findById(studyEditDto.getId()).get().getName()
                .equals(studyEditDto.getName())){
            validateUniqueName(studyEditDto.getName());
        }
        validateAppsCount(studyEditDto.getEnvironment().getSiteApps(),
                studyEditDto.getEnvironment().getPatientApps());
    }

    private List<IqviaStudy> getEntities() {
        return this.studyRepository.findAllByNameNot(BaseEntity.UNKNOWN);
    }

    public List<StudyDisplayDto> displayAllStudies() {
        return getEntities().stream()
                .map(s -> new StudyDisplayDto()
                        .setId(s.getId())
                        .setName(s.getName())
                        .setSponsor(s.getSponsor().getName()))
                .sorted((s1,s2) -> {
                    int result = s1.getSponsor().compareTo(s2.getSponsor());
                    if(result == 0){
                        result = s1.getName().compareTo(s2.getName());
                    }
                    return result;
                })
                .collect(Collectors.toList());
    }

    public IqviaStudyInfoDto getStudyInfoById(Long id) {
        IqviaStudy study = this.studyRepository
                .findById(id).orElseThrow();

        IqviaStudyInfoDto dto = this.modelMapper
                .map(study, IqviaStudyInfoDto.class);
        dto.setSpecialFields(study);

        return dto;
    }
}
