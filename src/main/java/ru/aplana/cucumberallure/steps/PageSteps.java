package ru.aplana.cucumberallure.steps;

import cucumber.api.java.ru.Когда;
import ru.aplana.cucumberallure.pages.CartPage;
import ru.aplana.cucumberallure.pages.MainPage;
import ru.aplana.cucumberallure.pages.SearchPage;

import java.util.List;

//    Сценарий 1
//            1 Перейдите на сервис http://www.ozon.ru/
//            2 Выполните поиск по «iphone»
//            3 Ограничить цену до 60 000
//            4 Отметить чекбокс – Высокий рейтинг
//            5 Отметить чекбокс – 3Гб
//            6 Из результатов поиска добавьте в корзину первые 8 нечетных  товаров.
//            7 Запомнить название товаров
//            8 Перейдите в корзину, убедитесь, что все добавленные ранее товары находятся в корзине
//            9 Проверить, что отображается текст «Ваша корзина  - 8 товаров»
//            10 Удалите все товары из корзины
//            11 Проверьте, что корзина не содержит никаких товаров
//            12 В аллюр отчет добавить шаг, в котором будет приложен файл с информацией о всех добавленных товарах (наименование и цена). Также указать товар с наибольшей ценой.


public class PageSteps {
    MainPage main = new MainPage();
    SearchPage searchPage = new SearchPage();
    CartPage cartPage = new CartPage();

    @Когда("пользователь закрывает всплывающее сообщение")
    public void closePopup() {
        searchPage.closePopup();
    }

    @Когда("пользователь ищет \"(.*)\"")
    public void search(String key) {
        searchPage = main.search(key);
    }

    @Когда("пользователь ограничивает максимальную цену до \"(.*)\"")
    public void setMaxPrice(String price) {
        searchPage.setMaxPrice(price);
    }

    @Когда("пользователь нажимает на чекбокс \"(.*)\"")
    public void clickCheckbox(String name) {
//        int oldValue = searchPage.getOldValue();
        searchPage.clickCheckbox(name);
//        searchPage.checkOldValueChanged(oldValue);
    }

    @Когда("пользователь раскрывает категорию \"Бренды\"")
    public void expandCategory() {
        searchPage.clickExpand();
    }

    @Когда("пользователь кладёт в корзину продукты")
    public void doWithParam(List<Integer> ids) {
        searchPage.addProductsToCart(ids);
    }

    @Когда("пользователь переходит в корзину")
    public void goToCart() {
        cartPage = searchPage.goToCart();
    }

    @Когда("проверка списка товаров")
    public void checkProductsList() {
        cartPage.checkProductsList();
    }

    @Когда("проверка количества товара \"(.*)\"")
    public void checkNumberOfProducts(String num) {
        cartPage.checkNumberOfProducts(num);
    }

    @Когда("пользователь удаляет товар из корзины")
    public void deleteProducts() {
        cartPage.attachLog();
        cartPage.deleteProducts();
    }

    @Когда("проверка пустой корзины")
    public void checkEmpty() {
        cartPage.checkEmpty();
    }
}