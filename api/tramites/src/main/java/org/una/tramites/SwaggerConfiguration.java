/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites;

import static io.swagger.annotations.ApiKeyAuthDefinition.ApiKeyLocation.HEADER;
import java.util.Collections;
import static java.util.Collections.singletonList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author colo7
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(singletonList(new ApiKey("JWT", AUTHORIZATION, HEADER.name())))
                .securityContexts(singletonList(
                        SecurityContext.builder()
                                .securityReferences(
                                        singletonList(SecurityReference.builder()
                                                .reference("JWT")
                                                .scopes(new AuthorizationScope[0])
                                                .build()
                                        )
                                )
                                .build())
                )
                .select()
                .apis(
                        RequestHandlerSelectors
                                .basePackage("org.una.tramites.controllers"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiInfo())
                .tags(   new Tag("Seguridad", "Metodos de Seguridad") {},
                         new Tag("Usuarios", "Entidad de Usuarios"),
                         new Tag("Requisitos", "Entidad de Usuarios"),
                         new Tag("Departamentos", "Entidad de Departamentos"),
                         new Tag("Permisos", "Entidad de Permisos"),
                         new Tag("Permisos_Otorgados", "Entidad de Permisos_Otorgados"),
                         new Tag("Tramites_Tipos", "Entidad de Tramites_Tipos"),
                         new Tag("Transacciones", "Entidad de Transacciones"),
                         new Tag("Archivos_Relacionados", "Entidad de Archivos Relacionados"),
                         new Tag("Clientes", "Entidad de Clientes"),
                         new Tag("Notas", "Entidad de Notas"),
                         new Tag("Parametros_Generales", "Entidad de Parametros Generales"),
                         new Tag("Requisitos_Presentados", "Entidad de Requisitos Presentados"),
                         new Tag("Tramites_Estados", "Entidad de Requisitos Tramites Estados"),
                         new Tag("Tramites_Registrados", "Entidad de Requisitos Tramites Registrados"),
                         new Tag("Variaciones", "Entidad de Variaciones"),
                         new Tag("Tramites_Cambio_Estado", "Entidad de Tramites Cambio de Estado")
                );

    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Trámites Municipales",
                "Rest API sobre Trámites Municipales.",
                "Versión:2.1.0",
                "https://google.com",
                new Contact("UNA Sede Región Brunca", "https://srb.una.ac.cr/index.php/es/", "decanatosrb@una.cr"),
                "Apache-2.0", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
    }
}
