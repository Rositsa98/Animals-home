package fmi.course.hcmi.animalshome.entity;

import fmi.course.hcmi.animalshome.dto.Gender;
import fmi.course.hcmi.animalshome.dto.PetType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "pet_details", schema = "dko8s14veb65m")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PetDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "pet_name")
    private String petName;

    @Column
    private String breed;

    @Column
    private String color;

    @Column
    private String city;

    @Column
    private int age;

    @Column
    private int months;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private double weight;

    @Column(name = "pet_type")
    @Enumerated(EnumType.STRING)
    private PetType petType;

    @OneToOne(mappedBy = "petDetails")
    private Pet pet;
}
