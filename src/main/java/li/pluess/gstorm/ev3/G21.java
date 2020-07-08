package li.pluess.gstorm.ev3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Set Units to Millimeters.
 * Does nothing here, since milimeters is the only supported unit.
 */
@Component
public class G21 {

    private static final Logger LOGGER = LoggerFactory.getLogger(G21.class);

    public void execute() {
        LOGGER.debug("");
    }

}
