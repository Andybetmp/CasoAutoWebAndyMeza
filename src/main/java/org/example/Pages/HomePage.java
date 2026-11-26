package org.example.Pages;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

@DefaultUrl("https://www.demoblaze.com/")
public class HomePage extends PageObject {

    public static final Target CATEGORY_LINK = Target.the("categoria {0} del menu lateral")
            .locatedBy("//a[@id='itemc' and text()='{0}']");

    public static final Target PRODUCT_BY_NAME = Target.the("tarjeta del producto {0}")
            .locatedBy("//div[@id='tbodyid']//a[contains(text(),'{0}')]");

    public static final Target CART_MENU_LINK = Target.the("enlace Cart de la barra superior")
            .located(By.id("cartur"));
}