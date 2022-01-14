package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

import io.cucumber.java.Scenario;
import stepDefinitions.Hooks;

public class Utils {
	
	public static WebDriver driver;

	static Faker faker = new Faker(new Locale("en-US"));

	public static int randomOpcao(int numOpcoes) {
		Random r = new Random();
		int opcaoAleatoria = r.nextInt(numOpcoes);
		if (opcaoAleatoria == 0) {
			return opcaoAleatoria + 1;
		} else {
			return opcaoAleatoria;
		}
	}

	public static void selectAleatorio(WebElement webElementSelect) {
		Select select = new Select(webElementSelect);
		List<WebElement> optionsList = select.getOptions();
		select.selectByIndex(Utils.randomOpcao(optionsList.size()));
	}
	
	public static void selectInvalido(WebElement webElementSelect) {
		
		// Primeiro para o sistema aceitar como erro, preciso marcar um valor valido
		selectAleatorio(webElementSelect);
		
		Select select = new Select(webElementSelect);
		select.selectByIndex(0);
	}


	public static int randomEntreDoisNumeros(int maior, int menor) {
		Random r = new Random();
		return r.nextInt(maior - menor + 1) + menor;
	}

	public static String randomDataEntreDuasDatas() {

		LocalDate startDate = LocalDate.of(1000, 1, 1); // primeira data aceita pelo sistema
		long start = startDate.toEpochDay();

		LocalDate endDate = LocalDate.now(); // ultima data aceita pelo sistema
		long end = endDate.toEpochDay();

		long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
		LocalDate data = LocalDate.ofEpochDay(randomEpochDay); // data aleatoria entre as duas

		return data.getMonthValue() + "/" + data.getDayOfMonth() + "/" + data.getYear();
	}

	public static String randomDateBetween18And70() {

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		Calendar mininumAge = Calendar.getInstance();
		mininumAge.add(Calendar.YEAR, -18);

		Date dateMinimum = mininumAge.getTime();

		Calendar maximumAge = Calendar.getInstance();
		maximumAge.add(Calendar.YEAR, -70);

		Date dateMaximum = maximumAge.getTime();

		return sdf.format(faker.date().between(dateMaximum, dateMinimum));
	}
	
	public static String randomDateOver30Days() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.MONTH, 1);

		return sdf.format(faker.date().future(31, TimeUnit.DAYS, cal.getTime()));
	}
	
	public static String nextDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.DAY_OF_MONTH, 1);

		return sdf.format(cal.getTime());
	}
	
	
	// --- Metodos de espera

	public static void esperarElemento(WebElement elemento) {
		driver = Hooks.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(elemento));
	}

	public static void esperarPagina(int tempo) {
		driver = Hooks.getDriver();
		driver.manage().timeouts().implicitlyWait(tempo, TimeUnit.SECONDS);
	}
	
	public static File gerarScreenShot(Scenario cenario) {
		driver = Hooks.getDriver();
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		cenario.attach(screenshot, "image/png", "finalScreenshot");
		File imagem = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(imagem, (new File("./target/screenshots", cenario.getName() + "-" + cenario.getStatus() + ".png")));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return imagem;
	}

	
}
