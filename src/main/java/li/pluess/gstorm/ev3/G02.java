package li.pluess.gstorm.ev3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Move to the specified coordinates by drawing an arc.
 * This {@link Component} is statefull. If <code>null</code> is given for a coordinate
 * it uses the value from the {@link CurrentPosition}.
 */
@Component
public class G02 {

    private static final Logger LOGGER = LoggerFactory.getLogger(G02.class);

    private final Ev3Client ev3Client;
    private final CurrentPosition currentPosition;

    @Autowired
    public G02(Ev3Client ev3Client, CurrentPosition currentPosition) {
        this.ev3Client = ev3Client;
        this.currentPosition = currentPosition;
    }

    /**
     * Passing <code>null</code> is ok. In this case the value from the
     * last method call is used for x, y and z. For li and ly 0 is assumed.
     */
    public void execute(
            Double x,
            Double y,
            Double z,
            Double li,
            Double lj) {

        currentPosition.setXIfNotNull(x);
        currentPosition.setYIfNotNull(y);
        currentPosition.setZIfNotNull(z);

        double i = li != null ? li : 0.0;
        double j = lj != null ? lj : 0.0;

        LOGGER.debug("Given x={}, y={}, z={}, i={}, j={}, Using x={}, y={}, z={},  i={}, j={}",
                x, y, z, li, lj,
                currentPosition.getX(), currentPosition.getY(), currentPosition.getZ(),
                i, j);
        ev3Client.moveArc(
                new Ev3Client.ArcCoordinates(
                        currentPosition.getX(), currentPosition.getY(), currentPosition.getZ(),
                        i, j));
    }

}
