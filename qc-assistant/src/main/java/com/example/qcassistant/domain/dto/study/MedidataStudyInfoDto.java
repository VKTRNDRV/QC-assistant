package com.example.qcassistant.domain.dto.study;

import com.example.qcassistant.domain.dto.study.environment.MedidataEnvironmentInfoDto;
import com.example.qcassistant.domain.entity.app.BaseApp;
import com.example.qcassistant.domain.entity.study.MedidataStudy;
import com.example.qcassistant.domain.entity.study.environment.MedidataEnvironment;
import com.example.qcassistant.util.TrinaryBoolean;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;
import java.util.stream.Collectors;

public class MedidataStudyInfoDto {

    private String name;
    private String folderURL;
    private String sponsor;
    private String containsTranslatedLabels;
    private String containsTranslatedDocs;
    private String containsEditableWelcomeLetter;
    private String isPatientDeviceIpad;
    private String includesHeadphonesStyluses;
    private MedidataEnvironmentInfoDto environment;


    public String getName() {
        return name;
    }

    public MedidataStudyInfoDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getFolderURL() {
        return folderURL;
    }

    public MedidataStudyInfoDto setFolderURL(String folderURL) {
        this.folderURL = folderURL;
        return this;
    }

    public String getSponsor() {
        return sponsor;
    }

    public MedidataStudyInfoDto setSponsor(String sponsor) {
        this.sponsor = sponsor;
        return this;
    }

    public String getContainsTranslatedLabels() {
        return containsTranslatedLabels;
    }

    public MedidataStudyInfoDto setContainsTranslatedLabels(String containsTranslatedLabels) {
        this.containsTranslatedLabels = containsTranslatedLabels;
        return this;
    }

    public String getContainsTranslatedDocs() {
        return containsTranslatedDocs;
    }

    public MedidataStudyInfoDto setContainsTranslatedDocs(String containsTranslatedDocs) {
        this.containsTranslatedDocs = containsTranslatedDocs;
        return this;
    }

    public String getContainsEditableWelcomeLetter() {
        return containsEditableWelcomeLetter;
    }

    public MedidataStudyInfoDto setContainsEditableWelcomeLetter(String containsEditableWelcomeLetter) {
        this.containsEditableWelcomeLetter = containsEditableWelcomeLetter;
        return this;
    }

    public String getIsPatientDeviceIpad() {
        return isPatientDeviceIpad;
    }

    public MedidataStudyInfoDto setIsPatientDeviceIpad(String isPatientDeviceIpad) {
        this.isPatientDeviceIpad = isPatientDeviceIpad;
        return this;
    }

    public String getIncludesHeadphonesStyluses() {
        return includesHeadphonesStyluses;
    }

    public MedidataStudyInfoDto setIncludesHeadphonesStyluses(String includesHeadphonesStyluses) {
        this.includesHeadphonesStyluses = includesHeadphonesStyluses;
        return this;
    }

    public MedidataEnvironmentInfoDto getEnvironment() {
        return environment;
    }

    public MedidataStudyInfoDto setEnvironment(MedidataEnvironmentInfoDto environment) {
        this.environment = environment;
        return this;
    }

    public void setSpecialFields(MedidataStudy study) {
        this.setSponsor(study.getSponsor().getName());
        this.environment.setSpecialFields(study.getEnvironment());
    }
}
