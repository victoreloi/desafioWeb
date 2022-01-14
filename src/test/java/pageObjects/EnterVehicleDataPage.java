package pageObjects;

import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.github.javafaker.Faker;

import utils.Utils;

public class EnterVehicleDataPage {

	protected WebDriver driver;

	protected Faker faker = new Faker(new Locale("en-US"));

	// Inicialização da pagina
	public EnterVehicleDataPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Mapeamento de elementos

	// Aba ativa

	@FindBy(how = How.XPATH, using = "//li[@class='idealsteps-step-active']/a[@id='entervehicledata']")
	private WebElement abaVehicleData;

	// Formulario

	@FindBy(how = How.ID, using = "make")
	private WebElement selectMake;

	@FindBy(how = How.ID, using = "model")
	private WebElement selectModel;

	@FindBy(how = How.ID, using = "cylindercapacity")
	private WebElement inputCylinderCapacity;

	@FindBy(how = How.ID, using = "engineperformance")
	private WebElement inputEnginePerfomance;

	@FindBy(how = How.ID, using = "dateofmanufacture")
	private WebElement inputDateManufacture;

	@FindBy(how = How.ID, using = "numberofseats")
	private WebElement selectNumberSeats;

	@FindBy(how = How.ID, using = "righthanddriveyes")
	private WebElement radioRightHandYes;

	@FindBy(how = How.ID, using = "righthanddriveno")
	private WebElement radioRightHandNo;

	@FindBy(how = How.ID, using = "numberofseatsmotorcycle")
	private WebElement selectNumberSeatsMotorcycle;

	@FindBy(how = How.ID, using = "fuel")
	private WebElement selectFuelType;

	@FindBy(how = How.ID, using = "payload")
	private WebElement inputPayload;

	@FindBy(how = How.ID, using = "totalweight")
	private WebElement inputTotalWeight;

	@FindBy(how = How.ID, using = "listprice")
	private WebElement inputListPrice;

	@FindBy(how = How.ID, using = "licenseplatenumber")
	private WebElement inputLicencePlateNumber;

	@FindBy(how = How.ID, using = "annualmileage")
	private WebElement inputAnnualMileage;

	@FindBy(how = How.XPATH, using = "//a[@id='entervehicledata']/span[@class='counter zero']")
	private WebElement spanVehicleDataCounter0;

	@FindBy(how = How.ID, using = "nextenterinsurantdata")
	private WebElement buttonNextInsurantData;

