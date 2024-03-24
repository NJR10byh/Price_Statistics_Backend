package tutu.njr10byh.price_statistics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket(Environment environment) {

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(
                        "tutu.njr10byh.price_statistics.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("价格统计系统API文档",
                "价格统计系统接口文档",
                "v0.1.0",
                "",
                new Contact("", "", ""),
                "MIT License",
                "https://opensource.org/licenses/MIT",
                new ArrayList<>());
    }

}
