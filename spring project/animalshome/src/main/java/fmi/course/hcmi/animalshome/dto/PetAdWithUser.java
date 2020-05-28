package fmi.course.hcmi.animalshome.dto;

import fmi.course.hcmi.animalshome.model.UserInfo;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class PetAdWithUser {
    private PetAdDto petAdDto;
    private UserInfo owner;
}
