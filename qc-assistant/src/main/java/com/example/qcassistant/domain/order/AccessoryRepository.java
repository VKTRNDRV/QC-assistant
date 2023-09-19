package com.example.qcassistant.domain.order;

import com.example.qcassistant.domain.item.accessory.Accessory;

import java.util.ArrayList;
import java.util.Collection;

public class AccessoryRepository {

    private Collection<Accessory> accessories;

    public AccessoryRepository(){
        this.accessories = new ArrayList<>();
    }

    public void addAccessory(Accessory accessory){
        this.accessories.add(accessory);
    }

    public boolean containsAccessory(String shortName){
        for(Accessory accessory : accessories){
            if(accessory.getShortName().equals(shortName)){
                return true;
            }
        }

        return false;
    }
}
