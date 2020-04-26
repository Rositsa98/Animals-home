package fmi.course.hcmi.animalshome.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    //@ManyToOne
    //@JoinColumn(name = "user_id")
    //    private User owner;

    @OneToOne(mappedBy = "pet")
    private PetAd petAd;

    //    @OneToMany
    //    @JoinColumn(name = "pet_id")
    //    @OnDelete(action = OnDeleteAction.CASCADE)
    //    private List<Photo> photos;

    @Column(name = "pet_description")
    private String petDescription;
}
