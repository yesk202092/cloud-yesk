package sure;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = "sure.**.dao")
@EnableCaching
@Configuration
@EnableSwagger2
public class AdminBootStart {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AdminBootStart.class).web(true).run(args);
    }


    public class Swagger2 {
        @Value("${admin-swagger.basepackage}")
        private String basepackage;
        @Value("${admin-swagger.service.name}")
        private String titile;
        @Value("${admin-swagger.service.description}")
        private String description;
        @Value("${admin-swagger.service.developer}")
        private String developer;

        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage(basepackage))
                    .paths(PathSelectors.any())
                    .build();
        }
        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title(titile)
                    .description(description)
                    .termsOfServiceUrl("")
                    .contact(developer)
                    .version("1.0")
                    .build();
        }
    }
}
