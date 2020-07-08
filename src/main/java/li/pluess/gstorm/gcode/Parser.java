package li.pluess.gstorm.gcode;

import li.pluess.gstrom.antlr.GCodeLexer;
import li.pluess.gstrom.antlr.GCodeParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(Parser.class);

    private final GStromGCodeListener gStromGCodeListener;

    public Parser(GStromGCodeListener gStromGCodeListener) {
        this.gStromGCodeListener = gStromGCodeListener;
    }

    public void parseFile(String fileName) throws IOException {
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
