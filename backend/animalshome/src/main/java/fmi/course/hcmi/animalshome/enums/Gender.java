package fmi.course.hcmi.animalshome.enums;

public enum Gender {
    MALE("male"), FEMALE("female");

    private String value;

    Gender() {

    }

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
