package li.pluess.gstorm.ev3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Ev3Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Ev3Controller.class);

    public void goTo(
            double x,
            double y,
            double z
    ) {
        LOGGER.debug("x={}, y={}, z={}", x, y, z);
    }

}
