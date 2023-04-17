Feature: Funcionalidad  Gestion de Usuario

  @create
  Scenario:  Crear un usuario en la petStore App
    Given que el  owner con los siguientes detalles:
      | id | username | firstName | lastName | email              | password | phone     | userStatus |
      | 8  | Gabo     | Gabriel   | Briceno  | gaboxi83@gmail.com | 123456   | 929839763 | 0          |
    When el owner registra el usuario
    Then el usuario se debe visualizar en la lista con su nombre Gabo



  @update
  Scenario:  Actualizar el usuario en la petStore App
    Given que el owner con los siguientes detalles:
      | id | username | firstName | lastName | email             | password | phone     | userStatus |
      | 8  | Angelo   | Angel     | Zambrano | angelox@gmail.com | 223344   | 929839754 | 0          |
    When el owner actualiza el usuario Gabo
    Then el usuario se debe visualizar en la lista con su nombre Angelo



  @delete
  Scenario:  Eliminar el usuario en la petStore App
    Given que el owner con los siguientes detalles:
      | id | username | firstName | lastName | email              | password | phone     | userStatus |
      | 8  | Gabo     | Gabriel   | Briceno  | gaboxi83@gmail.com | 123456   | 929839763 | 0          |
    And  el owner registra el usuario
    When el owner realiza la eliminacion del usuario
    Then el owner obtiene en la busqueda el mensaje User not found





