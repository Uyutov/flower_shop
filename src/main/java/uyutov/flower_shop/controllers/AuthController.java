package uyutov.flower_shop.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uyutov.flower_shop.dao.CustomerDao;
import uyutov.flower_shop.models.users.Customer;
import uyutov.flower_shop.services.RegistrationService;

import java.io.IOException;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final CustomerDao customerDao;
    private final RegistrationService registrationService;

    public AuthController(CustomerDao customerDao, RegistrationService registrationService) {
        this.customerDao = customerDao;
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginPage()
    {
        return "/auth/login";
    }
    @GetMapping("/registration")
    public String registrationPageForStudent(@ModelAttribute("customer") Customer customer)
    {
        return "/auth/registration";
    }
    @PostMapping("/registration")
    public String StudentRegistration(@ModelAttribute("customer") @Valid Customer customer,
                                      BindingResult bindingResult)
    {
        //studentValidator.validate(student, bindingResult);
        if(bindingResult.hasErrors())
        {
            return "/auth/registration";
        }
        registrationService.registerCustomer(customer);

        return "redirect:/auth/login";
    }

}
