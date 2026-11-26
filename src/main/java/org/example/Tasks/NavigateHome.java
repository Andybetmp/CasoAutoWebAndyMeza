package org.example.Tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import org.example.Pages.HomePage;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class NavigateHome implements Task {

    public static NavigateHome page() {
        return instrumented(NavigateHome.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn().the(HomePage.class)
        );
    }
}