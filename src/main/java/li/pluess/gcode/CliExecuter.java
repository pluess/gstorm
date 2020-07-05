package li.pluess.gcode;

import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Command(name = "getting-started", description = "We're just getting started")
public class CliExecuter implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CliExecuter.class);

    @Option(name = { "-f", "--file" }, description = "The GCode file to parse.")
    private String fileName;

    @Override
    public void run(String... ingnoredArguments) throws Exception {
        LOGGER.info("Parsing file {}", fileName);
    }

}
