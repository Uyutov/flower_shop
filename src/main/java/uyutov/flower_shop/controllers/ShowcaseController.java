package uyutov.flower_shop.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.util.Pair;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uyutov.flower_shop.dao.FlowerDao;
import uyutov.flower_shop.dao.OrderDao;
import uyutov.flower_shop.models.Flower;
import uyutov.flower_shop.models.Order;
import uyutov.flower_shop.models.OrderState;
import uyutov.flower_shop.security.CustomerDetails;
import uyutov.flower_shop.services.UsersDetailsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/showcase")
public class ShowcaseController {
    private final FlowerDao flowerDao;
    private final OrderDao orderDao;
    private final UsersDetailsService usersDetailsService;
    public ShowcaseController(FlowerDao flowerDao, OrderDao orderDao, UsersDetailsService usersDetailsService) {
        this.flowerDao = flowerDao;
        this.orderDao = orderDao;
        this.usersDetailsService = usersDetailsService;
    }
    @GetMapping()
    public String getShowcase(Model model, String keyword, HttpSession session)
    {
        Order order = (Order) session.getAttribute("order");
        if(order==null) {
            order = new Order();
            session.setAttribute("order", order);
        }
        if(keyword == null || keyword.isEmpty()) {
            model.addAttribute("flowers", flowerDao.getAllFlowers());
        }else{
            model.addAttribute("flowers", flowerDao.getAllFlowersByKeyword(keyword));
        }
        model.addAttribute("order", order);
        System.out.println(order.toString());
        return "/showcase/main_page";
    }
    @GetMapping("/flower/{flower_id}")
    public String getFlowerPage(@PathVariable("flower_id")int flower_id, Model model)
    {
        model.addAttribute("flower", flowerDao.getFlowerById(flower_id).get());
        return "/showcase/flower";
    }
    @PostMapping("/flower/{flower_id}")
    public String addFlowerToOrder(@PathVariable("flower_id")int flower_id,
                                   HttpSession session,
                                   @ModelAttribute("amount")int amount, Model model) throws JsonProcessingException {

        Optional<Flower> newFlower = flowerDao.getFlowerById(flower_id);
        if(newFlower.isPresent())
        {
            Order order = (Order) session.getAttribute("order");
            if (order==null)
            {
                session.setAttribute("order", new Order());
            }
            newFlower.get().setAmount(amount);
            Flower flower = newFlower.get();

            order.addFlower(flower);

            session.setAttribute("order", order);
        }
        return "redirect:/showcase";
    }
    @GetMapping("/cart")
    public String getCart(Model model, HttpSession session)
    {
        Order order = (Order) session.getAttribute("order");
        if (order==null)
        {
            session.setAttribute("order", new Order());
        }
        model.addAttribute("order", order);
        return "/showcase/cart";
    }

    @GetMapping("/profile")
    public String getProfile()
    {
        System.out.println("Inside profile");
        Collection<SimpleGrantedAuthority> authority = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (authority.stream().anyMatch(simpleGrantedAuthority -> simpleGrantedAuthority.getAuthority().equals("ROLE_ADMIN"))){
            return "redirect:/admin/profile";
        }
        if (authority.stream().anyMatch(simpleGrantedAuthority -> simpleGrantedAuthority.getAuthority().equals("ROLE_PROVIDER"))){
            return "redirect:/provider/profile";
        }
        if (authority.stream().anyMatch(simpleGrantedAuthority -> simpleGrantedAuthority.getAuthority().equals("ROLE_PACKER"))){
            return "redirect:/packer/profile";
        }
        if (authority.stream().anyMatch(simpleGrantedAuthority -> simpleGrantedAuthority.getAuthority().equals("ROLE_CUSTOMER"))){
            return "redirect:/customer/orders";
        }
        return "redirect:/auth/login";
    }
}
















