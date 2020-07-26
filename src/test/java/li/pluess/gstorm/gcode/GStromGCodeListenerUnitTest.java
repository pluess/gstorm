package li.pluess.gstorm.gcode;

import li.pluess.gstorm.ev3.Ev3Client;
import li.pluess.gstorm.ev3.G00;
import li.pluess.gstorm.ev3.G21;
import li.pluess.gstorm.ev3.M3;
import li.pluess.gstrom.antlr.GCodeParser;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.mockito.Mockito.*;

public class GStromGCodeListenerUnitTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GStromGCodeListenerUnitTest.class);

    @Mock
    private M3 m3;

    @Mock
    private G21 g21;

    @Mock
    private G00 g00;

    @Mock
    private Ev3Client ev3Client;

    @InjectMocks
    private GStromGCodeListener gStromGCodeListener;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    public static Stream<Arguments> enterG00() {
        return Stream.of(
                createG00Arguments("5.00", "6.00", "7.00"),
                createG00Arguments(null, "6.00", "7.00"),
                createG00Arguments("5.00", null, "7.00"),
                createG00Arguments("5.00", "6.00", null),
                createG00Arguments(null, null, null)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void enterG00(Double xd, Double yd, Double zd, GCodeParser.G00Context ctx) {
        gStromGCodeListener.enterG00(ctx);
        verify(g00).execute(xd, yd, zd);
    }

    public static Stream<Arguments> enterG01() {
        return Stream.of(
                createG01Arguments("5.00", "6.00", "7.00"),
                createG01Arguments(null, "6.00", "7.00"),
                createG01Arguments("5.00", null, "7.00"),
                createG01Arguments("5.00", "6.00", null),
                createG01Arguments(null, null, null)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void enterG01(Double xd, Double yd, Double zd, GCodeParser.G01Context ctx) {
        gStromGCodeListener.enterG01(ctx);
        verify(g00).execute(xd, yd, zd);
    }

    private static Arguments createG00Arguments(String xs, String ys, String zs) {
        GCodeParser.G00Context g00Context = mock(GCodeParser.G00Context.class);
        GCodeParser.CoordinatesContext coordinatesContext = createCoordinatesContext(xs, ys, zs);
        when(g00Context.coordinates()).thenReturn(coordinatesContext);
        return Arguments.of(
                xs != null ? Double.parseDouble(xs) : null,
                ys != null ? Double.parseDouble(ys) : null,
                zs != null ? Double.parseDouble(zs) : null,
                g00Context);
    }

    private static Arguments createG01Arguments(String xs, String ys, String zs) {
        GCodeParser.G01Context g01Context = mock(GCodeParser.G01Context.class);
        GCodeParser.CoordinatesContext coordinatesContext = createCoordinatesContext(xs, ys, zs);
        when(g01Context.coordinates()).thenReturn(coordinatesContext);
        return Arguments.of(
                xs != null ? Double.parseDouble(xs) : null,
                ys != null ? Double.parseDouble(ys) : null,
                zs != null ? Double.parseDouble(zs) : null,
                g01Context);
    }

    private static GCodeParser.CoordinatesContext createCoordinatesContext(String xs, String ys, String zs) {
        GCodeParser.CoordinatesContext coordinatesContext = mock(GCodeParser.CoordinatesContext.class);

        GCodeParser.XContext xContext = mock(GCodeParser.XContext.class);
        GCodeParser.YContext yContext = mock(GCodeParser.YContext.class);
        GCodeParser.ZContext zContext = mock(GCodeParser.ZContext.class);
        TerminalNode xFloat = mock(TerminalNode.class);
        TerminalNode yFloat = mock(TerminalNode.class);
        TerminalNode zFloat = mock(TerminalNode.class);

        if (xs != null) {
            when(coordinatesContext.x()).thenReturn(xContext);
            when(xContext.FLOAT()).thenReturn(xFloat);
            when(xFloat.getText()).thenReturn(xs);
        }
        if (ys != null) {
            when(coordinatesContext.y()).thenReturn(yContext);
            when(yContext.FLOAT()).thenReturn(yFloat);
            when(yFloat.getText()).thenReturn(ys);
        }
        if (zs != null) {
            when(coordinatesContext.z()).thenReturn(zContext);
            when(zContext.FLOAT()).thenReturn(zFloat);
            when(zFloat.getText()).thenReturn(zs);
        }
        return coordinatesContext;
    }

}
