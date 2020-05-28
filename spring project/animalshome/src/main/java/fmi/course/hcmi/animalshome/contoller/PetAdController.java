package fmi.course.hcmi.animalshome.contoller;

import fmi.course.hcmi.animalshome.dto.FilterCriteria;
import fmi.course.hcmi.animalshome.dto.PetAdDto;
import fmi.course.hcmi.animalshome.service.impl.PetAdService;
import fmi.course.hcmi.animalshome.dto.PetAdWithUser;
import fmi.course.hcmi.animalshome.dto.PetType;
import fmi.course.hcmi.animalshome.dto.PhotoDto;
import fmi.course.hcmi.animalshome.exception.ResourceNotFoundException;

import java.io.IOException;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin()
@RestController
@RequestMapping("/api/pet/ad")
public class PetAdController {
    private final PetAdService petAdService;

    @Autowired
    PetAdController(final PetAdService petAdService) {
        this.petAdService = petAdService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PetAdDto> createPetAd(@Valid @RequestPart("petAdDto") final PetAdDto petAdDto,
                                                @RequestPart("files") List<MultipartFile> files) throws IOException {
        return new ResponseEntity<>(petAdService.createPetAd(petAdDto, files), HttpStatus.CREATED);
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
    public ResponseEntity<PetAdDto> getPetAdById(@PathVariable long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(petAdService.getPetAdById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/view/{id}")
    public ResponseEntity<PetAdWithUser> getPetAdByIdWithUserInfo(@PathVariable long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(petAdService.getPedAdWithUserInfo(id), HttpStatus.OK);
    }

    @GetMapping(value = "/all/type")
    public ResponseEntity<List<PetAdDto>> getAllPetAdsByPetType(@RequestParam PetType petType) {
        return new ResponseEntity<>(petAdService.getAllPetAdsByPetType(petType), HttpStatus.OK);
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<PetAdDto>> getFilteredPetAds(@RequestParam PetType petType, @RequestBody FilterCriteria filterCriteria) {
        return new ResponseEntity<>(petAdService.getFilteredPetAds(petType, filterCriteria), HttpStatus.OK);
    }

    @GetMapping(value = "/all/user/favorite")
    public ResponseEntity<List<PetAdDto>> getCurrentUserFavoritePetAds() {
        return new ResponseEntity<>(petAdService.getCurrentUserFavoritePetAds(), HttpStatus.OK);
    }

    //TODO remove existing photos and add new photos
    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PetAdDto> updatePetAd(@PathVariable long id,
                                                @RequestPart("petAdDto") final PetAdDto petAdDto,
                                                @RequestPart("deletedPhotos") final List<PhotoDto> deletedPhotos,
                                                @RequestPart("files") final List<MultipartFile> files) {
        return new ResponseEntity<>(petAdService.updatePetAd(id, petAdDto), HttpStatus.OK);
    }

    @PatchMapping(value = "/remove/user/favorite")
    public ResponseEntity<Void> removePetAdFromFavorites(@RequestParam long id) {
        petAdService.removePetAdFromFavorites(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/add/user/favorite")
    public ResponseEntity<Void> addPetAdToFavorites(@RequestParam long id) {
        petAdService.addPetAdToFavorites(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePetAd(@PathVariable long id) throws ResourceNotFoundException {
        petAdService.deletePetAd(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}