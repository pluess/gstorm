package li.pluess.gstorm;

import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import li.pluess.gstorm.gcode.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@Command(name = "getting-started", description = "We're just getting started")
public class CliExecuter implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CliExecuter.class);

    @Option(name = {"-f", "--file"}, description = "The GCode file to parse.")
    private String fileName;

    /**
     * Don't use construcgtor injection here, since
     * Ariline requires a non argument constructor.
     */
    @Autowired
    private Parser parser;

    @Override
    public void run(String... ingnoredArguments) throws Exception {
        parser.parseFile(fileName);
    }

}