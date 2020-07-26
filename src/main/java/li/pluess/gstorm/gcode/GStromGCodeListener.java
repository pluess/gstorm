package li.pluess.gstorm.gcode;

import li.pluess.gstorm.ev3.G00;
import li.pluess.gstorm.ev3.G21;
import li.pluess.gstorm.ev3.M3;
import li.pluess.gstrom.antlr.GCodeBaseListener;
import li.pluess.gstrom.antlr.GCodeParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GStromGCodeListener extends GCodeBaseListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(GStromGCodeListener.class);

    private final M3 m3;
    private final G21 g21;
    private final G00 g00;

    public GStromGCodeListener(M3 m3, G21 g21, G00 g00) {
        this.m3 = m3;
        this.g21 = g21;
        this.g00 = g00;
    }

    @Override
    public void enterM3(GCodeParser.M3Context ctx) {
        m3.execute();
    }

    @Override
    public void enterG00(GCodeParser.G00Context ctx) {
        Double x = ctx.coordinates().x() != null ? Double.parseDouble(ctx.coordinates().x().FLOAT().getText()) : null;
        Double y = ctx.coordinates().y() != null ? Double.parseDouble(ctx.coordinates().y().FLOAT().getText()) : null;
        Double z = ctx.coordinates().z() != null ? Double.parseDouble(ctx.coordinates().z().FLOAT().getText()) : null;
        g00.execute(x, y, z);
    }

    /**
     * Speed is ignored, which means G01 is the same as G00.
     *
     * @param ctx
     */
    @Override
    public void enterG01(GCodeParser.G01Context ctx) {
        Double x = ctx.coordinates().x() != null ? Double.parseDouble(ctx.coordinates().x().FLOAT().getText()) : null;
        Double y = ctx.coordinates().y() != null ? Double.parseDouble(ctx.coordinates().y().FLOAT().getText()) : null;
        Double z = ctx.coordinates().z() != null ? Double.parseDouble(ctx.coordinates().z().FLOAT().getText()) : null;
        g00.execute(x, y, z);
    }

    @Override
    public void enterG21(GCodeParser.G21Context ctx) {
        g21.execute();
    }
}
