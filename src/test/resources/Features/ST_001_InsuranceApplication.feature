#language: pt
#author: Victor Hugo Alves Eloi

# ST = suite de teste
@ST_001_InsuranceApplication @End2End
Funcionalidade: Aplicacao para seguro

  Contexto: 
    Dado que o usuario esteja na pagina principal do sistema

  # CT = cenario de teste
  @ST_001_CT_001_PreencherEEnviarTodosOsFormulariosTodosOsCampos
  Cenario: Preencher todos os campos e enviar todos os formularios da aplicacao de seguro
    Quando eu preencher o formulario presente na aba Enter Vehicle Data e pressionar next
    E eu preencher o formulario presente na aba Enter Insurant Data e pressionar next
    E eu preencher o formulario presente na aba Enter Product Data e pressionar next
    E eu selecionar uma das opcoes presentes na aba Select Price Option e pressionar next
    E eu preencher o formulario presente na aba Send Quote e pressionar Send
    Entao o sistema retorna que o e-mail foi enviado com sucesso

  @ST_001_CT_002_PreencherEEnviarTodosOsFormulariosApenasCamposObrigatorios
  Cenario: Preencher apenas campos obrigatorios e enviar todos os formularios da aplicacao de seguro
    Quando eu preencher o formulario presente na aba Enter Vehicle Data com apenas os obrigatorios e pressionar next
    E eu preencher o formulario presente na aba Enter Insurant Data com apenas os obrigatorios e pressionar next
    E eu preencher o formulario presente na aba Enter Product Data e pressionar next
    E eu selecionar uma das opcoes presentes na aba Select Price Option e pressionar next
    E eu preencher o formulario presente na aba Send Quote e pressionar Send
    Entao o sistema retorna que o e-mail foi enviado com sucesso

  @ST_001_CT_003_DeixarDePreencherUmOuMaisCamposObrigatorios
  Cenario: Deixar de preencher um ou mais campos obrigatorios
    Quando eu preencher o formulario presente na aba Enter Vehicle Data sem um ou mais campos obrigatorios e pressionar next
    E eu preencher o formulario presente na aba Enter Insurant Data sem um ou mais os obrigatorios e pressionar next
    E eu preencher o formulario presente na aba Enter Product Data sem um ou mais e pressionar next
    Entao na aba Select Price Option uma mensagem ira solicitar que eu complete os tres primeiros passos para continuar

  @ST_001_CT_004_PreencherComErroFormularioEnterVehicleData @TestarErro
  Cenario: Preencher todos os campos com erro no formulario da aba Enter Vehicle Data
    Quando eu preencher o formulario presente na aba Enter Vehicle Data apenas com dados invalidos
    Entao todos os campos estarao sinalizando erro
