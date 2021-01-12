package fmi.course.hcmi.animalshome.model.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisitShelterRequest implements Serializable {

    private String petName;
    private String userName;
    private String shelterName;
    private String date;
    private String visitRequestAnswer;

}
