package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	public static WebDriver driver;
	
	@Before
	public void iniciarDriver() {
		System.setProperty("webdriver.edge.driver", "C://driver//msedgedriver.exe");
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void fecharDriver(Scenario cenario) {
		utils.Utils.gerarScreenShot(cenario);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.quit();
	}
	
	public static WebDriver getDriver() {
		return driver;
	}

	public static void abrirUrl(String url) {
		driver.get(url);
	}
}