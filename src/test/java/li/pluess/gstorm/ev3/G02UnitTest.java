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
public class G02UnitTest {

    @Mock
    private Ev3Client ev3Client;

    @Captor
    private ArgumentCaptor<Ev3Client.ArcCoordinates> arcCoordinatesArgumentCaptor;

    private G02 g02;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        g02 = new G02(ev3Client, new CurrentPosition());
    }

    @Test
    public void execute() {
        g02.execute(null, null, null, null, null);
        g02.execute(5.0, 6.0, 7.0, 8.0, 9.0);
        g02.execute(null, null, null, null, null);

        verify(ev3Client, times(3)).moveArc(
                arcCoordinatesArgumentCaptor.capture());

        int i = 1;
        for (Ev3Client.ArcCoordinates arcCoordinates : arcCoordinatesArgumentCaptor.getAllValues()) {
            switch (i++) {
                case 1:
                    Assertions.assertEquals(new Ev3Client.ArcCoordinates(0.0, 0.0, 0.0, 0.0, 0.0), arcCoordinates);
                    break;
                case 2:
                    Assertions.assertEquals(new Ev3Client.ArcCoordinates(5.0, 6.0, 7.0, 8.0, 9.0), arcCoordinates);
                    break;
                case 3:
                    Assertions.assertEquals(new Ev3Client.ArcCoordinates(5.0, 6.0, 7.0, 0.0, 0.0), arcCoordinates);
                    break;
            }
        }

    }

}
