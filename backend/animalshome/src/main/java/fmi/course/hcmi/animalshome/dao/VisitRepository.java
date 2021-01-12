package fmi.course.hcmi.animalshome.dao;

import fmi.course.hcmi.animalshome.model.VisitRequest;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<VisitRequest, Long> {

}
