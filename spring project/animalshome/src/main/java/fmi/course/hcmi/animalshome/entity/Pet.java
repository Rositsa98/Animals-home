package fmi.course.hcmi.animalshome.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "pet", schema = "animalsHome")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_details_id", referencedColumnName = "id")
    private PetDetails petDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_habits_id", referencedColumnName = "id")
    private PetHabits petHabits;

    @OneToOne(mappedBy = "pet")
    private PetAd petAd;

    @Column(name = "pet_description")
    private String petDescription;
}
