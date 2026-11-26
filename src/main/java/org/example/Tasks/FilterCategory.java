package org.example.Tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.example.Pages.HomePage;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class FilterCategory implements Task {

    private final String category;

    public FilterCategory(String category) {
        this.category = category;
    }

    public static FilterCategory named(String category) {
        return instrumented(FilterCategory.class, category);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(HomePage.CATEGORY_LINK.of(category), isClickable()).forNoMoreThan(10).seconds(),
                Click.on(HomePage.CATEGORY_LINK.of(category))
        );
    }
}