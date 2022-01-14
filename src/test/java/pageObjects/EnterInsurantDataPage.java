package pageObjects;

import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;

import utils.Utils;

public class EnterInsurantDataPage {

	protected WebDriver driver;

	protected Faker faker = new Faker(new Locale("en-US"));

	// Inicialização da pagina
	public EnterInsurantDataPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Aba ativa
	@FindBy(how = How.XPATH, using = "//li[@class='idealsteps-step-active']/a[@id='enterinsurantdata']")
	private WebElement abaInsurantData;

	// Formulario

	@FindBy(how = How.ID, using = "firstname")
	private WebElement inputFirstName;

	@FindBy(how = How.ID, using = "lastname")
	private WebElement inputLastName;

	@FindBy(how = How.ID, using = "birthdate")
	private WebElement inputBirthdate;

	@FindBy(how = How.ID, using = "gendermale")
	private WebElement radioGenderMale;

	@FindBy(how = How.ID, using = "genderfemale")
	private WebElement radioGenderFemale;

	@FindBy(how = How.ID, using = "streetaddress")
	private WebElement inputStreetAdress;

	@FindBy(how = How.ID, using = "country")
	private WebElement selectCountry;

	@FindBy(how = How.ID, using = "zipcode")
	private WebElement inputZipCode;

	@FindBy(how = How.ID, using = "city")
	private WebElement inputCity;

	@FindBy(how = How.ID, using = "occupation")
	private WebElement selectOcupation;

	@FindBy(how = How.ID, using = "speeding")
	private WebElement radioSpeeding;

	@FindBy(how = How.ID, using = "bungeejumping")
	private WebElement radioBungeeJumping;

	@FindBy(how = How.ID, using = "cliffdiving")
	private WebElement radioCliffDiving;

	@FindBy(how = How.ID, using = "skydiving")
	private WebElement radioSkydiving;

	@FindBy(how = How.ID, using = "other")
	private WebElement radioOther;

	@FindBy(how = How.XPATH, using = "//div[@class='field idealforms-field idealforms-field-checkbox valid']")
	private WebElement divHobbiesIsValid;

	@FindBy(how = How.ID, using = "website")
	private WebElement inputWebsite;

	@FindBy(how = How.ID, using = "picture")
	private WebElement inputPicture;

	@FindBy(how = How.ID, using = "open")
	private WebElement buttonOpen;

	@FindBy(how = How.XPATH, using = "//a[@id='enterinsurantdata']/span[@class='counter zero']")
	private WebElement spanInsurantDataCounter0;

	@FindBy(how = How.ID, using = "nextenterproductdata")
	private WebElement buttonNextProductData;

	// --- Anexos de teste

	String caminhoAnexo = System.getProperty("user.dir") + "\\src\\test\\resources\\anexos\\";
	public String testeJPEG = "testeJPEG.jpeg";
	public String testeJPG = "testeJPG.jpg";
	public String testePNG = "testePNG.png";

	// Metodos

	public void validarAbaInsurantDataEstaAtiva() {
		assertTrue(abaInsurantData.isDisplayed());
	}

	public void validarCamposObrigatoriosAbaInsurantDataEstaoPreenchidos() {
		// Se os campos obrigatorios nao forem totalmente preenchidos, estara com outro
		// valor e o teste quebra
		assertTrue(spanInsurantDataCounter0.isDisplayed());
	}

	public void preencherFormularioInsurantData() {

		validarAbaInsurantDataEstaAtiva();

		inputFirstName.sendKeys(faker.name().firstName().replace("'", ""));

		inputLastName.sendKeys(faker.name().lastName().replace("'", ""));

		inputBirthdate.sendKeys("" + Utils.randomDateBetween18And70());

		// *** Inicio bloco Gender
		Actions action = new Actions(driver);

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			action.moveToElement(radioGenderMale).click().perform();
		else
			action.moveToElement(radioGenderFemale).click().perform();
		// *** Fim bloco Gender

		Address adress = faker.address();

		inputStreetAdress.sendKeys(adress.streetAddress());

		Utils.selectAleatorio(selectCountry);

		inputZipCode.sendKeys(RandomStringUtils.randomNumeric(8));

		inputCity.sendKeys(adress.city());

		Utils.selectAleatorio(selectOcupation);

		preencherHobbies();

		inputWebsite.sendKeys(faker.internet().domainName());

		// *** Inicio bloco Picture
		String path = new File(caminhoAnexo + randomTestFile()).getAbsolutePath();

		fileUploadRobot(path);
		// *** Fim bloco Picture

		validarCamposObrigatoriosAbaInsurantDataEstaoPreenchidos();

		buttonNextProductData.click();
	}

	public void fileUploadRobot(String path) {

		buttonOpen.click();

		StringSelection ss = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		Robot robot;
		try {
			robot = new Robot();
			robot.delay(250);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(90);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	public String randomTestFile() {

		String arquivoTeste = testeJPEG; // valor padrao

		int opcao = Utils.randomEntreDoisNumeros(3, 1);

		if (opcao == 1)
			arquivoTeste = testeJPEG;
		if (opcao == 2)
			arquivoTeste = testeJPG;
		if (opcao == 3)
			arquivoTeste = testePNG;

		return arquivoTeste;
	}

	public void preencherHobbies() {

		Actions action = new Actions(driver);

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			action.moveToElement(radioSpeeding).click().perform();

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			action.moveToElement(radioBungeeJumping).click().perform();

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			action.moveToElement(radioCliffDiving).click().perform();

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			action.moveToElement(radioSkydiving).click().perform();

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			action.moveToElement(radioOther).click().perform();

		// Caso a div de hobbies nao seja valida entao todos os randons deram 0, pego a
		// exception e trato para chamar novamente o metodo
		try {
			assertTrue(divHobbiesIsValid.isDisplayed());
		} catch (Exception e) {
			preencherHobbies();
		}
	}

	public void preencherApenasCamposObrigatoriosFormularioInsurantData() {

		validarAbaInsurantDataEstaAtiva();

		inputFirstName.sendKeys(faker.name().firstName().replace("'", ""));

		inputLastName.sendKeys(faker.name().lastName().replace("'", ""));

		inputBirthdate.sendKeys("" + Utils.randomDateBetween18And70());

		Address adress = faker.address();

		inputStreetAdress.sendKeys(adress.streetAddress());

		Utils.selectAleatorio(selectCountry);

		inputZipCode.sendKeys(RandomStringUtils.randomNumeric(8));

		Utils.selectAleatorio(selectOcupation);

		preencherHobbies();

		inputWebsite.sendKeys(faker.internet().domainName());

		validarCamposObrigatoriosAbaInsurantDataEstaoPreenchidos();

		buttonNextProductData.click();
	}

	public void naoPreencherUmOuMaisCamposObrigatoriosFormularioInsurantData() {

		validarAbaInsurantDataEstaAtiva();

		// Para sempre existir um campo nao preenchido, removi o selectCountry

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			inputFirstName.sendKeys(faker.name().firstName().replace("'", ""));

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			inputLastName.sendKeys(faker.name().lastName().replace("'", ""));

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			inputBirthdate.sendKeys("" + Utils.randomDateBetween18And70());

		Address adress = faker.address();

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			inputStreetAdress.sendKeys(adress.streetAddress());

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			inputZipCode.sendKeys(RandomStringUtils.randomNumeric(8));

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			Utils.selectAleatorio(selectOcupation);

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			preencherHobbies();

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			inputWebsite.sendKeys(faker.internet().domainName());

		buttonNextProductData.click();
	}
}
