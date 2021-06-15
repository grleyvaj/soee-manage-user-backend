package es.soee.demo.core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @author Gloria R. Leyva Jerez
 */
@Configuration
@PropertySource("classpath:validation.properties")
@PropertySource("classpath:notification.properties")
public class PropertiesSourceConfig {
}
