import org.junit.*;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class ExampleWebTestCase {

    @Before
    public void prepare() {
        setBaseUrl("http://127.0.0.1:8000");
    }

    @Test
    public void testLogin() {
        //setTextField("query", "test");
        //setTextField("password", "test123");
        //submit();
        assertTitleEquals("test - Startpage Web Search");
    }
}