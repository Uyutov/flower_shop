package uyutov.flower_shop.models.users;

import java.io.Serializable;

public class Admin implements Serializable {
    private Integer admin_id;
    private String phone_number;
    private String password;
    private final String role = "ROLE_ADMIN";

    public Admin(Integer adminId, String phoneNumber, String password) {
        admin_id = adminId;
        phone_number = phoneNumber;
        this.password = password;
    }

    public Admin() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public String getRole() {
        return role;
    }
}
