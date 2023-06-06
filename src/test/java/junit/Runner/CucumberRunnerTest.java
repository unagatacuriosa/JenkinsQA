package junit.Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty"
        },
        tags = "@InventarySize",
        glue = {
                "junit/stepdefs",
                "src/test/support"
        },
        features = {
                "src/test/resources"
        }
)
public class CucumberRunnerTest {

}
