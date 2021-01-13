package fmi.course.hcmi.animalshome.dao;

import fmi.course.hcmi.animalshome.entity.PetAd;

import fmi.course.hcmi.animalshome.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetAdRepository extends CrudRepository<PetAd, Long> {
    List<PetAd> findByOwner(User user);

}
