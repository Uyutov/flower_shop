package uyutov.flower_shop.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uyutov.flower_shop.dao.FlowerDao;
import uyutov.flower_shop.dao.StuffDao;
import uyutov.flower_shop.models.users.Admin;
import uyutov.flower_shop.models.users.Packer;
import uyutov.flower_shop.models.users.Provider;
import uyutov.flower_shop.security.AdminDetails;
import uyutov.flower_shop.services.RegistrationService;
import uyutov.flower_shop.services.UsersDetailsService;
import uyutov.flower_shop.utils.Parser;

import java.util.Optional;

@Controller()
@RequestMapping("/admin")
public class AdminController {
    private final StuffDao stuffDao;
    private final FlowerDao flowerDao;
    private final RegistrationService registrationService;
    private final UsersDetailsService usersDetailsService;

    public AdminController(StuffDao stuffDao, FlowerDao flowerDao, RegistrationService registrationService, UsersDetailsService usersDetailsService) {
        this.stuffDao = stuffDao;
        this.flowerDao = flowerDao;
        this.registrationService = registrationService;
        this.usersDetailsService = usersDetailsService;
    }

    @GetMapping("/profile")
    public String getAdminProfile(Model model)
    {
        model.addAttribute("provider", new Provider());
        model.addAttribute("packer", new Packer());
        model.addAttribute("flower", flowerDao.getMostExpensiveFlower());
        return "/admin/profile";
    }
    @PostMapping("/create_provider")
    public String createProvider(@ModelAttribute("provider")Provider provider)
    {
        registrationService.registerProvider(provider);
        return "redirect:/admin/profile";
    }
    @PostMapping("/create_packer")
    public String createPacker(@ModelAttribute("packer") Packer packer)
    {
        registrationService.registerPacker(packer);
        return "redirect:/admin/profile";
    }

    @PostMapping("/create_json")
    public String createJsonFile()
    {
        Parser.parseJson(stuffDao.getAllPackers());
        return "redirect:/admin/profile";
    }
    @PostMapping("/create_pdf")
    public String createPdfFile()
    {
        String phone_number = ((AdminDetails) usersDetailsService.loadUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName())).getAdmin().getPhone_number();
        Optional<Admin> admin = stuffDao.getAdminByPhoneNumber(phone_number);
        if(admin.isPresent()) {
            Parser.parsePdf(admin.get());
        }
        return "redirect:/admin/profile";
    }

}
