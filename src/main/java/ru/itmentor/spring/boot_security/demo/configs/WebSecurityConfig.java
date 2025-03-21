package ru.itmentor.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.itmentor.spring.boot_security.demo.service.admin.AdminService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String USER_ROLE = "USER";

    @Value("${default.user.username}")
    private String defaultUsername;

    @Value("${default.user.password}")
    private String defaultPassword;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/api/admin").hasRole(ADMIN_ROLE)
                .requestMatchers("/api/user").hasAnyRole("USER", ADMIN_ROLE)
                .anyRequest().authenticated()
    )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    // аутентификация inMemory
    @Bean
    public UserDetailsService userDetailsService(AdminService adminService) {
        UserDetails defaultUser = User.builder()
                        .username(defaultUsername)
                .password(passwordEncoder().encode(defaultPassword))
                        .roles(ADMIN_ROLE,USER_ROLE)
                        .build();

        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(defaultUser);

        return username -> {
            try{
              return inMemoryUserDetailsManager.loadUserByUsername(username);

            } catch (UsernameNotFoundException e) {
                return adminService.findUserByUsername(username);
            }

        };
    }
}