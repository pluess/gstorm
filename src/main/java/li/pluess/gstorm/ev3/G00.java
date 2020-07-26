package li.pluess.gstorm.ev3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Move to the specified coordinates.
 * This {@link Component} is statefull. If <codd>null</codd> is given for a coordinate
 * it uses the value from the last call to {@link #execute(Double, Double, Double)}.
 */
@Component
public class G00 {

    private static final Logger LOGGER = LoggerFactory.getLogger(G00.class);

    private final Ev3Client ev3Client;

    private double x = 0.0;
    private double y = 0.0;
    private double z = 0.0;

    @Autowired
    public G00(Ev3Client ev3Client) {
        this.ev3Client = ev3Client;
    }

    /**
     * Passing <code>null</code> is ok. In this case the value from the
     * last method call is used.
     */
    public void execute(
            Double lx,
            Double ly,
            Double lz) {

        x = lx != null ? lx : x;
        y = ly != null ? ly : y;
        z = lz != null ? lz : z;

        LOGGER.debug("Given x={}, y={}, z={}, Using x={}, y={}, z={}", lx, ly, lz, x, y, z);
        ev3Client.moveLinear(new Ev3Client.Coordinates(x, y, z));
    }

}
