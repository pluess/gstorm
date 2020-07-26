package li.pluess.gstorm;

import com.github.rvesse.airline.SingleCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {"li.pluess"}
)
@EnableFeignClients
public class GcodeApplication {

    private static String[] args;

    public static void main(String[] largs) {
        args = largs;
        SpringApplication.run(GcodeApplication.class, args);
    }

    @Bean
    CliExecuter createCliExecuter() {
        SingleCommand<CliExecuter> parser = SingleCommand.singleCommand(CliExecuter.class);
        return parser.parse(args);
    }

}