package fmi.course.hcmi.animalshome.service;

import fmi.course.hcmi.animalshome.dao.PetAdRepository;
import fmi.course.hcmi.animalshome.dto.PetAdDto;
import fmi.course.hcmi.animalshome.entity.PetAd;
import fmi.course.hcmi.animalshome.mapper.Mapper;
import fmi.course.hcmi.animalshome.model.User;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PetAdService {
    private final PetAdRepository petAdRepository;

    @Autowired
    public PetAdService(final PetAdRepository petAdRepository) {
        this.petAdRepository = petAdRepository;
    }

    public PetAdDto createPetAd(final PetAdDto petAdDto) {
        //The user must be registered
        final PetAd petAd = Mapper.INSTANCE.petAdDtoToPetAd(petAdDto);
        petAd.setOwner(getCurrentUser());

        return Mapper.INSTANCE.petAdToPetAdDto(petAdRepository.save(petAd));
    }

    public void deletePetAd(int petAdId) {
        //PetAd petAd = petAdRepository.findById()
    }

    //    public PetAdDto updatePetAd(final PetAdDto petAdDto){
    //
    //    }

    public List<PetAdDto> getAllPetAds() {
        final List<PetAd> petAds = (List) petAdRepository.findAll();

        return Mapper.INSTANCE.petAdsToPetAdsDto(petAds);
    }

    private User getCurrentUser() {
        final User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return user;
    }
    //TODO getPetAdsOfCurrentUser
    //TODO getPetAdsPerPage
    //TODO getAllAds
    //TODO getPetAdByID
    //TODO filterPetAds
    //TODO add ad to favourite
    //TODO remove ad to favourite
    //TODO get user favourite ads
    //TODO delete ad
    //TODO update ad
    //TODO find ad by pet type
}
