package org.example.StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.example.Pages.CartPage;
import org.example.Pages.HomePage;
import org.example.Questions.ElementText;
import org.example.Questions.ElementVisible;
import org.example.Tasks.AddProductToCart;
import org.example.Tasks.CompletePurchase;
import org.example.Tasks.FilterCategory;
import org.example.Tasks.NavigateHome;
import org.example.Utils.PreAndProsCondition;
import org.example.Utils.ScreenShot;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GestionarCatalogo {

    @Given("el {actor} abre la pagina principal de la tienda")
    public void elActorAbreLaPaginaPrincipalDeLaTienda(Actor actor) {
        actor.attemptsTo(
                NavigateHome.page()
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.screenShot, "Paso1");
    }

    @When("el {actor} filtra el catalogo por la categoria {string}")
    public void elActorFiltraElCatalogoPorLaCategoria(Actor actor, String categoria) {
        actor.attemptsTo(
                FilterCategory.named(categoria)
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.screenShot, "Paso2");
    }

    @Then("deberia visualizar el producto {string} en el catalogo")
    public void deberiaVisualizarElProductoEnElCatalogo(String productoEsperado) {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(HomePage.PRODUCT_BY_NAME.of(productoEsperado), isVisible()).forNoMoreThan(10).seconds()
        );
        theActorInTheSpotlight().should(
                seeThat("el producto filtrado en el catalogo",
                        ElementVisible.onScreen(HomePage.PRODUCT_BY_NAME.of(productoEsperado)),
                        is(true))
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.screenShot, "Paso3");
    }

    @When("el {actor} selecciona el producto {string} y lo agrega al carrito")
    public void elActorSeleccionaElProductoYLoAgregaAlCarrito(Actor actor, String producto) {
        actor.attemptsTo(
                AddProductToCart.productNamed(producto)
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.screenShot, "Paso2");
    }

    @And("el {actor} abre el carrito de compras")
    public void elActorAbreElCarritoDeCompras(Actor actor) {
        actor.attemptsTo(
                WaitUntil.the(HomePage.CART_MENU_LINK, isClickable()).forNoMoreThan(10).seconds(),
                Click.on(HomePage.CART_MENU_LINK),
                WaitUntil.the(CartPage.CART_PRODUCT_NAME, isVisible()).forNoMoreThan(10).seconds()
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.screenShot, "Paso3");
    }

    @Then("deberia ver el producto {string} listado en el carrito")
    public void deberiaVerElProductoListadoEnElCarrito(String producto) {
        theActorInTheSpotlight().should(
                seeThat("el producto listado en el carrito",
                        ElementText.of(CartPage.CART_PRODUCT_NAME),
                        containsStringIgnoringCase(producto))
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.screenShot, "Paso4");
    }

    @And("el {actor} abre el carrito y confirma la compra con nombre {string} y tarjeta {string}")
    public void elActorAbreElCarritoYConfirmaLaCompraConNombreYTarjeta(Actor actor, String nombre, String tarjeta) {
        actor.attemptsTo(
                CompletePurchase.withData(nombre, tarjeta)
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.screenShot, "Paso3");
    }

    @Then("deberia visualizar el mensaje de confirmacion {string}")
    public void deberiaVisualizarElMensajeDeConfirmacion(String mensajeEsperado) {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(CartPage.PURCHASE_CONFIRMATION_TITLE, isVisible()).forNoMoreThan(10).seconds()
        );
        theActorInTheSpotlight().should(
                seeThat("el mensaje de confirmacion de compra",
                        ElementText.of(CartPage.PURCHASE_CONFIRMATION_TITLE),
                        equalTo(mensajeEsperado))
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.screenShot, "Paso4");
    }

    @And("el {actor} abre el carrito e intenta confirmar la compra con datos vacios")
    public void elActorAbreElCarritoEIntentaConfirmarLaCompraConDatosVacios(Actor actor) {
        actor.attemptsTo(
                CompletePurchase.withEmptyData()
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.screenShot, "Paso3");
    }

    @Then("deberia visualizar la alerta de validacion {string}")
    public void deberiaVisualizarLaAlertaDeValidacion(String alertaEsperada) {
        String alertaRecibida = theActorInTheSpotlight().recall("alertaCompra");
        theActorInTheSpotlight().should(
                seeThat("el texto de la alerta de validacion",
                        actor -> alertaRecibida,
                        equalTo(alertaEsperada))
        );
        ScreenShot.captureScreenshot(getDriver(), PreAndProsCondition.screenShot, "Paso4");
    }
}