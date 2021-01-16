package fmi.course.hcmi.animalshome.service.impl;

import fmi.course.hcmi.animalshome.dao.UserRepository;
import fmi.course.hcmi.animalshome.dao.VisitRepository;
import fmi.course.hcmi.animalshome.enums.VisitRequestAnswer;
import fmi.course.hcmi.animalshome.model.Shelter;
import fmi.course.hcmi.animalshome.model.User;
import fmi.course.hcmi.animalshome.model.VisitRequest;
import fmi.course.hcmi.animalshome.notifications.Notification;
import fmi.course.hcmi.animalshome.notifications.NotificationsServiceClient;
import fmi.course.hcmi.animalshome.service.IVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitService implements IVisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationsServiceClient notificationsServiceClient;

    @Override
    public List<VisitRequest> getAllRequests() {
        Iterable<VisitRequest> requests = visitRepository.findAll();
        List<VisitRequest> result = new ArrayList<>();
        requests.forEach(result::add);

        return result;
    }

    @Override
    public synchronized VisitRequest addRequest(VisitRequest visitRequest) {
        //TODO add synchronize logic
        //TODO check if okay and if not - send notification
        //TODO else send shelter notification for new request
        //TODO add to db
        //TODO - check if such pet is part of shelter

        if(petExists(visitRequest) && !checkIfRequestNotCoincideWithOthers(visitRequest)) {
            visitRequest.setVisitRequestAnswer(VisitRequestAnswer.NOTDEFINED);
            sendShelterNotification(visitRequest);
        } else{
            visitRequest.setVisitRequestAnswer(VisitRequestAnswer.REJECTED);
            sendUserNotification(visitRequest);
        }

        return visitRepository.save(visitRequest);

    }

    @Override
    public VisitRequest approveRequest(VisitRequest visitRequest) {
        visitRequest.setVisitRequestAnswer(VisitRequestAnswer.APPROVED);
        return visitRepository.save(visitRequest);
    }

    @Override
    public VisitRequest rejectRequest(VisitRequest visitRequest) {
        visitRequest.setVisitRequestAnswer(VisitRequestAnswer.REJECTED);
        return visitRepository.save(visitRequest);
    }

    @Override
    public boolean checkIfRequestNotCoincideWithOthers( VisitRequest visitRequest) {

        Shelter shelter = visitRequest.getShelter();

        if(shelter != null){
            List<VisitRequest> existingVisitRequests = shelter.getVisitRequests();

            List<VisitRequest> foundCoincidents = existingVisitRequests.stream().filter(req -> (req.getDate().equals(visitRequest.getDate()) &&
                    req.getPetName().equals(visitRequest.getPetName()))).collect(Collectors.toList());

            return foundCoincidents != null && foundCoincidents.size() > 0;
        }

        return false;
    }

    @Override
    public void sendUserNotification(VisitRequest visitRequest) {

        User user = visitRequest.getUser();

        if(user != null){
            String notificationContent = "Your visit request for: " + visitRequest.getPetName()
                    + " in shelter: " + visitRequest.getShelter().getUsername() + " for date: " +
                    visitRequest.getDate() + " was " + visitRequest.getVisitRequestAnswer();

            Notification notification = new Notification(user.getId(), user.getUsername(),
                    notificationContent);
            this.notificationsServiceClient.sendNotification(notification);
        }
    }

    @Override
    public void sendShelterNotification(VisitRequest visitRequest) {

        Shelter shelter = visitRequest.getShelter();

        if(shelter != null) {
            String notificationContent = "You have a new visit request for: "
                    + visitRequest.getPetName() + " for date: " + visitRequest.getDate()
                    + " from user: " + visitRequest.getUser().getUsername();

            Notification notification = new Notification(shelter.getId(),
                    shelter.getUsername(), notificationContent);
            this.notificationsServiceClient.sendNotification(notification);
        }
    }

    private boolean petExists(VisitRequest visitRequest){
        //TODO implement when can retrieve pets
        return true;
    }

}
