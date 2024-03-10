package uyutov.flower_shop.dao;

import org.springframework.data.relational.core.sql.Select;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import uyutov.flower_shop.models.Order;
import uyutov.flower_shop.models.OrderState;

import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.*;

@Component
public class OrderDao {
    private final JdbcTemplate jdbcTemplate;

    public OrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createOrder(Order newOrder)
    {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("Insert into \"order\"(total_price) values (?);");
            ps.setString(1, newOrder.getSumTotal_price().toString());
            return ps;
        }, keyHolder);

        return (int) keyHolder.getKey();
    }
    public List<Order> getAllOrdersOfCustomer(Integer customer_id)
    {
        return jdbcTemplate.query("Select \"order\".* from customer " +
                "left join customer_order on customer_order.customer_id = customer.customer_id " +
                "left join \"order\" on customer_order.order_id = \"order\".order_id " +
                "where customer.customer_id=?", new Object[]{customer_id}, new BeanPropertyRowMapper<>(Order.class));
    }
    public void addOrderToCustomer(int customer_id, Order newOrder)
    {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(
                "Insert into \"order\"(total_price) values (?);", Types.FLOAT);
        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);

        PreparedStatementCreator preparedStatementCreator = preparedStatementCreatorFactory.newPreparedStatementCreator(Collections.singletonList(newOrder.getSumTotal_price()));

        jdbcTemplate.update(preparedStatementCreator, keyHolder);

        System.out.println("Key holder value: " + keyHolder.getKeys().get("order_id"));

        jdbcTemplate.update("insert into order_state(order_id) values (?)", keyHolder.getKeys().get("order_id"));

        jdbcTemplate.update("Insert into customer_order(customer_id, order_id) values (?,?)",
                customer_id, keyHolder.getKeys().get("order_id"));
    }
    public OrderState getOrderState(int order_id)
    {
        return jdbcTemplate.query("Select * from order_state where order_id=?", new Object[]{order_id}, new BeanPropertyRowMapper<>(OrderState.class))
                .stream().findAny().orElse(null);
    }

    public List<Order> getPreparingOrders()
    {
        return jdbcTemplate.query("Select \"order\".* from order_state " +
                "left join \"order\" on \"order\".order_id=order_state.order_id " +
                "where order_state.state='preparing';", new Object[]{}, new BeanPropertyRowMapper<>(Order.class));
    }

    public void finishOrder(int order_id)
    {
        jdbcTemplate.update("UPDATE order_state set state='done' where order_id=?;", order_id);
    }
}
