package uyutov.flower_shop.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uyutov.flower_shop.models.Flower;

import java.util.List;
import java.util.Optional;

@Component
public class FlowerDao {
    private final JdbcTemplate jdbcTemplate;

    public FlowerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Flower> getAllFlowers()
    {
        return jdbcTemplate.query("Select * from flower",
                new Object[]{}, new BeanPropertyRowMapper<>(Flower.class));
    }
    public Optional<Flower> getFlowerById(int flower_id)
    {
        return Optional.ofNullable(jdbcTemplate.query("Select * from Flower where flower_id=?",
                new Object[]{flower_id}, new BeanPropertyRowMapper<>(Flower.class))
                .stream().findAny().orElse(null));
    }
    public void createFlower(Flower newFlower)
    {
        jdbcTemplate.update("Insert into flower(name, amount, price, length, color, icon) values (?,?,?,?,?,?)",
                newFlower.getName(), newFlower.getAmount(), newFlower.getPrice(), newFlower.getLength(), newFlower.getColor(), newFlower.getIcon());
    }
    public List<Flower> getAllFlowersByKeyword(String keyword)
    {
        keyword = "%"+keyword+"%";
        return jdbcTemplate.query("Select * from flower where lower(name) like lower(?)", new Object[]{keyword}, new BeanPropertyRowMapper<>(Flower.class));
    }

    public Flower getMostExpensiveFlower()
    {
        return jdbcTemplate.query("Select name, length, color, price from flower order by price desc;",
                        new Object[]{}, new BeanPropertyRowMapper<>(Flower.class))
                .stream().findAny().orElse(null);
    }
}























