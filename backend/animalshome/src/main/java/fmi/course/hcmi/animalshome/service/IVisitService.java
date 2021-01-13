package fmi.course.hcmi.animalshome.service;

import fmi.course.hcmi.animalshome.model.VisitRequest;

import java.util.List;

public interface IVisitService {

    List<VisitRequest> getAllRequests();

    VisitRequest addRequest(VisitRequest visitRequest);

    VisitRequest approveRequest(VisitRequest visitRequest);

    VisitRequest rejectRequest(VisitRequest visitRequest);

    boolean checkIfRequestNotCoincideWithOthers(VisitRequest visitRequest);

    void sendUserNotification(VisitRequest visitRequest);

    void sendShelterNotification(VisitRequest visitRequest);

}
