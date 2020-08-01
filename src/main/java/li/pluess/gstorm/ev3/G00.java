package li.pluess.gstorm.ev3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Move to the specified coordinates.
 * This {@link Component} is statefull. If <code>null</code> is given for a coordinate
 * it uses the value from the {@link CurrentPosition}.
 */
@Component
public class G00 {

    private static final Logger LOGGER = LoggerFactory.getLogger(G00.class);

    private final Ev3Client ev3Client;
    private final CurrentPosition currentPosition;

    @Autowired
    public G00(Ev3Client ev3Client, CurrentPosition currentPosition) {
        this.ev3Client = ev3Client;
        this.currentPosition = currentPosition;
    }

    /**
     * Passing <code>null</code> is ok. In this case the value from the
     * last method call is used.
     */
    public void execute(
            Double x,
            Double y,
            Double z) {

        currentPosition.setXIfNotNull(x);
        currentPosition.setYIfNotNull(y);
        currentPosition.setZIfNotNull(z);

        LOGGER.debug("Given x={}, y={}, z={}, Using x={}, y={}, z={}",
                x, y, z,
                currentPosition.getX(), currentPosition.getY(), currentPosition.getZ());
        ev3Client.moveLinear(
                new Ev3Client.Coordinates(
                        currentPosition.getX(),
                        currentPosition.getY(),
                        currentPosition.getZ()));
    }

}
