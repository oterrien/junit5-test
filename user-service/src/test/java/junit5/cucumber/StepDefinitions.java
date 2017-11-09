package junit5.cucumber;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sample.Application;
import sample.HelloService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Slf4j
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
// Log level is defined in logback.xml
public class StepDefinitions {

    private ScenarioContext scenarioContext = new ScenarioContext();

    private MockMvc mockMvc;

    @Autowired
    private HelloService helloService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() throws Exception {
        scenarioContext.clear();
    }

    @Given("the service is alive")
    public void givenServiceAlive() throws Exception {

        System.out.println(helloService.getName());

        MvcResult result = mockMvc.perform(get("/isAlive")).andReturn();
        assertEquals(true, Boolean.valueOf(result.getResponse().getContentAsString()));
    }

    @When("I call the uri '(.*)'")
    public void whenICallTheUri(String uri) throws Exception {
        MvcResult result = mockMvc.perform(get("/hello")).andReturn();
        scenarioContext.put("result", result.getResponse().getContentAsString());
    }


    @Then("I should receive '(.*)'")
    public void thenIShouldReceive(String message) throws Exception {
        assertEquals(message, scenarioContext.get("result"));
    }
}