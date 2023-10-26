package com.example.qcassistant.service.sponsor;

import com.example.qcassistant.domain.dto.sponsor.sponsorAddDto;
import com.example.qcassistant.domain.dto.sponsor.sponsorDisplayDto;
import com.example.qcassistant.domain.dto.sponsor.sponsorEditDto;
import com.example.qcassistant.domain.entity.BaseEntity;
import com.example.qcassistant.domain.entity.sponsor.BaseSponsor;
import com.example.qcassistant.domain.entity.sponsor.IqviaSponsor;
import com.example.qcassistant.domain.entity.sponsor.MedidataSponsor;
import com.example.qcassistant.repository.sponsor.IqviaSponsorRepository;
import com.example.qcassistant.repository.sponsor.MedidataSponsorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IqviaSponsorService extends BaseSponsorService{

    private IqviaSponsorRepository sponsorRepository;

    @Autowired
    public IqviaSponsorService(ModelMapper modelMapper, IqviaSponsorRepository sponsorRepository) {
        super(modelMapper);
        this.sponsorRepository = sponsorRepository;
    }

    @Override
    public void addSponsor(sponsorAddDto sponsorAddDto) {
        validateAddSponsor(sponsorAddDto);
        IqviaSponsor sponsor = this.modelMapper.map(
                sponsorAddDto, IqviaSponsor.class);
        this.sponsorRepository.save(sponsor);
    }

    @Override
    public List<IqviaSponsor> getEntities() {
        return this.sponsorRepository
                .findAllByNameNot(BaseEntity.UNKNOWN);
    }

    public sponsorEditDto getSponsorEditById(Long id) {
        return this.modelMapper.map(
                this.sponsorRepository.findById(id)
                        .orElseThrow(),
                sponsorEditDto.class);
    }

    @Override
    public void editSponsor(sponsorEditDto sponsorEditDto) {
        validateEditSponsor(sponsorEditDto);
        IqviaSponsor sponsor = this.modelMapper.map(sponsorEditDto, IqviaSponsor.class);
        this.sponsorRepository.save(sponsor);
    }

    @Override
    protected Optional<IqviaSponsor> findFirstByName(String name) {
        return getSponsorRepository().findFirstByName(name);
    }

    @Override
    protected IqviaSponsorRepository getSponsorRepository() {
        return this.sponsorRepository;
    }
}