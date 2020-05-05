package fmi.course.hcmi.animalshome.contoller;

import fmi.course.hcmi.animalshome.dto.PetAdDto;
import fmi.course.hcmi.animalshome.dto.PetType;
import fmi.course.hcmi.animalshome.enums.Gender;
import fmi.course.hcmi.animalshome.service.PetAdService;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<PetAdDto> createPetAd(@Valid @RequestBody final PetAdDto petAdDto) {
        return new ResponseEntity<>(petAdService.createPetAd(petAdDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<PetAdDto>> getAllPetAds() {
        return new ResponseEntity<>(petAdService.getAllPetAds(), HttpStatus.OK);
    }

    @GetMapping(value = "/all/user")
    public ResponseEntity<List<PetAdDto>> getAllPetAdsOfCurrentUser() {
        return new ResponseEntity<>(petAdService.getPetAdsOfCurrentUser(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PetAdDto> getPetAdById(@PathVariable long id) {
        return new ResponseEntity<>(petAdService.getPetAdById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/all/type")
    public ResponseEntity<List<PetAdDto>> getAllPetAdsByPetType(@RequestParam PetType petType) {
        return new ResponseEntity<>(petAdService.getAllPetAdsByPetType(petType), HttpStatus.OK);
    }

    @GetMapping(value = "/all/user/favorite")
    public ResponseEntity<List<PetAdDto>> getCurrentUserFavoritePetAds() {
        return new ResponseEntity<>(petAdService.getCurrentUserFavoritePetAds(), HttpStatus.OK);
    }

    @GetMapping(value = "/all/shelter")
    public ResponseEntity<List<PetAdDto>> getPetAdsByShelter() {
        return new ResponseEntity<>(petAdService.getPetAdsByShelter(), HttpStatus.OK);
    }

    @GetMapping(value = "/all/single-user")
    public ResponseEntity<List<PetAdDto>> getPetAdsBySingleUser() {
        return new ResponseEntity<>(petAdService.getPetAdsBySingleUser(), HttpStatus.OK);
    }

    @GetMapping(value = "/all/city")
    public ResponseEntity<List<PetAdDto>> getPetAdsByCity(@RequestParam String city) {
        return new ResponseEntity<>(petAdService.getPetAdsByCity(city), HttpStatus.OK);
    }

    @GetMapping(value = "/all/gender")
    public ResponseEntity<List<PetAdDto>> getPetAdsByGender(@RequestParam Gender gender) {
        return new ResponseEntity<>(petAdService.getPetAdsByGender(gender), HttpStatus.OK);
    }

    @GetMapping(value = "/all/age")
    public ResponseEntity<List<PetAdDto>> getPetAdsByPetAge(@RequestParam int age) {
        return new ResponseEntity<>(petAdService.getPetAdsByPetAge(age), HttpStatus.OK);
    }

    @GetMapping(value = "/all/breed")
    public ResponseEntity<List<PetAdDto>> getPetAdsByBreed(@RequestParam String breed) {
        return new ResponseEntity<>(petAdService.getPetAdsByBreed(breed), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<PetAdDto> updatePetAd(@PathVariable long id, @RequestBody PetAdDto petAdDto) {
        return new ResponseEntity<>(petAdService.updatePetAd(id, petAdDto), HttpStatus.OK);
    }

    @PatchMapping(value = "/remove/user/favorite/{id}")
    public ResponseEntity<Void> removePetAdFromFavorites(@PathVariable long id) {
        petAdService.removePetAdFromFavorites(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/add/user/favorite/{id}")
    public ResponseEntity<Void> addPetAdToFavorites(@PathVariable long id) {
        petAdService.addPetAdToFavorites(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePetAd(@PathVariable long id) {
        petAdService.deletePetAd(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
