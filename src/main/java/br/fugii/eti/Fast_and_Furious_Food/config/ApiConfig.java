package br.fugii.eti.Fast_and_Furious_Food.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {
  
     @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FastFuriousFood API")
                        .description("API para gerenciamento de pedidos de trailer")
                        .version("1.0"));
    }
    
}
