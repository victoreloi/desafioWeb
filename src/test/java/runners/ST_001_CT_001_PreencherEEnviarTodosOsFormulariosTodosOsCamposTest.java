package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions( 
		features = "classpath:Features",
		glue = "stepDefinitions",
		plugin = {"pretty", "html:target/cucumber-reports"},
		monochrome = true,
		snippets = SnippetType.CAMELCASE,
		dryRun = false,
		tags = "@ST_001_CT_001_PreencherEEnviarTodosOsFormulariosTodosOsCampos"
		)

public class ST_001_CT_001_PreencherEEnviarTodosOsFormulariosTodosOsCamposTest {
	
}