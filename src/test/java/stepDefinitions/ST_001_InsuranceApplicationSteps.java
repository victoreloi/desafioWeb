package stepDefinitions;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import pageObjects.EnterInsurantDataPage;
import pageObjects.EnterProductDataPage;
import pageObjects.EnterVehicleDataPage;
import pageObjects.SelectPriceOptionPage;
import pageObjects.SendQuotePage;

public class ST_001_InsuranceApplicationSteps {
	
	protected WebDriver driver;

	// ST_001_CT_001_PreencherEEnviarTodosOsFormulariosTodosOsCampos
	
	@Dado("^que o usuario esteja na pagina principal do sistema$")
	public void queOUsuarioEstejaNaPaginaPrincipalDoSistema() {
		Hooks.abrirUrl("http://sampleapp.tricentis.com/101/app.php");
		driver = Hooks.getDriver();
	}
	
	@Quando("^eu preencher o formulario presente na aba Enter Vehicle Data e pressionar next$")
	public void euPreencherOFormularioPresenteNaAbaEnterVehicleDataEPressionarNext() {
		EnterVehicleDataPage enterVehicleDataPage = new EnterVehicleDataPage(driver);
		enterVehicleDataPage.preencherFormularioVehicleData();
	}

	@Quando("^eu preencher o formulario presente na aba Enter Insurant Data e pressionar next$")
	public void euPreencherOFormularioPresenteNaAbaEnterInsurantDataEPressionarNext() {
		EnterInsurantDataPage enterInsurantDataPage = new EnterInsurantDataPage(driver);
		enterInsurantDataPage.preencherFormularioInsurantData();
	}

	@Quando("^eu preencher o formulario presente na aba Enter Product Data e pressionar next$")
	public void euPreencherOFormularioPresenteNaAbaEnterProductDataEPressionarNext() {
		EnterProductDataPage enterProductDataPage = new EnterProductDataPage(driver);
		enterProductDataPage.preencherFormularioProductData();
	}

	@Quando("^eu selecionar uma das opcoes presentes na aba Select Price Option e pressionar next$")
	public void euSelecionarUmaDasOpcoesPresentesNaAbaSelectPriceOptionEPressionarNext() {
		SelectPriceOptionPage selectPriceOptionPage = new SelectPriceOptionPage(driver);
		selectPriceOptionPage.preencherFormularioPriceOption();
	}

	@Quando("^eu preencher o formulario presente na aba Send Quote e pressionar Send$")
	public void euPreencherOFormularioPresenteNaAbaSendQuoteEPressionarSend() {
		SendQuotePage sendQuotePage = new SendQuotePage(driver);
		sendQuotePage.preencherFormularioSendQuote();
	}

	@Entao("^o sistema retorna que o e-mail foi enviado com sucesso$")
	public void oSistemaRetornaQueOEMailFoiEnviadoComSucesso() {
		SendQuotePage sendQuotePage = new SendQuotePage(driver);
		sendQuotePage.validarEnvioEmailComSucesso();
	}
	
	// ST_001_CT_002_PreencherEEnviarTodosOsFormulariosApenasCamposObrigatorios
	
	@Quando("^eu preencher o formulario presente na aba Enter Vehicle Data com apenas os obrigatorios e pressionar next$")
	public void euPreencherOFormularioPresenteNaAbaEnterVehicleDataComApenasOsObrigatoriosEPressionarNext() {
		EnterVehicleDataPage enterVehicleDataPage = new EnterVehicleDataPage(driver);
		enterVehicleDataPage.preencherApenasCamposObrigatoriosFormularioVehicleData();
	}

	@Quando("^eu preencher o formulario presente na aba Enter Insurant Data com apenas os obrigatorios e pressionar next$")
	public void euPreencherOFormularioPresenteNaAbaEnterInsurantDataComApenasOsObrigatoriosEPressionarNext() {
		EnterInsurantDataPage enterInsurantDataPage = new EnterInsurantDataPage(driver);
		enterInsurantDataPage.preencherApenasCamposObrigatoriosFormularioInsurantData();
	}
	
	// ST_001_CT_003_DeixarDePreencherUmOuMaisCamposObrigatorios
	
	@Quando("^eu preencher o formulario presente na aba Enter Vehicle Data sem um ou mais campos obrigatorios e pressionar next$")
	public void euPreencherOFormularioPresenteNaAbaEnterVehicleDataSemUmOuMaisCamposObrigatoriosEPressionarNext() {
		EnterVehicleDataPage enterVehicleDataPage = new EnterVehicleDataPage(driver);
		enterVehicleDataPage.naoPreencherUmOuMaisCamposObrigatoriosFormularioVehicleData();
	}

	@Quando("^eu preencher o formulario presente na aba Enter Insurant Data sem um ou mais os obrigatorios e pressionar next$")
	public void euPreencherOFormularioPresenteNaAbaEnterInsurantDataSemUmOuMaisOsObrigatoriosEPressionarNext() {
		EnterInsurantDataPage enterInsurantDataPage = new EnterInsurantDataPage(driver);
		enterInsurantDataPage.naoPreencherUmOuMaisCamposObrigatoriosFormularioInsurantData();
	}

	@Quando("^eu preencher o formulario presente na aba Enter Product Data sem um ou mais e pressionar next$")
	public void euPreencherOFormularioPresenteNaAbaEnterProductDataSemUmOuMaisEPressionarNext() {
		EnterProductDataPage enterProductDataPage = new EnterProductDataPage(driver);
		enterProductDataPage.naoPreencherUmOuMaisCamposObrigatoriosFormularioProductData();
	}

	@Entao("^na aba Select Price Option uma mensagem ira solicitar que eu complete os tres primeiros passos para continuar$")
	public void naAbaSelectPriceOptionUmaMensagemIraSolicitarQueEuCompleteOsTresPrimeirosPassosParaContinuar() {
		SelectPriceOptionPage selectPriceOptionPage = new SelectPriceOptionPage(driver);
		selectPriceOptionPage.validarSeCamposObrigatoriosAnterioresForamPreenchidos();
	}
	
	// ST_001_CT_004_PreencherComErroFormularioEnterVehicleData
	
	@Quando("^eu preencher o formulario presente na aba Enter Vehicle Data apenas com dados invalidos$")
	public void euPreencherOFormularioPresenteNaAbaEnterVehicleDataApenasComDadosInvalidos() {
		EnterVehicleDataPage enterVehicleDataPage = new EnterVehicleDataPage(driver);
		enterVehicleDataPage.preencherFormularioVehicleDataComSomenteErros();
	}

	@Entao("^todos os campos estarao sinalizando erro$")
	public void todosOsCamposEstaraoSinalizandoErro() {
		EnterVehicleDataPage enterVehicleDataPage = new EnterVehicleDataPage(driver);
		enterVehicleDataPage.validarTodosOsCamposComErro();
	}

}
