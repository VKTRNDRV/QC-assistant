package com.example.qcassistant.domain.item.device.ios.ipad;

import com.example.qcassistant.domain.enums.item.ConnectorType;

public enum IqviaIPad {

    PRO_2ND_GEN("IQVIA\\s*Apple\\s*iPad\\s*Pro\\s*2nd\\s*Gen.{0,16}\\s*Tablet\\s*Shell\\s*(?<serial>[A-Z0-9]{12})",
            "iPad Pro 2nd Gen", ConnectorType.TYPE_C),
    FIFTH_GEN("IQVIA\\s*Cellular\\s*Apple\\s*iPad\\s*5th\\s*Gen.{0,16}Tablet\\s*Shell\\s*(?<serial>[A-Z0-9]{12})",
            "iPad 5th Gen", ConnectorType.LIGHTNING),
    SIXTH_GEN("IQVIA\\s*Cellular\\s*Apple\\s*iPad\\s*6th\\s*Gen.{0,16}Tablet\\s*Shell\\s*(?<serial>[A-Z0-9]{12})",
            "iPad 6th Gen", ConnectorType.LIGHTNING),
    SEVENTH_GEN("IQVIA\\s*Cellular\\s*Apple\\s*iPad\\s*7th\\s*Gen.{0,16}Tablet\\s*Shell\\s*(?<serial>[A-Z0-9]{12})",
            "iPad 7th Gen", ConnectorType.LIGHTNING),
    EIGHT_GEN("IQVIA\\s*Cellular\\s*Apple\\s*iPad\\s*8th\\s*Gen.{0,16}Tablet\\s*Shell\\s*(?<serial>[A-Z0-9]{12})",
            "iPad 8th Gen", ConnectorType.LIGHTNING),
    NINTH_GEN("IQVIA\\s*Cellular\\s*Apple\\s*iPad\\s*9th\\s*Gen.{0,16}Tablet\\s*Shell\\s*(?<serial>[A-Z0-9]{10})",
            "iPad 9th Gen", ConnectorType.LIGHTNING),
    AIR_2("Apple\\s*iPad\\s*Air\\s*2.{0,16}Tablet\\s*Shell\\s*(?<serial>[A-Z0-9]{12})",
            "iPad Air 2", ConnectorType.LIGHTNING)
    ;

    private String regexPattern;
    private String shortName;
    private ConnectorType connectorType;

    public static final String SERIAL_GROUP_NAME = "serial";

    IqviaIPad(String regexPattern, String shortName, ConnectorType connectorType) {
        this.regexPattern = regexPattern;
        this.shortName = shortName;
        this.connectorType = connectorType;
    }

    public String getRegexPattern() {
        return regexPattern;
    }

    public String getShortName() {
        return shortName;
    }

    public ConnectorType getConnectorType() {
        return connectorType;
    }
}
