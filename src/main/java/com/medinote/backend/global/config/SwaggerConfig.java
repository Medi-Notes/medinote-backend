package com.medinote.backend.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Medinote API 명세서", version = "v1"))
public class SwaggerConfig {
    @Bean
    GroupedOpenApi memberOpenApi() {
        String[] paths = { "/api/v1/member/**"};
        return GroupedOpenApi.builder().
            group("멤버 관련 API")
            .pathsToMatch(paths)
            .build();
    }

    @Bean
    GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
            .group("전체 API")
            .pathsToMatch("/api/v1/**")
            .build();
    }
}
