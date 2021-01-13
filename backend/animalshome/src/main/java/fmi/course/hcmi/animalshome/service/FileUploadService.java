package fmi.course.hcmi.animalshome.service;

import fmi.course.hcmi.animalshome.exception.DeletePetPhotoException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class FileUploadService {
    private static final String PATH = "src/main/resources/static/petPhotos/";

    public void addPetPhoto(String photoName, MultipartFile file) throws IOException {
        File petPhoto = new File(PATH + photoName);
        petPhoto.createNewFile();
        try (OutputStream out = new FileOutputStream(petPhoto)) {
            out.write(file.getBytes());
        }
    }

    public void deletePetPhoto(String photoName) throws DeletePetPhotoException {
        File petPhoto = new File(PATH + photoName);
        if (!petPhoto.delete()) {
            throw new DeletePetPhotoException("The image can not be deleted!");
        }
    }
}
