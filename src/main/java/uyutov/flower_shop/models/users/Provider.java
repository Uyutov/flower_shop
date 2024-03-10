package uyutov.flower_shop.models.users;

import java.io.Serializable;

public class Provider implements Serializable {
    private Integer provider_id;
    private String phone_number;
    private String password;
    private final String role = "ROLE_PROVIDER";

    public Provider(Integer provider_id, String phone_number, String password) {
        this.provider_id = provider_id;
        this.phone_number = phone_number;
        this.password = password;
    }

    public Provider() {
    }

    public Integer getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(Integer provider_id) {
        this.provider_id = provider_id;
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
