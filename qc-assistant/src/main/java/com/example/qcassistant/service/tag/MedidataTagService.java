package com.example.qcassistant.service.tag;

import com.example.qcassistant.domain.dto.tag.TagAddDto;
import com.example.qcassistant.domain.entity.study.MedidataStudy;
import com.example.qcassistant.domain.entity.tag.MedidataTag;
import com.example.qcassistant.repository.DestinationRepository;
import com.example.qcassistant.repository.study.MedidataStudyRepository;
import com.example.qcassistant.repository.tag.MedidataTagRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedidataTagService extends BaseTagService{

    private MedidataTagRepository tagRepository;

    private MedidataStudyRepository studyRepository;

    @Autowired
    public MedidataTagService(ModelMapper modelMapper, DestinationRepository destinationRepository, MedidataTagRepository tagRepository, MedidataStudyRepository studyRepository) {
        super(modelMapper, destinationRepository);
        this.tagRepository = tagRepository;
        this.studyRepository = studyRepository;
    }

    @Override
    public List<MedidataTag> getEntities() {
        return null;
    }

    @Override
    public void addTag(TagAddDto tagAddDto) {
        super.validateTagAdd(tagAddDto);
        MedidataTag tag = this.mapToEntity(tagAddDto);
        this.tagRepository.save(tag);
    }

    private MedidataTag mapToEntity(TagAddDto tagAddDto) {
        MedidataTag tag = super.modelMapper
                .map(tagAddDto, MedidataTag.class);
        tag.setDestinations(super.getDestinationsByNames(
                tagAddDto.getDestinations()));
        tag.setStudies(this.getStudiesByNames(
                tagAddDto.getStudies()));

        return tag;
    }

    private List<MedidataStudy> getStudiesByNames(List<String> studyNames) {
        List<MedidataStudy> studies = new ArrayList<>();
        for(String studyName : studyNames){
            Optional<MedidataStudy> study = this.studyRepository
                    .findFirstByName(studyName);
            study.ifPresent(studies::add);
        }

        return studies;
    }

    @Override
    public void editTag() {

    }

    @Override
    public MedidataTag getTagById(Long id) {
        return null;
    }
}
