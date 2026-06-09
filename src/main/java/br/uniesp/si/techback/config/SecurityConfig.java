package br.uniesp.si.techback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // Permitir explicitamente o Swagger, OpenAPI e Actuator
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/actuator/**"
                        ).permitAll()
                        // Permitir H2 console
                        .requestMatchers("/h2-console/**").permitAll()
                        // ALTERADO: Permitir todas as outras rotas (Filmes, Diretores, Planos) para você testar livremente
                        .anyRequest().permitAll()
                )
                // Permitir uso do frame para o H2 console abrir dentro do navegador
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                // ALTERADO: Desabilita o CSRF por completo para não dar erro 403 nos seus testes de POST e PUT
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}