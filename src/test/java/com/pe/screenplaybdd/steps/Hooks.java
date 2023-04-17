package com.pe.screenplaybdd.steps;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;

public class Hooks {

    // Para poder hacer uso de ciertos componentes de screenplay para que en El StepDefinitions llamemos al actor primero tenemos que usar esta clase
    @Before
    public void setUp () {
        OnStage.setTheStage(new Cast()); // El Onstage es una clase de la libreria de screenplay de tipo actor, esto me va a permitir llamar a ciertos metodos como theActorCalled y theActorInTheSpotlight que me van a permitir ejecutar ciertas habilidades en nuestro stepDefinitions
    }
}
