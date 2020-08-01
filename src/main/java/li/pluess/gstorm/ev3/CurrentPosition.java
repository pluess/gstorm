package li.pluess.gstorm.ev3;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

/**
 * Speicher die aktuelle Position des Plotters.
 *
 * @author Ernst Plüss
 * @since 01.08.20
 */
@Component
public class CurrentPosition {

    private double x = 0.0;
    private double y = 0.0;
    private double z = 0.0;

    public CurrentPosition() {
    }

    CurrentPosition(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setXIfNotNull(Double x) {
        if (x != null) {
            this.x = x;
        }
    }

    public double getY() {
        return y;
    }

    public void setYIfNotNull(Double y) {
        if (y != null) {
            this.y = y;
        }
    }

    public double getZ() {
        return z;
    }

    public void setZIfNotNull(Double z) {
        if (z != null) {
            this.z = z;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CurrentPosition that = (CurrentPosition) o;

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
