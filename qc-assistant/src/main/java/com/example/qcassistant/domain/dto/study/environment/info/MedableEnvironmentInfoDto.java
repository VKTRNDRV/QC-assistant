package com.example.qcassistant.domain.dto.study.environment.info;

import com.example.qcassistant.domain.entity.app.BaseApp;
import com.example.qcassistant.domain.entity.study.environment.IqviaEnvironment;
import com.example.qcassistant.domain.entity.study.environment.MedableEnvironment;

import java.util.List;
import java.util.stream.Collectors;

public class MedableEnvironmentInfoDto{

    private String isSitePatientSeparated;
    private String isDestinationSeparated;
    private String patientApps;
    private String siteApps;
    private String containsChinaGroup;




    public String getPatientApps() {
        return patientApps;
    }

    public MedableEnvironmentInfoDto setPatientApps(String patientApps) {
        this.patientApps = patientApps;
        return this;
    }

    public String getSiteApps() {
        return siteApps;
    }

    public MedableEnvironmentInfoDto setSiteApps(String siteApps) {
        this.siteApps = siteApps;
        return this;
    }

    public String getContainsChinaGroup() {
        return containsChinaGroup;
    }

    public MedableEnvironmentInfoDto setContainsChinaGroup(String containsChinaGroup) {
        this.containsChinaGroup = containsChinaGroup;
        return this;
    }

    public String getIsSitePatientSeparated() {
        return isSitePatientSeparated;
    }

    public MedableEnvironmentInfoDto setIsSitePatientSeparated(String isSitePatientSeparated) {
        this.isSitePatientSeparated = isSitePatientSeparated;
        return this;
    }

    public String getIsDestinationSeparated() {
        return isDestinationSeparated;
    }

    public MedableEnvironmentInfoDto setIsDestinationSeparated(String isDestinationSeparated) {
        this.isDestinationSeparated = isDestinationSeparated;
        return this;
    }

    public void setSpecialFields(MedableEnvironment environment){
        this.setPatientApps(this.getAppNamesList(environment.getPatientApps()))
                .setSiteApps(this.getAppNamesList(environment.getSiteApps()))
                .setContainsChinaGroup(environment.getContainsChinaGroup().name());
    }

    public String getAppNamesList(List<? extends BaseApp> apps){
        return apps.stream()
                .map(BaseApp::getName)
                .collect(Collectors.joining(", "));
    }
}
