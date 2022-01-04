package guru.springframework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

/**
 * Created by jt on 2018-10-29.
 */
public class AnnotationMocksTest {
// chapter 105. Creating Mockito Mocks with Annotations, not needed

    @Mock // will be initialised
    Map<String, Object> mapMock;

    @BeforeEach // for each test method we create a Mock + inject into the class
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testMock() {
        mapMock.put("keyvalue", "foo");
    }
}
