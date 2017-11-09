package junit5;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MvcResult;
import sample.Application;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("unit")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHello() throws Exception {
        String result = restTemplate.getForEntity("/hello", String.class).getBody();
        assertEquals("Hello from HelloService", result);
    }


    @Test
    public void testIsAlive() throws Exception {
        Boolean result = restTemplate.getForEntity("/isAlive", Boolean.class).getBody();
        assertEquals(true, result);
    }
}