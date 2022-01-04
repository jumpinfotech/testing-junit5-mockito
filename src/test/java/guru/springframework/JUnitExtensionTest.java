package guru.springframework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

/**
 * Created by jt on 2018-10-29.
 */
// JUnit 5 extension, MockitoExtension.class>in beforeEach it runs initMocks.
// JT - says cleaner than @BeforeEach>calling MockitoAnnotations.initMocks(this);
@ExtendWith(MockitoExtension.class)
public class JUnitExtensionTest {

    @Mock
    Map<String, Object> mapMock;

    @Test
    void testMock() {
        mapMock.put("keyvalue", "foo");
    }
}
