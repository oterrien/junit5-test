package junit5.cucumber;

import cucumber.api.CucumberOptions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.stream.Stream;

@Tag("cucumber")
@CucumberOptions(
        plugin = {"pretty"},
        features = "src/test/resources",
        tags = {"@Hello", "~@Ignore"},
        glue = "junit5.cucumber")
public class InfraCucumberTest {

    @ExtendWith(CucumberExtension.class)
    @TestFactory
    public Stream<DynamicTest> runCukes(Stream<DynamicTest> scenarios) {
        return scenarios;
    }
}