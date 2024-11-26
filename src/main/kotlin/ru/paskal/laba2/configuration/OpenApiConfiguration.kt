package ru.paskal.laba2.configuration

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@OpenAPIDefinition()
@Configuration
class OpenApiConfiguration {

    @Bean
    fun publicApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("public")
            .pathsToMatch("/api/**")
            .build()
    }

    @Bean
    fun apiInfo(): Info {
        return Info()
            .title("Laureates API")
            .description("API базы данных нобелевских лауреатов")
            .version("1.0.0")
            .contact(Contact().name("Leo Sokolov").email("vaxxo9000@gmail.com").url("https://github.com/OverLeo007"))
    }

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .components(
                Components().addSecuritySchemes(
                    "bearer-key",
                    SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("JWT")
                )
            )
            .info(
                apiInfo()
            )
            .addSecurityItem(SecurityRequirement().addList("bearer-key"))
    }


}