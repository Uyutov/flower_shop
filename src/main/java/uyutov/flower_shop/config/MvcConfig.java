package uyutov.flower_shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String flowerIcons = "file:///D:/JavaProjects/Курсач/flower_shop/src/main/resources/static/flower-icons/";
        String cssPath = "file:///D:/JavaProjects/Курсач/flower_shop/src/main/resources/static/css/";

        registry.addResourceHandler("/flower-icons/**").addResourceLocations(flowerIcons);
        registry.addResourceHandler("/css/**").addResourceLocations(cssPath);
    }
}
