package fmi.course.hcmi.animalshome.contoller;

import fmi.course.hcmi.animalshome.dto.PetAdDto;
import fmi.course.hcmi.animalshome.service.impl.PetAdService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pet/ad")
public class PetAdController {
    private final PetAdService petAdService;

    @Autowired
    PetAdController(final PetAdService petAdService) {
        this.petAdService = petAdService;
    }

    @PostMapping
    public ResponseEntity<PetAdDto> createPetAd(@RequestBody PetAdDto petAdDto) {
        return new ResponseEntity<>(petAdService.createPetAd(petAdDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PetAdDto>> getAllPetAds(){
        return new ResponseEntity<>(petAdService.getAllPetAds(), HttpStatus.OK);
    }
}
