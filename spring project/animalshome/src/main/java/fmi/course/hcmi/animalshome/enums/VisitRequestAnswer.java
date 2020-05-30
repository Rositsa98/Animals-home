package fmi.course.hcmi.animalshome.enums;

public enum VisitRequestAnswer {
    APPROVED("approved"), REJECTED("rejected"), NOTDEFINED("not defined");

    private String value;

    VisitRequestAnswer(){
    }

    VisitRequestAnswer(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
