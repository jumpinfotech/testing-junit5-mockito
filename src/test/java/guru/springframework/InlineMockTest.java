package guru.springframework;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by jt on 2018-10-29.
 */
public class InlineMockTest {
// chapter 104. Creating Mockito Mocks Inline, not important

    @Test
    void testInlineMock() {
        // use static mock method from Mockito to create a mock Map interface,
        // should not use
        Map mapMock = mock(Map.class);

        assertEquals(mapMock.size(), 0); // int 0 is expected
    }
}
