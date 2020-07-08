package li.pluess.ev3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Move to the specified coordinates.
 */
@Component
public class G00 {

    private static final Logger LOGGER = LoggerFactory.getLogger(G00.class);

    private Double x = 0.0;
    private Double y = 0.0;
    private Double z = 0.0;

    public void execute(
            Double lx,
            Double ly,
            Double lz) {

        x = lx != null ? lx : x;
        y = ly != null ? ly : y;
        z = lz != null ? lz : z;

        LOGGER.debug("Given x={}, y={}, z={} Using x={}, y={}, z={}", lx, ly, lz, x, y, z);

    }
}
