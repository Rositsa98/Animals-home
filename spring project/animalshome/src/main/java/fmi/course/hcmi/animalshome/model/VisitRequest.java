package fmi.course.hcmi.animalshome.model;

import fmi.course.hcmi.animalshome.enums.VisitRequestAnswer;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="visit_request", schema="animalsHome")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class VisitRequest {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="pet_name")
    private String petName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user; // sent from user

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="shelter_id")
    private Shelter shelter; //add is for shelter

    @Column(name="date")
    private Date date;

    @Column(name="answer")
    private VisitRequestAnswer visitRequestAnswer;


}
