package li.pluess.ev3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * There's nothing to spin up for a plotter.
 */
@Component
public class M3 {

    private static final Logger LOGGER = LoggerFactory.getLogger(M3.class);

    public void execute() {
        LOGGER.debug("");
    }

}
