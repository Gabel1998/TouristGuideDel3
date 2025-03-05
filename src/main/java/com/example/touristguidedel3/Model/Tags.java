package com.example.touristguidedel3.Model;

public enum Tags {
    RESTAURANT("Restaurant"), GRATIS("Gratis"), BIOGRAF("Biograf"), BØRNEVENLIG("Børnevenlig"), MUSEUM("Museum"), NATUR("Natur"), KULTURELT("Kulturelt"), HISTORISK("Historisk"), SHOPPING("Shoping"), MADOGDRIKKE("Mad og drikke");


    private String screenName;

    Tags(String getScreenName) {
        this.screenName = getScreenName;
    }

    public String getScreenName() {
        return screenName;
    }
}
