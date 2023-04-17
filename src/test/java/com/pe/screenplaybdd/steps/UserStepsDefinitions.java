package com.pe.screenplaybdd.steps;

import com.pe.screenplaybdd.model.User;
import com.pe.screenplaybdd.questions.theUser;
import com.pe.screenplaybdd.tasks.CreateUser;
import com.pe.screenplaybdd.tasks.DeleteUser;
import com.pe.screenplaybdd.tasks.UpdateUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;


public class UserStepsDefinitions {

 EnvironmentVariables environmentVariables; // como tenemos un archivo serenity.conf tenemos que agregar una variable Enviroment
 private User user; // Tambien creo una variable de tipo User


   @Given("^que el (.*) con los siguientes detalles:$")
   public void que_owner_con_los_siguientes_detalles(String actor, List<Map<String, String>> listOfdata) {
     theActorCalled(actor).whoCan(CallAnApi.at(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("url"))); // llamo a la url base que esta en el serenity.conf
     Map<String, String> userData = listOfdata.get(0); // creamos la varible userData y le pasamos lo que esta en el indice (0) de ListOfData
    //Nosotros tenemos que construir el request body para eso a travez de la variable user instanciamos al constructor
    // de la clase User y pasamos los parametros que vienen del gherkin y que se guardaron en paso anterior en la variable tipo lista userData
     this.user = new User(Integer.parseInt(userData.get("id")), userData.get("username"), userData.get("firstName"), userData.get("lastName"),
          userData.get("email"), userData.get("password"), userData.get("phone"), Integer.parseInt(userData.get("userStatus")));
  }


   @When("^el owner registra el usuario$")
   public void elOwnerRegistraElUsuario() {
     theActorInTheSpotlight().attemptsTo(CreateUser.asNewuser(this.user));// Ya no usamos theActorCalled ahora usamos theActorInTheSpotlight ya que es la continuacion de lo que contiene theActorCalled  y continua hasta el final
   }


   @Then("^el usuario se debe visualizar en la lista con su nombre (.*)$")
   public void elUsuarioDebeVisualizarEnLaListaConSuNombreGabo(String users){
      String userRegistered = theActorInTheSpotlight().recall("userName"); //Se crea la variable userRegistered y se le pasa el userName que se habia gurdado en la clase CreateUser con el metodo remember
      theActorInTheSpotlight().should(seeThat(theUser.theValueName(userRegistered), is(equalTo(users))));

   }


    @When("^el owner actualiza el usuario (.*)$")
    public void elOwnerActualizaElUsuarioGabo(String userUpdate) {
       theActorInTheSpotlight().attemptsTo(UpdateUser.as(this.user,userUpdate));

    }



    @When("^el owner realiza la eliminacion del usuario$")
    public void elOwnerRealizaLaEliminacionDelUsuario() {
       theActorInTheSpotlight().attemptsTo(DeleteUser.how(this.user.getUsername()));

    }

    @Then("^el owner obtiene en la busqueda el mensaje (.*)$")
    public void elOwnerObtieneEnLaBusquedaElMensajeUserNotFound(String message) {
       String userRegistered = theActorInTheSpotlight().recall("userName");
       theActorInTheSpotlight().should(seeThat(theUser.message(userRegistered), is(equalTo(message))));

    }
}
