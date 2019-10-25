package ru.aplana.cucumberallure.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.aplana.cucumberallure.Product;

import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(xpath = "//div[@data-test-id='filter-block-price']//input[@data-test-id='range-filter-from-input']")
    WebElement minPrice;

    @FindBy(xpath = "//div[@data-test-id='filter-block-price']//input[@data-test-id='range-filter-to-input']")
    WebElement maxPrice;

    @FindBy(xpath = "//span[contains(text(), 'Цена')]")
    WebElement priceCondition;

    @FindBy(xpath = "//div[@class='sort']/div[2]/div/div")
    List<WebElement> conditions;

    @FindBy(xpath = "//label[@class='checkbox-label']")
    List<WebElement> checkboxes;

    public static final By checkboxName = By.xpath("./span[contains(@class,'label-text')]");
    public static final By checkboxInput = By.xpath("./span[@class='checkmark']");

    @FindBy(xpath = "//div[@class='title'][contains(text(), 'Бренды')]/following-sibling::div//span[@class='show-text']")
    WebElement expandCategory;

//    @FindBy(xpath = "//div[@class='widget-search-result-container content'][./following-sibling::div[@title='Товары не в наличии']]//a[@class='tile-wrapper']")
    @FindBy(xpath = "//a[@class='tile-wrapper']")
    List<WebElement> productTiles;

    public static final By productName = By.xpath(".//span[@data-test-id='tile-name']");
    public static final By productPrice = By.xpath(".//span[@data-test-id='tile-price']");
    public static final By addToCartButton = By.xpath(".//button");

    public void setMaxPrice(String price) {
        String oldValue = maxPrice.getAttribute("value");
        fw.waitAndSendKey(maxPrice, price);
        fw.waitForChange(maxPrice, oldValue);
        fw.waitAndClick(minPrice);
        fw.wait(priceCondition);
    }

    public int getOldValue() {
        return conditions.size();
    }

    public void checkOldValueChanged(int oldValue) {
        fw.waitForCondition(conditions, oldValue);
    }

    public void clickCheckbox(String name) {
        int oldValue = conditions.size();
        fw.wait(checkboxes.get(0));
        for (WebElement element : checkboxes) {
            if (element.findElement(checkboxName).getText().equals(name)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                fw.waitAndClick(element.findElement(checkboxInput));
                fw.waitForCondition(conditions, oldValue);
                return;
            }
        }
        Assert.fail(String.format("Чекбокс %s не найден", name));
    }

    public void clickExpand() {
        fw.waitAndClick(expandCategory);
    }

    public void addProductToCart(int id) {
        WebElement element = productTiles.get(id);
        if (element.findElement(addToCartButton).getText().equals("В корзину")) {
            fw.waitAndClick(element.findElement(addToCartButton));
            cart.add(new Product(element.findElement(productName).getText(), Integer.parseInt(element.findElement(productPrice).getText().replaceAll("\\D", ""))));
        }
    }

    public void addProductsToCart(List<Integer> ids) {
        fw.waitForReload();
        for (int i = 0; i < productTiles.size(); i++) {
            if (ids.contains(i)) {
                addProductToCart(i);
            }
        }
    }
}
