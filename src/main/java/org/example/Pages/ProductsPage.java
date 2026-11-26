package org.example.Pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProductsPage extends PageObject {

    public static final Target PRODUCT_TITLE = Target.the("titulo del producto en detalle")
            .located(By.cssSelector("h2.name"));

    public static final Target ADD_TO_CART_BUTTON = Target.the("boton Add to cart")
            .located(By.xpath("//a[text()='Add to cart']"));
}