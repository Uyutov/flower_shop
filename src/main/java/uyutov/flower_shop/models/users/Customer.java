package uyutov.flower_shop.models.users;

import java.io.Serializable;

public class Customer implements Serializable{
    private Integer customer_id;
    private String phone_number;
    private String password;
    private String name;
    private String surname;
    private String address;
    private final String role = "ROLE_CUSTOMER";

    public Customer(Integer customerId, String phoneNumber, String password, String name, String surname, String address) {
        customer_id = customerId;
        phone_number = phoneNumber;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public Customer() {}
    public String getRole() {
        return role;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", phone_number='" + phone_number + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
