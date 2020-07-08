package li.pluess.gcode;

import com.github.rvesse.airline.SingleCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {"li.pluess"}
)
public class GcodeApplication {

    private static String[] args;

    public static void main(String[] largs) {
        args = largs;
        SpringApplication.run(GcodeApplication.class, args);
    }

    @Bean
    CliExecuter createCliExecuter() {
        SingleCommand<CliExecuter> parser = SingleCommand.singleCommand(CliExecuter.class);
        CliExecuter cmd = parser.parse(args);
        return cmd;
    }

}
