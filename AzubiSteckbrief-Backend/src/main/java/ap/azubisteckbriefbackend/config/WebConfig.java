package ap.azubisteckbriefbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // <-- Passe dies an, falls dein API-Pfad anders ist
                        .allowedOrigins("http://localhost:4200") // <-- Erlaube Angular-Frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // <-- Erlaubte HTTP-Methoden
                        .allowedHeaders("*") // <-- Erlaube alle Header
                        .allowCredentials(true); // <-- Erlaube Cookies/Credentials, falls benötigt
            }
        };
    }
}