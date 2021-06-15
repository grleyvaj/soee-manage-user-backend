package es.soee.demo.core.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Gloria R. Leyva Jerez
 * Configure globally the CORS security policy
 */
@Configuration
public class CorsConfig {

    @Value("${server.port}")
    private String port;

    /**
     * Specify which domains are authorized and which are not, and what they are authorized for.,
     *
     * @return WebMvcConfigurer configuration for project
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") /*	/ENTITY_API/**	*/
                        .allowedOrigins("http://localhost:" + port)          /*	ALL(*) or http://localhost:port	 (8085 or other port) */
                        .allowedMethods("PUT", "DELETE", "GET", "POST", "PATCH" /*, other methods*/)
                        .allowCredentials(true)
                /*.maxAge(3600);*/             /* You can define maximum number of years*/
                ;
            }
        };
    }
}
