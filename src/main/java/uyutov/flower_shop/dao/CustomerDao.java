package uyutov.flower_shop.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uyutov.flower_shop.models.users.Customer;
import uyutov.flower_shop.models.users.Provider;

import java.util.Optional;

@Component
public class CustomerDao {
    private final JdbcTemplate jdbcTemplate;

    public CustomerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Customer> getCustomerById(Integer customer_id)
    {
        return Optional.ofNullable(jdbcTemplate.query("Select * from customer where custoer_id = ?",
                new Object[]{customer_id}, new BeanPropertyRowMapper<>(Customer.class))
                .stream().findAny().orElse(null));
    }

    public Optional<Customer> getCustomerByPhoneNumber(String phonenumber)
    {
        return Optional.ofNullable(jdbcTemplate.query("Select * from customer where phone_number=?",
                new Object[]{phonenumber}, new BeanPropertyRowMapper<>(Customer.class)).stream().findAny().orElse(null));
    }

    public void createCustomer(Customer newCustomer)
    {
        jdbcTemplate.update("Insert into customer(name, surname, address, phone_number, password) values (?,?,?,?,?)",
                newCustomer.getName(), newCustomer.getSurname(), newCustomer.getAddress(), newCustomer.getPhone_number(), newCustomer.getPassword());
    }

}
