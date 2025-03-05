package com.example.touristguidedel3.Model;

public enum Cities {
    KOBENHAVN("København"),
    AARHUS("Aarhus"),
    ODENSE("Odense"),
    AALBORG("Aalborg"),
    ESBJERG("Esbjerg"),
    RANDERS("Randers"),
    KOLDING("Kolding"),
    HORSENS("Horsens"),
    VEJLE("Vejle"),
    ROSKILDE("Roskilde"),
    HELSINGOR("Helsingør"),
    HERNING("Herning"),
    SILKEBORG("Silkeborg"),
    NASTVED("Næstved"),
    FREDERICIA("Fredericia"),
    VIBORG("Viborg"),
    KOGE("Køge"),
    HOLSTEBRO("Holstebro"),
    TAARNBY("Tårnby"),
    SLAGELSE("Slagelse"),
    HILLEROD("Hillerød"),
    SONDERSO("Søndersø"),
    BORNHOLM("Bornholm"),
    SKAGEN("Skagen"),
    BILLUND("Billund"),
    EBELTOFT("Ebeltoft"),
    GRENAA("Grenaa"),
    SONDERBORG("Sønderborg"),
    MON("Møn"),
    CHRISTIANSFELD("Christiansfeld"),
    RONNE("Rønne"),
    MARIBO("Maribo"),
    NAKSKOV("Nakskov");

    private final String screenName;

    Cities(String screenName) {
        this.screenName = screenName;
    }

    public String getScreenName() {
        return screenName;
    }
}
