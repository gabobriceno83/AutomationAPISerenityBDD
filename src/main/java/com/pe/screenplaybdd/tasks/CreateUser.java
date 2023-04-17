package com.pe.screenplaybdd.tasks;

import com.pe.screenplaybdd.model.User;
import com.sun.tools.javac.comp.Todo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static com.pe.screenplaybdd.endpoints.UserEndpoints.*;

public class CreateUser implements Task {

    private final User user;

    //Constructor
    public CreateUser(User user) {
        this.user = user;
    }


    /*
    Agregamos un metodo de tipo Performable.
    Este metodo representa a lo que se creo como variable y a lo que se tiene como metodo de tipo actor.
    Cuando se llame desde afuera a asNewuser y se le pase el user va a poder ajecutar la clase Createuser
    */
    public static Performable asNewuser (User user) {
        return instrumented(CreateUser.class, user); //como es una tarea utilizamos este metodo instrumented que nos va a permitir ejecutar la clase CreateUser desde afuera
    }



    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to(Create_User.getPath()).with(request->request.given().log().all()
        .header("Content-Type", "application/json").body(user)));

        //Agregamos una condicion
        if(SerenityRest.lastResponse().statusCode()==200){
            SerenityRest.then().log().all(); //imprimimos el response
            actor.remember("userName", user.getUsername()); //Con remember guardamos username en la key userName para utilizarla luego cuando llame al get y le pase ese valor para validar que si aha registrado correctamente de esa manera en este caso voy a hacer mi question
        }

    }

}

