package li.pluess.gstorm.gcode;

import li.pluess.gstrom.antlr.GCodeLexer;
import li.pluess.gstrom.antlr.GCodeParser;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.BitSet;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class GCodeParserUnitTest {

    private static Logger LOGGER = LoggerFactory.getLogger(GCodeParserUnitTest.class);
    private static final String GCODE_FILE_NAME = "src/test/resources/zeichnung_0002.ngc";

    @Test
    void gcodeIsParsedOK() throws IOException {
        CharStream charStream = CharStreams.fromFileName(GCODE_FILE_NAME);
        GCodeLexer gCodeLexer = new GCodeLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(gCodeLexer);
        GCodeParser gCodeParser = new GCodeParser(tokens);
        GCodeParser.GcodeContext gcodeContext = gCodeParser.gcode();
        final AtomicInteger error = new AtomicInteger();
        gCodeParser.addErrorListener(new ANTLRErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                LOGGER.error(offendingSymbol.toString(), e);
                fail();
            }

            @Override
            public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
                fail();
            }

            @Override
            public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
                fail();
            }

            @Override
            public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
                fail();
            }

        });
        RecognitionException recognitionException = gcodeContext.exception;
        if (recognitionException != null) {
            LOGGER.error("Parse Error", recognitionException);
        }
        assertNull(recognitionException);
        assertEquals(0, error.get());

    }

}
