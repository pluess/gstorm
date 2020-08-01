package li.pluess.gstorm.ev3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Ernst Plüss
 * @since 09.07.20
 */
public class G00UnitTest {

    @Mock
    private Ev3Client ev3Client;

    @Captor
    private ArgumentCaptor<Ev3Client.Coordinates> coordinatesArgumentCaptor;

    private G00 g00;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        g00 = new G00(ev3Client, new CurrentPosition());
    }

    @Test
    public void execute() {
        g00.execute(null, null, null);
        g00.execute(5.0, 6.0, 7.0);
        g00.execute(null, null, null);

        verify(ev3Client, times(3)).moveLinear(
                coordinatesArgumentCaptor.capture());

        int i = 1;
        for (Ev3Client.Coordinates coordinates : coordinatesArgumentCaptor.getAllValues()) {
            switch (i++) {
                case 1:
                    Assertions.assertEquals(new Ev3Client.Coordinates(0.0, 0.0, 0.0), coordinates);
                    break;
                case 2:
                case 3:
                    Assertions.assertEquals(new Ev3Client.Coordinates(5.0, 6.0, 7.0), coordinates);
                    break;
            }
        }

    }

}
