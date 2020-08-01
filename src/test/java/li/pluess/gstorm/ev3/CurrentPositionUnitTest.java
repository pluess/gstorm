package li.pluess.gstorm.ev3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author Ernst Plüss
 * @since 09.07.20
 */
public class CurrentPositionUnitTest {

    @Mock
    private Ev3Client ev3Client;

    @Captor
    private ArgumentCaptor<Ev3Client.ArcCoordinates> arcCoordinatesArgumentCaptor;

    private G00 g00;
    private G02 g02;
    private CurrentPosition currentPosition;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        currentPosition = new CurrentPosition();
        g00 = new G00(ev3Client, currentPosition);
        g02 = new G02(ev3Client, currentPosition);
    }

    @Test
    public void execute() {
        g00.execute(5.0, 6.0, 7.0);
        CurrentPosition expectedPosition1 = new CurrentPosition(5.0, 6.0, 7.0);
        Assertions.assertEquals(expectedPosition1, currentPosition);

        g02.execute(5.1, 6.1, 7.1, 8.1, 9.1);
        CurrentPosition expectedPosition2 = new CurrentPosition(5.1, 6.1, 7.1);
        Assertions.assertEquals(expectedPosition2, currentPosition);

        g00.execute(null, null, null);
        Assertions.assertEquals(expectedPosition2, currentPosition);

        g02.execute(null, null, null, 8.0, 9.0);
        Assertions.assertEquals(expectedPosition2, currentPosition);

    }

}
