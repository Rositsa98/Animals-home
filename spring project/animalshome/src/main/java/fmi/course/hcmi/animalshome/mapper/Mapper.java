package fmi.course.hcmi.animalshome.mapper;

import fmi.course.hcmi.animalshome.dto.PetAdDto;
import fmi.course.hcmi.animalshome.dto.PetDetailsDto;
import fmi.course.hcmi.animalshome.dto.PetDto;
import fmi.course.hcmi.animalshome.dto.PetHabitsDto;
import fmi.course.hcmi.animalshome.dto.PhotoDto;
import fmi.course.hcmi.animalshome.entity.Pet;
import fmi.course.hcmi.animalshome.entity.PetAd;
import fmi.course.hcmi.animalshome.entity.PetDetails;
import fmi.course.hcmi.animalshome.entity.PetHabits;
import fmi.course.hcmi.animalshome.entity.Photo;

import java.util.List;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper
public interface Mapper {
    Mapper INSTANCE = Mappers.getMapper(Mapper.class);

    @Mapping(source = "petDetailsDto", target = "petDetails")
    @Mapping(source = "petHabitsDto", target = "petHabits")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "petAd", ignore = true)
    Pet petDtoToPet(PetDto petDto);

    @Mapping(source = "petDetails", target = "petDetailsDto")
    @Mapping(source = "petHabits", target = "petHabitsDto")
    PetDto petToPetDto(Pet pet);

    List<PetAdDto> petAdsToPetAdsDto(List<PetAd> petAds);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pet", ignore = true)
    PetHabits petHabitsDtoToPetHabits(PetHabitsDto petHabitsDto);

    PetHabitsDto petHabitsToPetHabitsDto(PetHabits petHabits);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pet", ignore = true)
    PetDetails petDetailsDtoToPetDetails(PetDetailsDto petDetailsDto);

    PetDetailsDto petDetailsDtoToPetDetails(PetDetails petDetails);

    @Mapping(source = "petDto", target = "pet")
    @Mapping(source = "photosDto", target = "photos")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "owner", ignore = true)
    PetAd petAdDtoToPetAd(PetAdDto petAdDto);

    @Mapping(source = "pet", target = "petDto")
    @Mapping(source = "photos", target = "photosDto")
    PetAdDto petAdToPetAdDto(PetAd petAd);

    @Mapping(target = "id", ignore = true)
    Photo photoDtoToPhoto(PhotoDto photoDto);

    PhotoDto photoDtoToPhoto(Photo photo);

    List<Photo> photoDtosToPhotos(List<PhotoDto> photoDtos);

    List<PhotoDto> photosToPhotoDtos(List<Photo> photos);
}
