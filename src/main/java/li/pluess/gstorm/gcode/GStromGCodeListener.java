package li.pluess.gstorm.gcode;

import li.pluess.gstorm.ev3.*;
import li.pluess.gstrom.antlr.GCodeBaseListener;
import li.pluess.gstrom.antlr.GCodeParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GStromGCodeListener extends GCodeBaseListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(GStromGCodeListener.class);

    private final Ev3Client ev3Client;
    private final M3 m3;
    private final G21 g21;
    private final G00 g00;
    private final G02 g02;

    @Autowired
    public GStromGCodeListener(Ev3Client ev3Client, M3 m3, G21 g21, G00 g00, G02 g02) {
        this.ev3Client = ev3Client;
        this.m3 = m3;
        this.g21 = g21;
        this.g00 = g00;
        this.g02 = g02;
        this.init();
    }

    private void init() {
        ev3Client.reset("a");
        ev3Client.reset("b");
        ev3Client.reset("c");
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
    public void enterG02(GCodeParser.G02Context ctx) {
        Double x = ctx.coordinates().x() != null ? Double.parseDouble(ctx.coordinates().x().FLOAT().getText()) : null;
        Double y = ctx.coordinates().y() != null ? Double.parseDouble(ctx.coordinates().y().FLOAT().getText()) : null;
        Double z = ctx.coordinates().z() != null ? Double.parseDouble(ctx.coordinates().z().FLOAT().getText()) : null;
        Double i = ctx.arc().i() != null ? Double.parseDouble(ctx.arc().i().FLOAT().getText()) : null;
        Double j = ctx.arc().j() != null ? Double.parseDouble(ctx.arc().j().FLOAT().getText()) : null;
        g02.execute(x, y, z, i, j);
    }

    @Override
    public void enterG21(GCodeParser.G21Context ctx) {
        g21.execute();
    }

}
