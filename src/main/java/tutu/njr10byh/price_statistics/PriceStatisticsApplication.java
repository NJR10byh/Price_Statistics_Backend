package tutu.njr10byh.price_statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PriceStatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(PriceStatisticsApplication.class, args);
    }
}
