package io.github.lsouza.oficina.config;

import io.github.lsouza.oficina.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import io.github.lsouza.oficina.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/usuarios/**").permitAll();

                    auth.requestMatchers("/clientes/**").hasAnyRole("ADMIN", "FUNCIONARIO");
                    auth.requestMatchers("/veiculos/**").hasAnyRole("ADMIN", "FUNCIONARIO");
                    auth.requestMatchers("/ordens/**").hasAnyRole("ADMIN", "FUNCIONARIO");

                    auth.anyRequest().authenticated();
                })
                .build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public UserDetailsService userDetailsService(UsuarioService service) {
        return new CustomUserDetailsService(service);
    }
}
