package li.pluess.gcode;

import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import li.pluess.gstrom.antlr.GCodeLexer;
import li.pluess.gstrom.antlr.GCodeParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.io.IOException;

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
    private GStromGCodeListener gStromGCodeListener;

    @Override
    public void run(String... ingnoredArguments) throws Exception {
        LOGGER.info("Parsing file {}", fileName);
        GCodeParser gCodeParser = createParser(fileName);
        ParseTree tree = gCodeParser.gcode();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(gStromGCodeListener, tree);

//        List<GCodeParser.LineContext> lineList = gCodeParser.gcode().line();
//        lineList.stream()
//                .filter(line -> line.command() != null)
//                .forEach(line -> {
//                    line.command().children.stream().forEach(
//                            parseTree -> {
//                                LOGGER.info(parseTree.getText());
//                            }
//                    );
//                });
    }

    private GCodeParser createParser(String fileName) throws IOException {
        CharStream charStream = CharStreams.fromFileName(fileName);
        GCodeLexer gCodeLexer = new GCodeLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(gCodeLexer);
        return new GCodeParser(tokens);
    }

}
