package li.pluess.gstorm.ev3;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * REST call defintion of the EV3.
 *
 * @author Ernst Plüss
 * @since 26.07.20
 */
@FeignClient(
        name = "ev3",
        url = "${ev3.client.url}",
        configuration = Ev3ClientConfig.class)
public interface Ev3Client {

    static class Coordinates {
        private double x;
        private double y;
        private double z;

        public Coordinates(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getZ() {
            return z;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            Coordinates that = (Coordinates) o;

            return new EqualsBuilder()
                    .append(x, that.x)
                    .append(y, that.y)
                    .append(z, that.z)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(x)
                    .append(y)
                    .append(z)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("x", x)
                    .append("y", y)
                    .append("z", z)
                    .toString();
        }
    }

    static class ArcCoordinates {
        private double x;
        private double y;
        private double z;
        private double i;
        private double j;

        public ArcCoordinates(double x, double y, double z, double i, double j) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.i = i;
            this.j = j;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getZ() {
            return z;
        }

        public double getI() {
            return i;
        }

        public double getJ() {
            return j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            ArcCoordinates that = (ArcCoordinates) o;

            return new EqualsBuilder()
                    .append(x, that.x)
                    .append(y, that.y)
                    .append(z, that.z)
                    .append(i, that.i)
                    .append(j, that.j)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(x)
                    .append(y)
                    .append(z)
                    .append(i)
                    .append(j)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("x", x)
                    .append("y", y)
                    .append("z", z)
                    .append("i", i)
                    .append("j", j)
                    .toString();
        }
    }


    @RequestMapping(method = RequestMethod.GET, value = "/motor/reset/{port}")
    void reset(@PathVariable("port") String port);

    @RequestMapping(method = RequestMethod.POST, value = "/move-linear", consumes = "application/json")
    void moveLinear(Coordinates coordinates);

    @RequestMapping(method = RequestMethod.POST, value = "/move-arc", consumes = "application/json")
    void moveArc(ArcCoordinates coordinates);

}