	// Divs com class INVALID!

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'invalid')]/select[@id='make']")
	private WebElement divMakeInvalid;

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'invalid')]/select[@id='model']")
	private WebElement divModelInvalid;

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'invalid')]/input[@id='cylindercapacity']")
	private WebElement divCylinderCapacityInvalid;

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'invalid')]/input[@id='engineperformance']")
	private WebElement divEnginePerformanceInvalid;

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'invalid')]/input[@id='dateofmanufacture']")
	private WebElement divDateOfManufactureInvalid;

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'invalid')]/select[@id='numberofseats']")
	private WebElement divNumberSeatsInvalid;

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'invalid')]/select[@id='numberofseatsmotorcycle']")
	private WebElement divNumberSeatsMotorcycleInvalid;

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'invalid')]/select[@id='fuel']")
	private WebElement divFuelInvalid;

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'invalid')]/input[@id='payload']")
	private WebElement divPayloadInvalid;

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'invalid')]/input[@id='totalweight']")
	private WebElement divTotalWeightInvalid;

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'invalid')]/input[@id='listprice']")
	private WebElement divListPriceInvalid;

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'invalid')]/input[@id='licenseplatenumber']")
	private WebElement divLicensePlatenumberInvalid;

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'invalid')]/input[@id='annualmileage']")
	private WebElement divAnnualMileageInvalid;

	// Validacoes

	public void validarAbaVehicleDataEstaAtiva() {
		assertTrue(abaVehicleData.isDisplayed());
	}

	public void validarCamposObrigatoriosAbaVehicleDataEstaoPreenchidos() {
		// Se os campos obrigatorios nao forem totalmente preenchidos, estara com outro
		// valor e o teste quebra
		assertTrue(spanVehicleDataCounter0.isDisplayed());
	}

	// Metodos

	public void preencherFormularioVehicleData() {

		validarAbaVehicleDataEstaAtiva();

		Utils.selectAleatorio(selectMake);

		Utils.selectAleatorio(selectModel);

		Utils.selectAleatorio(selectNumberSeats);

		inputCylinderCapacity.sendKeys("" + Utils.randomEntreDoisNumeros(2000, 1));

		inputEnginePerfomance.sendKeys("" + Utils.randomEntreDoisNumeros(2000, 1));

		inputDateManufacture.sendKeys(Utils.randomDataEntreDuasDatas());

		// *** Inicio bloco right hand drive
		Actions action = new Actions(driver);

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			action.moveToElement(radioRightHandYes).click().perform();
		else
			action.moveToElement(radioRightHandNo).click().perform();

		// *** fim bloco right hand drive

		Utils.selectAleatorio(selectNumberSeatsMotorcycle);

		Utils.selectAleatorio(selectFuelType);

		inputPayload.sendKeys("" + Utils.randomEntreDoisNumeros(1000, 1));

		inputTotalWeight.sendKeys("" + Utils.randomEntreDoisNumeros(50000, 100));

		inputListPrice.sendKeys("" + faker.number().randomDouble(2, 500, 100000));

		inputLicencePlateNumber.sendKeys(RandomStringUtils.randomAlphabetic(10));

		inputAnnualMileage.sendKeys("" + Utils.randomEntreDoisNumeros(100000, 100));

		validarCamposObrigatoriosAbaVehicleDataEstaoPreenchidos();

		buttonNextInsurantData.click();
	}

	public void preencherApenasCamposObrigatoriosFormularioVehicleData() {

		validarAbaVehicleDataEstaAtiva();

		Utils.selectAleatorio(selectMake);

		Utils.selectAleatorio(selectModel);

		Utils.selectAleatorio(selectNumberSeats);

		inputCylinderCapacity.sendKeys("" + Utils.randomEntreDoisNumeros(2000, 1));

		inputEnginePerfomance.sendKeys("" + Utils.randomEntreDoisNumeros(2000, 1));

		inputDateManufacture.sendKeys(Utils.randomDataEntreDuasDatas());

		Utils.selectAleatorio(selectNumberSeatsMotorcycle);

		Utils.selectAleatorio(selectFuelType);

		inputPayload.sendKeys("" + faker.number().randomDouble(2, 1, 1000));

		inputTotalWeight.sendKeys("" + faker.number().randomDouble(2, 100, 50000));

		inputListPrice.sendKeys("" + faker.number().randomDouble(2, 500, 100000));

		inputLicencePlateNumber.sendKeys(RandomStringUtils.randomAlphabetic(10));

		inputAnnualMileage.sendKeys("" + Utils.randomEntreDoisNumeros(100000, 100));

		validarCamposObrigatoriosAbaVehicleDataEstaoPreenchidos();

		buttonNextInsurantData.click();
	}

	public void naoPreencherUmOuMaisCamposObrigatoriosFormularioVehicleData() {

		validarAbaVehicleDataEstaAtiva();

		// Para sempre existir um campo nao preenchido, removi o selectMake

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			Utils.selectAleatorio(selectModel);

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			Utils.selectAleatorio(selectNumberSeats);

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			inputCylinderCapacity.sendKeys("" + Utils.randomEntreDoisNumeros(2000, 1));

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			inputEnginePerfomance.sendKeys("" + Utils.randomEntreDoisNumeros(2000, 1));

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			inputDateManufacture.sendKeys(Utils.randomDataEntreDuasDatas());

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			Utils.selectAleatorio(selectNumberSeatsMotorcycle);

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			Utils.selectAleatorio(selectFuelType);

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			inputPayload.sendKeys("" + faker.number().randomDouble(2, 1, 1000));

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			inputTotalWeight.sendKeys("" + faker.number().randomDouble(2, 100, 50000));

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			inputListPrice.sendKeys("" + faker.number().randomDouble(2, 500, 100000));

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			inputLicencePlateNumber.sendKeys(RandomStringUtils.randomAlphabetic(10));

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			inputAnnualMileage.sendKeys("" + Utils.randomEntreDoisNumeros(100000, 100));

		buttonNextInsurantData.click();
	}

	public void preencherFormularioVehicleDataComSomenteErros() {

		validarAbaVehicleDataEstaAtiva();

		Utils.selectInvalido(selectMake);

		Utils.selectInvalido(selectModel);

		Utils.selectInvalido(selectNumberSeats);

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			inputCylinderCapacity.sendKeys("0");
		else
			inputCylinderCapacity.sendKeys("2001");

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			inputEnginePerfomance.sendKeys("0");
		else
			inputEnginePerfomance.sendKeys("2001");

		inputDateManufacture.sendKeys(Utils.nextDay());

		// *** Não preencho o right hand drive

		Utils.selectInvalido(selectNumberSeatsMotorcycle);

		Utils.selectInvalido(selectFuelType);

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			inputPayload.sendKeys("0");
		else
			inputPayload.sendKeys("1001");

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			inputTotalWeight.sendKeys("99");
		else
			inputTotalWeight.sendKeys("50001");

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			inputListPrice.sendKeys("499");
		else
			inputListPrice.sendKeys("100001");

		inputLicencePlateNumber.sendKeys(RandomStringUtils.randomAlphabetic(11));

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
			inputAnnualMileage.sendKeys("99");
		else
			inputAnnualMileage.sendKeys("100001");
	}

	public void validarTodosOsCamposComErro() {
		assertTrue(divMakeInvalid.isDisplayed());
		assertTrue(divModelInvalid.isDisplayed());
		assertTrue(divCylinderCapacityInvalid.isDisplayed());
		assertTrue(divEnginePerformanceInvalid.isDisplayed());
		assertTrue(divDateOfManufactureInvalid.isDisplayed());
		assertTrue(divNumberSeatsInvalid.isDisplayed());
		assertTrue(divNumberSeatsMotorcycleInvalid.isDisplayed());
		assertTrue(divFuelInvalid.isDisplayed());
		assertTrue(divPayloadInvalid.isDisplayed());
		assertTrue(divTotalWeightInvalid.isDisplayed());
		assertTrue(divListPriceInvalid.isDisplayed());
		assertTrue(divLicensePlatenumberInvalid.isDisplayed());
		assertTrue(divAnnualMileageInvalid.isDisplayed());
	}

}
