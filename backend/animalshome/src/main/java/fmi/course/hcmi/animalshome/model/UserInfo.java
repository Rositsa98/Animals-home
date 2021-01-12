package fmi.course.hcmi.animalshome.model;

import java.util.Objects;

public abstract class UserInfo {
    private String username;
    private String address;
    private String email;
    private String phoneNumber;
    private String roles;

    UserInfo(){}

    public UserInfo(final String username, final String address, final String email, final String phoneNumber, final String roles) {
        this.username = username;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(final String roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final UserInfo userInfo = (UserInfo) o;
        return username.equals(userInfo.username) && address.equals(userInfo.address) && email.equals(userInfo.email) &&
                phoneNumber.equals(userInfo.phoneNumber) && roles.equals(userInfo.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, address, email, phoneNumber, roles);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserInfo{");
        sb.append("username='")
                .append(username)
                .append('\'');
        sb.append(", address='")
                .append(address)
                .append('\'');
        sb.append(", email='")
                .append(email)
                .append('\'');
        sb.append(", phoneNumber='")
                .append(phoneNumber)
                .append('\'');
        sb.append(", roles='")
                .append(roles)
                .append('\'');
        sb.append('}');
        return sb.toString();
    }
}
