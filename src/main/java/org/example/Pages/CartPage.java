package org.example.Pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CartPage extends PageObject {

    public static final Target CART_PRODUCT_NAME = Target.the("nombre del producto en el carrito")
            .located(By.xpath("//tbody[@id='tbodyid']/tr/td[2]"));

    public static final Target PLACE_ORDER_BUTTON = Target.the("boton Place Order")
            .located(By.xpath("//button[text()='Place Order']"));

    public static final Target NAME_INPUT = Target.the("campo Name del formulario de compra")
            .located(By.id("name"));

    public static final Target CARD_INPUT = Target.the("campo Credit card del formulario de compra")
            .located(By.id("card"));

    public static final Target PURCHASE_BUTTON = Target.the("boton Purchase")
            .located(By.xpath("//button[text()='Purchase']"));

    public static final Target PURCHASE_CONFIRMATION_TITLE = Target.the("titulo de confirmacion de compra")
            .located(By.xpath("//div[contains(@class,'sweet-alert')]//h2"));
}