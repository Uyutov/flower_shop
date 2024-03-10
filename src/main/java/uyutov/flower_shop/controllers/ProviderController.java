package uyutov.flower_shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uyutov.flower_shop.dao.FlowerDao;
import uyutov.flower_shop.models.Flower;
import uyutov.flower_shop.utils.FileUploadUtil;

import java.io.IOException;

@Controller()
@RequestMapping("/provider")
public class ProviderController {
    private final FlowerDao flowerDao;

    public ProviderController(FlowerDao flowerDao) {
        this.flowerDao = flowerDao;
    }

    @GetMapping("/profile")
    public String getPackerProfile(Model model)
    {
        model.addAttribute("flower", new Flower());
        return "/provider/profile";
    }
    @PostMapping("/create_flower")
    public String createFlower(@ModelAttribute("flower") Flower flower,
                               @RequestParam("image") MultipartFile multipartFile) throws IOException
    {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        flower.setIcon(fileName);
        flowerDao.createFlower(flower);
        String uploadDir = "flower-icons/" + flower.getName();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "redirect:/provider/profile";
    }
}
