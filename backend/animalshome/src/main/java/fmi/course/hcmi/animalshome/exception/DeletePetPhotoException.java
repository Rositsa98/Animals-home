package fmi.course.hcmi.animalshome.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DeletePetPhotoException extends Exception {
   public DeletePetPhotoException(String message){
        super(message);
    }
}
