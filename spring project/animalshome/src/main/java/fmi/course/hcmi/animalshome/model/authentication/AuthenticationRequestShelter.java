package fmi.course.hcmi.animalshome.model.authentication;

public class AuthenticationRequestShelter extends AuthenticationRequest {

    private String shelterCode;

    public AuthenticationRequestShelter(){

    }

    public AuthenticationRequestShelter(String username, String password, String shelterCode){
        setUsername(username);
        setPassword(password);
        setShelterCode(shelterCode);
    }

    public String getShelterCode() {
        return shelterCode;
    }

    public void setShelterCode(String shelterCode) {
        this.shelterCode = shelterCode;
    }

}
