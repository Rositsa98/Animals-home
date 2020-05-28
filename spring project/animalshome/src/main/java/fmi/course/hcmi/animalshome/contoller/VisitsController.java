package fmi.course.hcmi.animalshome.contoller;

import fmi.course.hcmi.animalshome.enums.VisitRequestAnswer;
import fmi.course.hcmi.animalshome.model.Shelter;
import fmi.course.hcmi.animalshome.model.User;
import fmi.course.hcmi.animalshome.model.VisitRequest;
import fmi.course.hcmi.animalshome.model.authentication.VisitShelterRequest;
import fmi.course.hcmi.animalshome.service.IUserService;
import fmi.course.hcmi.animalshome.service.IVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/visit")
public class VisitsController {

    @Autowired
    private IVisitService visitService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/sendRequest", method= RequestMethod.POST)
    public ResponseEntity<VisitShelterRequest> addRequest(@RequestBody VisitShelterRequest visitRequest){

         VisitRequest addedVisitRequest = visitService.addRequest(convertToVisitRequest(visitRequest));


        if(addedVisitRequest == null){
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}").build(addedVisitRequest.getId()))
                .body(convertToShelterRequest(addedVisitRequest));
    }

    @PutMapping(value = "/answerRequest")
    public VisitRequest answerRequest(@RequestBody VisitRequest visitRequest, VisitRequestAnswer answer){

        if(answer.equals(VisitRequestAnswer.APPROVED)){
           return visitService.approveRequest(visitRequest);
        } else if(answer.equals(VisitRequestAnswer.REJECTED)){
            return visitService.rejectRequest(visitRequest);
        }

        return null;
    }

    @GetMapping(value="/getRequests")
    public List<VisitShelterRequest> getRequests(@RequestHeader String username){
        List<VisitRequest> allRequests = visitService.getAllRequests();
        List<VisitShelterRequest> resultRequests = new ArrayList<>();

        User user = userService.findUserByUsername(username);
        if(user.getRoles().contains("ROLE_SHELTER")) {
            Shelter shelter = (Shelter) user;
            resultRequests = allRequests.stream().filter(r -> r.getShelter().getUsername().equals(username)).
                                map(r -> convertToShelterRequest(r)).
                                collect(Collectors.toList());
        }

        return resultRequests;
    }

    private VisitRequest convertToVisitRequest(VisitShelterRequest visitShelterRequest){
        VisitRequest visitRequest = new VisitRequest();

        visitRequest.setPetName(visitShelterRequest.getPetName());
        visitRequest.setDate(new Date(visitShelterRequest.getDate()));
        visitRequest.setVisitRequestAnswer(visitRequest.getVisitRequestAnswer());

        User user = userService.findUserByUsername(visitShelterRequest.getUserName());
        Shelter shelter = (Shelter) userService.findUserByUsername(visitShelterRequest.getShelterName());
        if(user!=null && shelter!=null) {
            visitRequest.setShelter(shelter);
            visitRequest.setUser(user);
        }

        return visitRequest;
    }

    private VisitShelterRequest convertToShelterRequest(VisitRequest visitRequest){
        VisitShelterRequest visitShelterRequest = new VisitShelterRequest();
        visitShelterRequest.setPetName(visitRequest.getPetName());
        visitShelterRequest.setDate(visitRequest.getDate().toString());
        visitShelterRequest.setUserName(visitRequest.getUser().getUsername());
        visitShelterRequest.setShelterName(visitRequest.getShelter().getUsername());
        visitShelterRequest.setVisitRequestAnswer(visitRequest.getVisitRequestAnswer().getValue());

        return visitShelterRequest;
    }


}
