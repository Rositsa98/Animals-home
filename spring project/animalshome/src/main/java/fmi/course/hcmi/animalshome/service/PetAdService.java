package fmi.course.hcmi.animalshome.service;

import fmi.course.hcmi.animalshome.dao.PetAdRepository;
import fmi.course.hcmi.animalshome.dao.UserRepository;
import fmi.course.hcmi.animalshome.dto.PetAdDto;
import fmi.course.hcmi.animalshome.dto.PetType;
import fmi.course.hcmi.animalshome.entity.PetAd;
import fmi.course.hcmi.animalshome.enums.Gender;
import fmi.course.hcmi.animalshome.mapper.Mapper;
import fmi.course.hcmi.animalshome.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PetAdService {
    private final PetAdRepository petAdRepository;
    private final UserRepository userRepository;

    @Autowired
    public PetAdService(final PetAdRepository petAdRepository, UserRepository userRepository) {
        this.petAdRepository = petAdRepository;
        this.userRepository = userRepository;
    }

    public PetAdDto createPetAd(final PetAdDto petAdDto) {
        //The user must be registered
        final PetAd petAd = Mapper.INSTANCE.petAdDtoToPetAd(petAdDto);
        petAd.setOwner(getCurrentUser());

        return Mapper.INSTANCE.petAdToPetAdDto(petAdRepository.save(petAd));
    }

    public List<PetAdDto> getAllPetAds() {
        final List<PetAd> petAds = (List<PetAd>) petAdRepository.findAll();
        return Mapper.INSTANCE.petAdsToPetAdsDto(petAds);
    }

    public List<PetAdDto> getPetAdsOfCurrentUser() {
        User currentUser = getCurrentUser();
        List<PetAd> petAds = petAdRepository.findByOwner(currentUser);

        return Mapper.INSTANCE.petAdsToPetAdsDto(petAds);
    }

    public PetAdDto getPetAdById(long id) {
        Optional<PetAd> petAd = petAdRepository.findById(id);
        return Mapper.INSTANCE.petAdToPetAdDto(petAd.get());
    }

    public void deletePetAd(long id) {
        Optional<PetAd> petAd = petAdRepository.findById(id);
        if (petAd.isPresent()) {
            petAdRepository.deleteById(id);
        }
    }

    public List<PetAdDto> getAllPetAdsByPetType(PetType petType) {
        List<PetAd> allPetAds = (List<PetAd>) petAdRepository.findAll();
        List<PetAd> petAdsByPetType = allPetAds.stream()
                .filter(ad -> ad.getPet().getPetDetails().getPetType().equals(petType))
                .collect(Collectors.toList());

        return Mapper.INSTANCE.petAdsToPetAdsDto(petAdsByPetType);
    }

    public List<PetAdDto> getCurrentUserFavoritePetAds() {
        User currentUser = getCurrentUser();
        return Mapper.INSTANCE.petAdsToPetAdsDto(currentUser.getFavouritePets());
    }

    public void removePetAdFromFavorites(long id) {
        User currentUser = getCurrentUser();
        Optional<PetAd> petAdToBeRemoved = petAdRepository.findById(id);
        List<PetAd> currentUserFavoritePets = currentUser.getFavouritePets();
        if (petAdToBeRemoved.isPresent()) {
            for (int i = 0; i < currentUserFavoritePets.size(); i++) {
                if (currentUserFavoritePets.get(i).getId() == id) {
                    currentUserFavoritePets.remove(i);
                    break;
                }
            }
        }

        currentUser.setFavouritePets(currentUserFavoritePets);
        userRepository.save(currentUser);
    }

    public void addPetAdToFavorites(long id) {
        User currentUser = getCurrentUser();
        Optional<PetAd> petAdToBeAdded = petAdRepository.findById(id);
        List<PetAd> currentUserFavoritePets = currentUser.getFavouritePets();
        if (petAdToBeAdded.isPresent()) {
            currentUserFavoritePets.add(petAdToBeAdded.get());
        }

        currentUser.setFavouritePets(currentUserFavoritePets);
        userRepository.save(currentUser);
    }

    public PetAdDto updatePetAd(long id, PetAdDto petAdDto) {
        Optional<PetAd> petAd = petAdRepository.findById(id);
        if (petAd.isPresent()) {
            petAd.get().setPet(Mapper.INSTANCE.petDtoToPet(petAdDto.getPetDto()));
            petAd.get().setPhotos(Mapper.INSTANCE.photoDtosToPhotos(petAdDto.getPhotosDto()));
        }
        return Mapper.INSTANCE.petAdToPetAdDto(petAd.get());
    }

    public List<PetAdDto> getPetAdsByShelter() {
        List<PetAd> allPetAds = (List<PetAd>) petAdRepository.findAll();
        List<PetAd> petAdsByShelter = allPetAds.stream().
                filter(ad -> ad.getOwner().getRoles().equals("ROLE_SHELTER"))
                .collect(Collectors.toList());

        return Mapper.INSTANCE.petAdsToPetAdsDto(petAdsByShelter);
    }

    public List<PetAdDto> getPetAdsBySingleUser() {
        List<PetAd> allPetAds = (List<PetAd>) petAdRepository.findAll();
        List<PetAd> petAdsBySingleUser = allPetAds.stream().
                filter(ad -> ad.getOwner().getRoles().equals("ROLE_USER"))
                .collect(Collectors.toList());

        return Mapper.INSTANCE.petAdsToPetAdsDto(petAdsBySingleUser);
    }

    public List<PetAdDto> getPetAdsByCity(String city) {
        List<PetAd> allPetAds = (List<PetAd>) petAdRepository.findAll();
        List<PetAd> petAdsByCity = allPetAds.stream().
                filter(ad -> ad.getPet().getPetDetails().getCity().equals(city))
                .collect(Collectors.toList());

        return Mapper.INSTANCE.petAdsToPetAdsDto(petAdsByCity);
    }

    public List<PetAdDto> getPetAdsByGender(Gender gender) {
        List<PetAd> allPetAds = (List<PetAd>) petAdRepository.findAll();
        List<PetAd> petAdsByGender = allPetAds.stream().
                filter(ad -> ad.getPet().getPetDetails().getGender().equals(gender))
                .collect(Collectors.toList());

        return Mapper.INSTANCE.petAdsToPetAdsDto(petAdsByGender);
    }

    public List<PetAdDto> getPetAdsByPetAge(int age) {
        List<PetAd> allPetAds = (List<PetAd>) petAdRepository.findAll();
        List<PetAd> petAdsByPetAge = allPetAds.stream().
                filter(ad -> ad.getPet().getPetDetails().getAge() == age)
                .collect(Collectors.toList());

        return Mapper.INSTANCE.petAdsToPetAdsDto(petAdsByPetAge);
    }

    public List<PetAdDto> getPetAdsByBreed(String breed) {
        List<PetAd> allPetAds = (List<PetAd>) petAdRepository.findAll();
        List<PetAd> petAdsByPetBreed = allPetAds.stream().
                filter(ad -> ad.getPet().getPetDetails().getBreed().equals(breed))
                .collect(Collectors.toList());

        return Mapper.INSTANCE.petAdsToPetAdsDto(petAdsByPetBreed);
    }

    private User getCurrentUser() {
        final User user;
        user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return user;
    }

    //TODO getPetAdsPerPage
}
