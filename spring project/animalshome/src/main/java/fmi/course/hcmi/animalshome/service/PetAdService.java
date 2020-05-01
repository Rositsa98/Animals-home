package fmi.course.hcmi.animalshome.service;

import fmi.course.hcmi.animalshome.dao.PetAdRepository;
import fmi.course.hcmi.animalshome.dto.PetAdDto;
import fmi.course.hcmi.animalshome.entity.PetAd;
import fmi.course.hcmi.animalshome.mapper.Mapper;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetAdService {
    private final PetAdRepository petAdRepository;

    @Autowired
    public PetAdService(final PetAdRepository petAdRepository) {
        this.petAdRepository = petAdRepository;
    }

    public PetAdDto createPetAd(final PetAdDto petAdDto) {
        PetAd petAd = Mapper.INSTANCE.petAdDtoToPetAd(petAdDto);

        return Mapper.INSTANCE.petAdToPetAdDto(petAdRepository.save(petAd));
    }

    public void deletePetAd(int petAdId) {
        //PetAd petAd = petAdRepository.findById()
    }

    //    public PetAdDto updatePetAd(final PetAdDto petAdDto){
    //
    //    }

    public List<PetAdDto> getAllPetAds() {
        List<PetAd> petAds = (List) petAdRepository.findAll();
        return Mapper.INSTANCE.petAdsToPetAdsDto(petAds);
    }

    //TODO getPetAdsByUsername
    //TODO getPetAdsPerPage
    //TODO filterPetAds
}
