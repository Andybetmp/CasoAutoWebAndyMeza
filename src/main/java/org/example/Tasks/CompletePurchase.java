package org.example.Tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.example.Pages.CartPage;
import org.example.Pages.HomePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CompletePurchase implements Task {

    private final String customerName;
    private final String creditCard;
    private final boolean submitEmpty;

    public CompletePurchase(String customerName, String creditCard, boolean submitEmpty) {
        this.customerName = customerName;
        this.creditCard = creditCard;
        this.submitEmpty = submitEmpty;
    }

    public static CompletePurchase withData(String customerName, String creditCard) {
        return instrumented(CompletePurchase.class, customerName, creditCard, false);
    }

    public static CompletePurchase withEmptyData() {
        return instrumented(CompletePurchase.class, "", "", true);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(HomePage.CART_MENU_LINK, isClickable()).forNoMoreThan(10).seconds(),
                Click.on(HomePage.CART_MENU_LINK),
                WaitUntil.the(CartPage.PLACE_ORDER_BUTTON, isClickable()).forNoMoreThan(10).seconds(),
                Click.on(CartPage.PLACE_ORDER_BUTTON),
                WaitUntil.the(CartPage.NAME_INPUT, isVisible()).forNoMoreThan(10).seconds()
        );

        if (!submitEmpty) {
            actor.attemptsTo(
                    Enter.theValue(customerName).into(CartPage.NAME_INPUT),
                    Enter.theValue(creditCard).into(CartPage.CARD_INPUT)
            );
        }

        actor.attemptsTo(
                Click.on(CartPage.PURCHASE_BUTTON)
        );

        if (submitEmpty) {
            WebDriverWait wait = new WebDriverWait(BrowseTheWeb.as(actor).getDriver(), Duration.ofSeconds(10));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            actor.remember("alertaCompra", alert.getText());
            alert.accept();
        }
    }
}