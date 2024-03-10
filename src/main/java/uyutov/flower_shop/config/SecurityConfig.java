package uyutov.flower_shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import uyutov.flower_shop.services.UsersDetailsService;

@Configuration
public class SecurityConfig {

    private final UsersDetailsService usersDetailsService;

    public SecurityConfig(UsersDetailsService usersDetailsService) {
        this.usersDetailsService = usersDetailsService;
    }
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.userDetailsService(usersDetailsService)
                .authorizeHttpRequests()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/packer/**").hasRole("PACKER")
                .requestMatchers("/provider/**").hasRole("PROVIDER")
                .requestMatchers("/customer/**").hasRole("CUSTOMER")
                .requestMatchers("/showcase/**", "/auth/**", "/error", "/logout", "/css/**", "/images/**", "/flower-icons/**").permitAll()
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/showcase/profile", true)
                .failureUrl("/auth/login?error")
                .and().logout().logoutUrl("/auth/logout").logoutSuccessUrl("/showcase");
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder getEncoder()
    {
        return new BCryptPasswordEncoder(10);
    }
}
