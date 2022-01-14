package pageObjects;

import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.github.javafaker.Faker;

import utils.Utils;

public class EnterProductDataPage {

	protected WebDriver driver;

	protected Faker faker = new Faker(new Locale("en-US"));

	// Inicialização da pagina
	public EnterProductDataPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Aba ativa
	@FindBy(how = How.XPATH, using = "//li[@class='idealsteps-step-active']/a[@id='enterproductdata']")
	private WebElement abaProductData;

	// Formulario

	@FindBy(how = How.ID, using = "startdate")
	private WebElement inputStartDate;

	@FindBy(how = How.ID, using = "insurancesum")
	private WebElement selectInsuranceSum;

	@FindBy(how = How.ID, using = "meritrating")
	private WebElement selectMeritRating;

	@FindBy(how = How.ID, using = "damageinsurance")
	private WebElement selectDamageInsurance;

	@FindBy(how = How.ID, using = "EuroProtection")
	private WebElement radioEuroProtection;

	@FindBy(how = How.ID, using = "LegalDefenseInsurance")
	private WebElement radioLegalDefenseInsurance;

	@FindBy(how = How.ID, using = "courtesycar")
	private WebElement selectCourtesyCar;

	@FindBy(how = How.XPATH, using = "//a[@id='enterproductdata']/span[@class='counter zero']")
	private WebElement spanProductDataCounter0;

	@FindBy(how = How.ID, using = "nextselectpriceoption")
	private WebElement buttonNextPriceOption;

	// Metodos

	public void validarAbaProductDataEstaAtiva() {
		assertTrue(abaProductData.isDisplayed());
	}

	public void validarCamposObrigatoriosAbaProductDataEstaoPreenchidos() {
		// Se os campos obrigatorios nao forem totalmente preenchidos, estara com outro
		// valor e o teste quebra
		assertTrue(spanProductDataCounter0.isDisplayed());
	}

	public void preencherFormularioProductData() {

		validarAbaProductDataEstaAtiva();

		inputStartDate.sendKeys("" + Utils.randomDateOver30Days());

		Utils.selectAleatorio(selectInsuranceSum);

		Utils.selectAleatorio(selectMeritRating);

		Utils.selectAleatorio(selectDamageInsurance);

		preencherOptionalProducts();

		Utils.selectAleatorio(selectCourtesyCar);

		validarCamposObrigatoriosAbaProductDataEstaoPreenchidos();

		buttonNextPriceOption.click();
	}

	public void preencherOptionalProducts() {

		Actions action = new Actions(driver);

		// Nesse momento eu devo marcar pelo menos um dos produtos

		if (Utils.randomEntreDoisNumeros(1, 0) == 1) {
			action.moveToElement(radioEuroProtection).click().perform();
			if (Utils.randomEntreDoisNumeros(1, 0) == 1) {
				action.moveToElement(radioLegalDefenseInsurance).click().perform();
			}
		} else {
			action.moveToElement(radioLegalDefenseInsurance).click().perform();
		}

	}

	public void naoPreencherUmOuMaisCamposObrigatoriosFormularioProductData() {

		validarAbaProductDataEstaAtiva();
		
		// Para sempre existir um campo nao preenchido, removi o startDate

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)			
		Utils.selectAleatorio(selectInsuranceSum);
		
		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
		Utils.selectAleatorio(selectMeritRating);

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
		Utils.selectAleatorio(selectDamageInsurance);

		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
		preencherOptionalProducts();
		
		if (Utils.randomEntreDoisNumeros(1, 0) == 1)
		Utils.selectAleatorio(selectCourtesyCar);

		buttonNextPriceOption.click();
	}

}
