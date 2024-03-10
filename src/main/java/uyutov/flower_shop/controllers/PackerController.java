package uyutov.flower_shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uyutov.flower_shop.dao.OrderDao;

@Controller()
@RequestMapping("/packer")
public class PackerController {
    private final OrderDao orderDao;

    public PackerController(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @GetMapping("/profile")
    public String getProfile(Model model)
    {
        model.addAttribute("orders", orderDao.getPreparingOrders());
        return "/packer/profile";
    }
    @GetMapping("/finish_order/{order_id}")
    public String finishOrder(@PathVariable("order_id")int order_id)
    {
        orderDao.finishOrder(order_id);
        return "redirect:/packer/profile";
    }
}
