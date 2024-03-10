package uyutov.flower_shop.models.users;

import java.io.Serializable;

public class Packer implements Serializable {
    private Integer packer_id;
    private String phone_number;
    private String password;
    private final String role = "ROLE_PACKER";

    public Packer(Integer packer_id, String phone_number, String password) {
        this.packer_id = packer_id;
        this.phone_number = phone_number;
        this.password = password;
    }

    public Packer() {
    }

    public Integer getPacker_id() {
        return packer_id;
    }

    public void setPacker_id(Integer packer_id) {
        this.packer_id = packer_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
}
