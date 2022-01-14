package pageObjects;

import static org.junit.Assert.assertEquals;
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

public class SelectPriceOptionPage {

	protected WebDriver driver;

	protected Faker faker = new Faker(new Locale("en-US"));

	// Inicialização da pagina
	public SelectPriceOptionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Aba ativa
	@FindBy(how = How.XPATH, using = "//li[@class='idealsteps-step-active']/a[@id='selectpriceoption']")
	private WebElement abaPriceOption;

	// Formulario select price option

	@FindBy(how = How.ID, using = "selectsilver")
	private WebElement radioSilver;

	@FindBy(how = How.ID, using = "selectgold")
	private WebElement radioGold;

	@FindBy(how = How.ID, using = "selectplatinum")
	private WebElement radioPlatinum;

	@FindBy(how = How.ID, using = "selectultimate")
	private WebElement radioUltimate;

	@FindBy(how = How.XPATH, using = "//a[@id='selectpriceoption']/span[@class='counter zero']")
	private WebElement spanPriceOptionCounter0;

	@FindBy(how = How.ID, using = "nextsendquote")
	private WebElement buttonNextSendQuote;
	
	@FindBy(how = How.XPATH, using = "//div[@id='xLoaderPrice']/p")
	private WebElement paragrafoMensagemSolicitacao;

	// Metodos

	public void validarAbaPriceOptionEstaAtiva() {
		assertTrue(abaPriceOption.isDisplayed());
	}

	public void validarCamposObrigatoriosAbaPriceOptionEstaoPreenchidos() {
		// Se os campos obrigatorios nao forem totalmente preenchidos, estara com outro
		// valor e o teste quebra
		assertTrue(spanPriceOptionCounter0.isDisplayed());
	}

	public void preencherFormularioPriceOption() {

		Actions action = new Actions(driver);

		validarAbaPriceOptionEstaAtiva();

		// Estou reutilizando o metodo, a opcao 0 não é considerada
		int value = Utils.randomOpcao(5);

		if (value == 1)
			action.moveToElement(radioSilver).click().perform();
		if (value == 2)
			action.moveToElement(radioGold).click().perform();
		if (value == 3)
			action.moveToElement(radioPlatinum).click().perform();
		if (value == 4)
			action.moveToElement(radioUltimate).click().perform();

		validarCamposObrigatoriosAbaPriceOptionEstaoPreenchidos();

		buttonNextSendQuote.click();
	}
	
	public void validarSeCamposObrigatoriosAnterioresForamPreenchidos() {		
		assertEquals("Please, complete the first three steps to see the price table.", paragrafoMensagemSolicitacao.getText());
	}

}
