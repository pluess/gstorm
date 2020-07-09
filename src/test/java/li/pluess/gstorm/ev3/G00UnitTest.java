package li.pluess.gstorm.ev3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Ernst Plüss
 * @since 09.07.20
 */
public class G00UnitTest {

    @Spy
    private Ev3Controller ev3Controller;

    @InjectMocks
    private G00 g00;

    @Captor
    ArgumentCaptor<Double> xCaptor;

    @Captor
    ArgumentCaptor<Double> yCaptor;

    @Captor
    ArgumentCaptor<Double> zCaptor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void execute() {
        g00.execute(null, null, null);
        g00.execute(5.0, 6.0, 7.0);
        g00.execute(null, null, null);

        verify(ev3Controller, times(3)).goTo(
                xCaptor.capture(),
                yCaptor.capture(),
                zCaptor.capture());

        Assertions.assertEquals(
                Arrays.asList(0.0, 5.0, 5.0),
                xCaptor.getAllValues());

        Assertions.assertEquals(
                Arrays.asList(0.0, 6.0, 6.0),
                yCaptor.getAllValues());

        Assertions.assertEquals(
                Arrays.asList(0.0, 7.0, 7.0),
                zCaptor.getAllValues());

    }

}
