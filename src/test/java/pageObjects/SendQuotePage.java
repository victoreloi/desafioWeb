package pageObjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.github.javafaker.Faker;

import utils.Utils;

public class SendQuotePage {

	static Faker faker = new Faker(new Locale("en-US"));
	
	protected WebDriver driver;

	// Inicialização da pagina

	public SendQuotePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Aba ativa

	@FindBy(how = How.XPATH, using = "//li[@class='idealsteps-step-active']/a[@id='sendquote']")
	private WebElement abaSendQuote;

	// Formulario Send Quote

	@FindBy(how = How.ID, using = "email")
	private WebElement inputEmail;

	@FindBy(how = How.ID, using = "phone")
	private WebElement inputPhone;

	@FindBy(how = How.ID, using = "username")
	private WebElement inputUsername;

	@FindBy(how = How.ID, using = "password")
	private WebElement inputPassword;

	@FindBy(how = How.ID, using = "confirmpassword")
	private WebElement inputConfirmPassword;

	@FindBy(how = How.ID, using = "Comments")
	private WebElement textAreaComments;

	@FindBy(how = How.XPATH, using = "//a[@id='sendquote']/span[@class='counter zero']")
	private WebElement spanSendQuoteCounter0;

	@FindBy(how = How.ID, using = "sendemail")
	private WebElement buttonSendEmail;

	// Alert box

	@FindBy(how = How.XPATH, using = "//body/div[@class='sweet-alert showSweetAlert visible']")
	private WebElement divAlert;

	@FindBy(how = How.XPATH, using = "//h2[text()='Sending e-mail success!']")
	private WebElement textEmailSucess;

	// Metodos

	public void validarAbaSendQuoteEstaAtiva() {
		assertTrue(abaSendQuote.isDisplayed());
	}

	public void validarCamposObrigatoriosAbaSendQuoteEstaoPreenchidos() {
		assertTrue(spanSendQuoteCounter0.isDisplayed());
	}

	public void preencherFormularioSendQuote() {

		validarAbaSendQuoteEstaAtiva();

		String firstName = faker.name().firstName().toLowerCase().replace("-", "");

		String lastName = faker.name().firstName().toLowerCase().replace("-", "");

		inputEmail.sendKeys(firstName + "." + lastName + "@email.com");

		inputPhone.sendKeys(faker.number().digits(15));

		inputUsername.sendKeys(firstName + "." + lastName);

		String randomPassword = faker.pokemon().name().toLowerCase() + "1A";

		inputPassword.sendKeys(randomPassword);

		inputConfirmPassword.sendKeys(randomPassword);

		textAreaComments.sendKeys(faker.lorem().characters(0, 300));

		validarCamposObrigatoriosAbaSendQuoteEstaoPreenchidos();

		buttonSendEmail.click();
	}

	public void validarEnvioEmailComSucesso() {

		Utils.esperarElemento(divAlert);

		assertEquals("Sending e-mail success!", textEmailSucess.getText());
	}
}
