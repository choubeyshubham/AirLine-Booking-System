package in.choubeyshubham.flightOpsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FlightOpsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightOpsServiceApplication.class, args);
    }

}
