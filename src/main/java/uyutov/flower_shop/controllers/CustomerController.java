package uyutov.flower_shop.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.data.util.Pair;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uyutov.flower_shop.dao.OrderDao;
import uyutov.flower_shop.models.Order;
import uyutov.flower_shop.models.OrderState;
import uyutov.flower_shop.security.CustomerDetails;
import uyutov.flower_shop.services.UsersDetailsService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final UsersDetailsService usersDetailsService;
    private final OrderDao orderDao;

    public CustomerController(UsersDetailsService usersDetailsService, OrderDao orderDao) {
        this.usersDetailsService = usersDetailsService;
        this.orderDao = orderDao;
    }

    @GetMapping("/orders")
    public String getAllCustomerOrders(Model model)
    {
        int customer_id = ((CustomerDetails) usersDetailsService.loadUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName())).getCustomer().getCustomer_id();
        List<Order> orders = orderDao.getAllOrdersOfCustomer(customer_id);
        List<Pair<Order, OrderState>> order_state = new ArrayList<>();
        if(orders.get(0).getOrder_id()!=null) {
            for (Order order : orders) {
                order_state.add(Pair.of(order, orderDao.getOrderState(order.getOrder_id())));
            }
        }
        model.addAttribute("order_state", order_state);
        return "/customer/orders";
    }
    @PostMapping("/create_order")
    public String createOrder(HttpSession session)
    {

        int customer_id = ((CustomerDetails) usersDetailsService.loadUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName())).getCustomer().getCustomer_id();
        Order order = (Order) session.getAttribute("order");
        if(order == null)
        {
            return "redirect:/showcase/cart";
        }
        orderDao.addOrderToCustomer(customer_id, order);
        session.setAttribute("order", new Order());
        return "redirect:/customer/orders";
    }
}
