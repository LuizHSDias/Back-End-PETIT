package com.cefet.ds_petit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cefet.ds_petit.security.JwtAuthenticationFilter;
import com.cefet.ds_petit.services.PetianoDetailsService;

@Configuration
public class SecurityConfig {

    @Autowired
    private PetianoDetailsService petianoDetailsService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Acesso ao H2 Console
                .requestMatchers("/h2-console/**").permitAll()

                // Acesso ao Swagger UI
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                // Permitir login de usuário
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                // Acesso às imagens salvas
                .requestMatchers(HttpMethod.GET, "/imagens/**").permitAll()

                // Regras de Autorização para ARQUIVOS
                .requestMatchers(HttpMethod.POST, "/imagens/upload").hasAnyRole("ADMIN", "PETIT")

                // Regras de Autorização para LIVROS
                .requestMatchers(HttpMethod.GET, "/livros").hasAnyRole("ADMIN", "PETIT")
                .requestMatchers(HttpMethod.GET, "/livros/{id}").hasAnyRole("ADMIN", "PETIT")
                .requestMatchers(HttpMethod.POST, "/livros").hasAnyRole("ADMIN", "PETIT")
                .requestMatchers(HttpMethod.PUT, "/livros/**").hasAnyRole("ADMIN", "PETIT")
                .requestMatchers(HttpMethod.DELETE, "/livros/**").hasAnyRole("ADMIN", "PETIT")

                // Regras de Autorização para ESTUDANTES
                .requestMatchers(HttpMethod.GET, "/estudantes").hasAnyRole("ADMIN", "PETIT")
                .requestMatchers(HttpMethod.GET, "/estudantes/{id}").hasAnyRole("ADMIN", "PETIT")
                .requestMatchers(HttpMethod.GET, "/estudantes/page").hasAnyRole("ADMIN", "PETIT")
                .requestMatchers(HttpMethod.GET, "/estudantes/ativos/count").hasAnyRole("ADMIN", "PETIT")
                .requestMatchers(HttpMethod.GET, "/estudantes/search").hasAnyRole("ADMIN", "PETIT")
                .requestMatchers(HttpMethod.POST, "/estudantes").hasAnyRole("ADMIN", "PETIT")
                .requestMatchers(HttpMethod.PUT, "/estudantes/**").hasAnyRole("ADMIN", "PETIT")
                .requestMatchers(HttpMethod.DELETE, "/estudantes/**").hasAnyRole("ADMIN", "PETIT")

                // Regras de Autorização para EMPRÉSTIMOS
                .requestMatchers(HttpMethod.GET, "/emprestimos").hasAnyRole("ADMIN", "PETIT")
                .requestMatchers(HttpMethod.GET, "/emprestimos/{id}").hasAnyRole("ADMIN", "PETIT")
                .requestMatchers(HttpMethod.POST, "/emprestimos").hasAnyRole("ADMIN", "PETIT")
                .requestMatchers(HttpMethod.PUT, "/emprestimos/**").hasAnyRole("ADMIN", "PETIT")
                .requestMatchers(HttpMethod.DELETE, "/emprestimos/**").hasAnyRole("ADMIN", "PETIT")

                // Regras de Autorização para PETIANOS
                .requestMatchers(HttpMethod.GET, "/petianos").hasAnyRole("ADMIN", "PETIT")
                .requestMatchers(HttpMethod.GET, "/petianos/{id}").hasAnyRole("ADMIN", "PETIT")
                .requestMatchers(HttpMethod.POST, "/petianos").hasAnyRole("ADMIN", "PETIT")
                .requestMatchers(HttpMethod.PUT, "/petianos/**").hasAnyRole("ADMIN", "PETIT")
                .requestMatchers(HttpMethod.DELETE, "/petianos/**").hasAnyRole("ADMIN", "PETIT")

                // Todos os outros endpoints exigem autenticação
                .anyRequest().authenticated()
            )

            // Configuração para o H2 Console
            .headers(headers -> headers.frameOptions().disable())

            // Configuração de sessão (JWT é stateless)
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // Adiciona o filtro JWT antes do filtro padrão de autenticação
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(petianoDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("http://localhost:4200", "https://biblioteca-petit.netlify.app/")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true);
            }
        };
    }
}
