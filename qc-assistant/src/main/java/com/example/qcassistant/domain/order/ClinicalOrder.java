package com.example.qcassistant.domain.order;

import com.example.qcassistant.domain.entity.destination.Destination;
import com.example.qcassistant.domain.entity.destination.Language;
import com.example.qcassistant.domain.enums.OrderType;
import com.example.qcassistant.domain.item.device.Device;

import java.util.Collection;

public abstract class ClinicalOrder {

    protected OrderType orderType;
    protected Destination destination;
    protected Collection<Language> requestedLanguages;
    protected String orderTermComments;
    protected DeviceRepository deviceRepository;
    protected AccessoryRepository accessoryRepository;



    public Destination getDestination() {
        return destination;
    }

    public ClinicalOrder setDestination(Destination destination) {
        this.destination = destination;
        return this;
    }

    public Collection<Language> getRequestedLanguages() {
        return requestedLanguages;
    }

    public ClinicalOrder setRequestedLanguages(Collection<Language> requestedLanguages) {
        this.requestedLanguages = requestedLanguages;
        return this;
    }

    public String getOrderTermComments() {
        return orderTermComments;
    }

    public ClinicalOrder setOrderTermComments(String orderTermComments) {
        this.orderTermComments = orderTermComments;
        return this;
    }

    public DeviceRepository getDeviceRepository() {
        return deviceRepository;
    }

    public ClinicalOrder setDeviceRepository(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
        return this;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public ClinicalOrder setOrderType(OrderType orderType) {
        this.orderType = orderType;
        return this;
    }

    public AccessoryRepository getAccessoryRepository() {
        return accessoryRepository;
    }

    public ClinicalOrder setAccessoryRepository(AccessoryRepository accessoryRepository) {
        this.accessoryRepository = accessoryRepository;
        return this;
    }

    public boolean containsPatientDevices(){
        return deviceRepository.containsPhones();
    }

    public boolean containsSiteDevices(){
        return deviceRepository.containsTablets();
    }

    public boolean isEnglishRequested() {
        if(requestedLanguages.size() > 1) return false;
        if(requestedLanguages.size() == 1){
            for(Language lang : requestedLanguages){
                if(lang.getName()
                        .equals(Language.ENGLISH)){
                    return true;
                }
            }

            return false;
        } else {
            if(destination.isEnglishSpeaking()){
                return true;
            }else {
                return false;
            }
        }
    }

    public boolean areMultipleLanguagesRequested() {
        return requestedLanguages.size() > 1;
    }

    public boolean areNoLanguagesDetected() {
        return requestedLanguages.isEmpty();
    }

    public boolean requestsUnusualLanguages() {
        if(destination.isUnknown()) return false;
        for(Language language : requestedLanguages){
            if(!language.getName().equals(Language.ENGLISH)){
                continue;
            }

            if(!destination.containsLanguage(language)){
                return true;
            }
        }

        return false;
    }
}
