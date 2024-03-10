package uyutov.flower_shop.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uyutov.flower_shop.models.users.Admin;
import uyutov.flower_shop.models.users.Packer;
import uyutov.flower_shop.models.users.Provider;

import java.util.List;
import java.util.Optional;

@Component
public class StuffDao {
    private final JdbcTemplate jdbcTemplate;

    public StuffDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Admin related query
    public Optional<Admin> getAdminByPhoneNumber(String phonenumber)
    {
        return Optional.ofNullable(jdbcTemplate.query("Select * from admin where phone_number=?",
                new Object[]{phonenumber}, new BeanPropertyRowMapper<>(Admin.class)).stream().findAny().orElse(null));
    }

    //Provider related query

    public Optional<Provider> getProviderByPhoneNumber(String phonenumber)
    {
        return Optional.ofNullable(jdbcTemplate.query("Select * from provider where phone_number=?",
                new Object[]{phonenumber}, new BeanPropertyRowMapper<>(Provider.class)).stream().findAny().orElse(null));
    }
    public void createProvider(Provider newProvider)
    {
        jdbcTemplate.update("Insert into provider(phone_number, password) values (?,?)",
                newProvider.getPhone_number(), newProvider.getPassword());
    }

    //Packer related query

    public void createPacker(Packer newPacker)
    {
        jdbcTemplate.update("Insert into packer(phone_number, password) values (?,?)",
                newPacker.getPhone_number(), newPacker.getPassword());
    }
    public Optional<Packer> getPackerByPhoneNumber(String phonenumber)
    {
        return Optional.ofNullable(jdbcTemplate.query("Select * from packer where phone_number=?",
                new Object[]{phonenumber}, new BeanPropertyRowMapper<>(Packer.class)).stream().findAny().orElse(null));
    }

    public List<Packer> getAllPackers()
    {
        return jdbcTemplate.query("Select * from packer", new Object[]{}, new BeanPropertyRowMapper<>(Packer.class));
    }

}
