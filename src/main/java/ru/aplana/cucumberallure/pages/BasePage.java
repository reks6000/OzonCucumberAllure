package ru.aplana.cucumberallure.pages;

import io.qameta.allure.Attachment;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.aplana.cucumberallure.Cart;
import ru.aplana.cucumberallure.Framework;
import ru.aplana.cucumberallure.Init;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BasePage {
    WebDriver driver;
    Framework fw;
    static Cart cart = new Cart();

    @FindBy(xpath = "//button[@aria-label='Закрыть сообщение']")
    WebElement closePopupButton;

    @FindBy(xpath = "//input[@data-test-id='header-search-input']")
    WebElement searchString;

    @FindBy(xpath = "//button[@data-test-id='header-search-go']")
    WebElement searchButton;

    @FindBy(xpath = "//a[@href='/cart']")
    WebElement cartButton;


    public BasePage(){
        this.driver = Init.getDriver();
        this.fw = Init.getFramework();
        PageFactory.initElements(driver, this);
    }

    @Attachment(value = "Products list", type = "text/plain")
    public static String attachLog() {
        return cart.toString();
    }

//    public void writeToFile() {
//        String path = "target\\allure-results\\products.txt";
//        File file = new File(path);
//        try {
//            if(file.createNewFile()) {
//                System.out.println("File created");
//            } else {
//                System.out.println("File already exists");
//            }
//            FileWriter fr = new FileWriter(file, true);
//            fr.write(cart.toString());
//            fr.close();
//        } catch (Exception e) {
//            Assert.fail("Problems with writefile");
//            e.printStackTrace();
//        }
//    }

    public void closePopup() {
        fw.waitAndClick(closePopupButton);
    }

    public SearchPage search (String key) {
        fw.waitAndSendKey(searchString, key);
        fw.waitAndClick(searchButton);
        return new SearchPage();
    }

    public CartPage goToCart() {
        fw.waitAndClick(cartButton);
        return new CartPage();
    }
}
