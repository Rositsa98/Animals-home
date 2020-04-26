package fmi.course.hcmi.animalshome.dao;

import fmi.course.hcmi.animalshome.entity.PetAd;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetAdRepository extends CrudRepository<PetAd, Long> {
}
