package sp.puc.comp.gpma.apigerenciamentotarefas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig{

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().info(new Info()
							.title("Gerenciamento de Tarefas")
							.description("API de gerenciamento de tarefas")
							.version("1"));
	}
}
