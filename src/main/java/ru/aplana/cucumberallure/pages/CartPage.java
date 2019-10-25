package ru.aplana.cucumberallure.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(xpath = "//a[@class='title']/span")
    List<WebElement> productTitles;

    @FindBy(xpath = "//span[@class='total-middle-header-text']")
    WebElement cartInfo;

    @FindBy(xpath = "//span[contains(text(),'Удалить выбранные')]")
    WebElement deleteButton;

    @FindBy(xpath = "//button[@data-test-id='checkcart-confirm-modal-confirm-button']")
    WebElement deleteConfirmButton;

    @FindBy(xpath = "//h1[contains(text(),'Корзина пуста')]")
    WebElement cartIsEmpty;

    public void checkProductsList() {
        for (WebElement element : productTitles) {
            cart.checkProduct(element.getText());
        }
    }

    public void checkNumberOfProducts(String num) {
        Assert.assertEquals("Неверное количество товара", num, cartInfo.getText().split(" ")[0]);
    }

    public void deleteProducts() {
        fw.waitAndClick(deleteButton);
        fw.waitAndClick(deleteConfirmButton);
        cart.clear();
    }

    public void checkEmpty() {
        try {
            fw.wait(cartIsEmpty);
        } catch (Exception e) {
            Assert.fail("Корзина не пуста!");
        }

    }
}
