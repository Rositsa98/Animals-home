package fmi.course.hcmi.animalshome.model;

public class SingleUserInfo extends UserInfo {

    public SingleUserInfo(){
        super();
    }

    public SingleUserInfo(final String username, final String address, final String email, final String phoneNumber, final String roles) {
        super(username, address, email, phoneNumber, roles);
    }
}
