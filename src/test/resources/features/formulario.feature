
  Feature: Registrar formulario
    Yo un nuevo cliente
    Quiero registrar mis datos
    Para que tengan mi informacion actualizada

  Scenario: Registro de formulario con Datos Validos
    Given que la web DEMOQA esta disponible
    When registro los datos requeridos
    And doy Click en el boton
    Then se muestran mis datos en la pantalla

  Scenario: Visualizar datos requeridos
    Given que la web DEMOQA esta disponible
    When no se ingresan datos
    And doy Click en el boton
    Then no se muestra la tabla con los datos
    Then se muestran los campos requeridos