package com.example.qcassistant.domain.item.accessory;

public enum MedidataAccessory {

    HEADPHONES("Medidata\\s*Universal\\s*Headphones\\s*54843", "Headphones"),
    STYLUS("Medidata\\s*Universal\\s*Tablet\\s*Stylus\\s*54845", "Stylus");

    private String regexPattern;

    private String shortName;

    MedidataAccessory(String regexPattern, String shortName){
        this.regexPattern = regexPattern;
        this.shortName = shortName;
    }

    public String getRegexPattern() {
        return regexPattern;
    }

    public String getShortName() {
        return shortName;
    }
}