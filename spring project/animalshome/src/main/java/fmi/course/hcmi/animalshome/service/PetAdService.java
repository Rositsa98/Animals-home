package fmi.course.hcmi.animalshome.service;

import fmi.course.hcmi.animalshome.dao.PetAdRepository;
import fmi.course.hcmi.animalshome.dao.UserRepository;
import fmi.course.hcmi.animalshome.dto.FilterCriteria;
import fmi.course.hcmi.animalshome.dto.PetAdDto;
import fmi.course.hcmi.animalshome.dto.PetType;
import fmi.course.hcmi.animalshome.dto.PhotoDto;
import fmi.course.hcmi.animalshome.entity.PetAd;
import fmi.course.hcmi.animalshome.entity.Photo;
import fmi.course.hcmi.animalshome.exception.ResourceNotFoundException;
import fmi.course.hcmi.animalshome.mapper.Mapper;
import fmi.course.hcmi.animalshome.model.User;
import fmi.course.hcmi.animalshome.enums.Gender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PetAdService {
    private final PetAdRepository petAdRepository;
    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;

    @Autowired
    public PetAdService(final PetAdRepository petAdRepository, UserRepository userRepository, FileUploadService fileUploadService) {
        this.petAdRepository = petAdRepository;
        this.userRepository = userRepository;
        this.fileUploadService = fileUploadService;
    }

    public PetAdDto createPetAd(final PetAdDto petAdDto, List<MultipartFile> files) throws IOException {
        //The user must be registered
        final PetAd petAd = Mapper.INSTANCE.petAdDtoToPetAd(petAdDto);
        petAd.setOwner(getCurrentUser());
        List<PhotoDto> photos = new ArrayList<>();
        PhotoDto currentPhoto;
        for (MultipartFile file : files) {
            String photoName = generateUniqueImageName() + getExtensionOfFile(file);
            currentPhoto = new PhotoDto(photoName);
            photos.add(currentPhoto);
            fileUploadService.addPetPhoto(photoName, file);
        }
        petAd.setPhotos(Mapper.INSTANCE.photoDtosToPhotos(photos));
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

    public PetAdDto getPetAdById(long id) throws ResourceNotFoundException {
        Optional<PetAd> petAd = petAdRepository.findById(id);
        if (!petAd.isPresent()) {
            throw new ResourceNotFoundException("The ad is not found");
        }
        return Mapper.INSTANCE.petAdToPetAdDto(petAd.get());
    }

    public void deletePetAd(long id) throws ResourceNotFoundException {
        Optional<PetAd> petAd = petAdRepository.findById(id);
        if (!petAd.isPresent()) {
            throw new ResourceNotFoundException("The ad is not found");
        }
        petAdRepository.deleteById(id);
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
        petAdToBeAdded.ifPresent(currentUserFavoritePets::add);

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

    public List<PetAdDto> getFilteredPetAds(PetType petType, FilterCriteria filterCriteria) {
        List<PetAdDto> petAds = getAllPetAdsByPetType(petType);
        List<PetAdDto> petAdsByGender = new ArrayList<>();
        if (filterCriteria.getGender() != null) {
            petAdsByGender = getPetAdsByGender(petAds, filterCriteria.getGender());
        }
        List<PetAdDto> petAdsByBreed = new ArrayList<>();
        if (filterCriteria.getBreed() != null) {
            petAdsByBreed = getPetAdsByBreed(petAdsByGender, filterCriteria.getBreed());
        }
        List<PetAdDto> petAdsByCity = new ArrayList<>();
        if (filterCriteria.getCity() != null) {
            petAdsByCity = getPetAdsByCity(petAdsByBreed, filterCriteria.getCity());
        }
        List<PetAdDto> petAdsByAge = new ArrayList<>();
        if (filterCriteria.getAge() != 0) {
            petAdsByAge = getPetAdsByPetAge(petAdsByCity, filterCriteria.getAge());
        }

        List<PetAdDto> petAdsByUserType;
        if (!filterCriteria.isFromShelter()) {
            petAdsByUserType = getPetAdsBySingleUser(petAdsByAge);
        } else {
            petAdsByUserType = getPetAdsByShelter(petAdsByAge);
        }
        return petAdsByUserType;
    }

    private List<PetAdDto> getPetAdsByShelter(List<PetAdDto> petAdsDto) {
        List<PetAd> petAds = Mapper.INSTANCE.petAdsDtoToPetAds(petAdsDto);
        List<PetAd> petAdsByShelter = petAds.stream().
                filter(ad -> ad.getOwner().getRoles().equals("ROLE_SHELTER"))
                .collect(Collectors.toList());

        return Mapper.INSTANCE.petAdsToPetAdsDto(petAdsByShelter);
    }

    private List<PetAdDto> getPetAdsBySingleUser(List<PetAdDto> petAdsDto) {
        List<PetAd> petAds = Mapper.INSTANCE.petAdsDtoToPetAds(petAdsDto);
        List<PetAd> petAdsBySingleUser = petAds.stream().
                filter(ad -> ad.getOwner().getRoles().equals("ROLE_USER"))
                .collect(Collectors.toList());

        return Mapper.INSTANCE.petAdsToPetAdsDto(petAdsBySingleUser);
    }

    private List<PetAdDto> getPetAdsByCity(List<PetAdDto> petAdsDto, String city) {
        List<PetAd> petAds = Mapper.INSTANCE.petAdsDtoToPetAds(petAdsDto);
        List<PetAd> petAdsByCity = petAds.stream().
                filter(ad -> ad.getPet().getPetDetails().getCity().equals(city))
                .collect(Collectors.toList());

        return Mapper.INSTANCE.petAdsToPetAdsDto(petAdsByCity);
    }

    private List<PetAdDto> getPetAdsByGender(List<PetAdDto> petAdsDto, Gender gender) {
        List<PetAd> petAds = Mapper.INSTANCE.petAdsDtoToPetAds(petAdsDto);
        List<PetAd> petAdsByGender = petAds.stream().
                filter(ad -> ad.getPet().getPetDetails().getGender().equals(gender))
                .collect(Collectors.toList());

        return Mapper.INSTANCE.petAdsToPetAdsDto(petAdsByGender);
    }

    private List<PetAdDto> getPetAdsByPetAge(List<PetAdDto> petAdsDto, int age) {
        List<PetAd> petAds = Mapper.INSTANCE.petAdsDtoToPetAds(petAdsDto);
        List<PetAd> petAdsByPetAge = petAds.stream().
                filter(ad -> ad.getPet().getPetDetails().getAge() == age)
                .collect(Collectors.toList());

        return Mapper.INSTANCE.petAdsToPetAdsDto(petAdsByPetAge);
    }

    public List<PetAdDto> getPetAdsByBreed(List<PetAdDto> petAdsDto, String breed) {
        List<PetAd> petAds = Mapper.INSTANCE.petAdsDtoToPetAds(petAdsDto);
        List<PetAd> petAdsByPetBreed = petAds.stream().
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

    private String generateUniqueImageName() {
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID;
    }

    private String getExtensionOfFile(MultipartFile file) {
        String name = file.getOriginalFilename();
        if (name.equals("blob")) {
            return ".jpg";
        }
        String extension = "." + name.split("\\.")[1];
        return extension;
    }

    //TODO getPetAdsPerPage
}
