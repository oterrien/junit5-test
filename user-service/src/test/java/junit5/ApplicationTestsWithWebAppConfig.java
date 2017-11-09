package junit5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sample.Application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Tag("unit")
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
public class ApplicationTestsWithWebAppConfig {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testHello() throws Exception {
        MvcResult result = mockMvc.perform(get("/hello")).andReturn();
        assertEquals("Hello from HelloService", result.getResponse().getContentAsString());
    }

    @Test
    public void testIsAlive() throws Exception {
        MvcResult result = mockMvc.perform(get("/isAlive")).andReturn();
        assertEquals(true, Boolean.valueOf((result.getResponse().getContentAsString())));
    }

}