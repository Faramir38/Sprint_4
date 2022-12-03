package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPageSamokat {

    private WebDriver driver;

    public static final int NONE = 0; //цвет самоката не выбран
    public static final int BLACK = 1; //цвет самоката черный
    public static final int GREY = 2; //цвет самоката серый
    public static final int BOTH = 3; //цвет самоката - выбраны оба

    //Поле "Имя"
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    //Поле "Фамилия"
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле "Адрес"
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле "Станция метро"
    private By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    //Поле "Телефон"
    private By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка "Далее"
    private By nextButton = By.xpath(".//button[text()='Далее']");
    //Поле "Дата"
    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Поле "Срок аренды"
    private By periodField = By.cssSelector(".Dropdown-placeholder");
    //список "Срок аренды"
    private By[] periodList = {
            By.xpath(".//div[@class ='Dropdown-menu']/div[text()='сутки']"),
            By.xpath(".//div[@class ='Dropdown-menu']/div[text()='двое суток']"),
            By.xpath(".//div[@class ='Dropdown-menu']/div[text()='трое суток']"),
            By.xpath(".//div[@class ='Dropdown-menu']/div[text()='четверо суток']"),
            By.xpath(".//div[@class ='Dropdown-menu']/div[text()='пятеро суток']"),
            By.xpath(".//div[@class ='Dropdown-menu']/div[text()='шестеро суток']"),
            By.xpath(".//div[@class ='Dropdown-menu']/div[text()='семеро суток']"),
    };

    //Чекбокс "Цвет"->"черный жемчуг"
    private By colorBlack = By.id("black");
    //Чекбокс "Цвет"->"серая безысходность"
    private By colorGrey = By.id("grey");
    //Поле "Комментарий"
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка "Заказать"
    private By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    //Кнопка "Оформить заказ" -> "Да"
    private By yesButton = By.xpath(".//button[text()='Да']");
    //Кнопка "Посмотреть статус"
    private By statusButton = By.xpath(".//button[text()='Посмотреть статус']");

    public OrderPageSamokat(WebDriver driver){
        this.driver = driver;// Инициализировали поле driver
    }

    //заполняем поле Имя на первой странице
    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    //заполняем поле Фамилия на первой странице
    public void enterSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    //заполняем поле адреса на первой странице
    public void enterAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    //заполняем поле станция метро на первой странице
    public void enterMetro(String metro) {
        driver.findElement(metroField).sendKeys(metro);
        String metroOptionTemplate = ".//div[@class='select-search__select']//*[text()='%s']";
        String selectorMetro = String.format(metroOptionTemplate, metro);
        driver.findElement(By.xpath(selectorMetro)).click();
    }

    //заполняем поле телефон на первой странице
    public void enterPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    //клик на кнопке Заказать на первой странице
    public void clickNext() {
        driver.findElement(nextButton).click();
    }

    //заполняем поле даты на второй странице
    public void enterDate(String date) {
        driver.findElement(dateField).sendKeys(date);
    }

    //выбираем срок аренды на второй странице
    public void selectPeriod(int period) {
        driver.findElement(periodField).click();
        driver.findElement(periodList[period-1]).click();
    }

    //выбираем цвет самоката на второй странице (без цвета - NONE, черный - BLACK, серый - GREY, оба - BOTH)
    public void selectColor(String color) {
        switch (color) {
            case "black":
                driver.findElement(colorBlack).click();
                break;
            case "grey":
                driver.findElement(colorGrey).click();
                break;
            case "both":
                driver.findElement(colorBlack).click();
                driver.findElement(colorGrey).click();
                break;
        }
    }

    //вводим текст комментария на второй странице
    public void enterComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    //кликаем на кнопке заказать на второй странице
    public void clickOrder() {
        driver.findElement(orderButton).click();
    }

    //кликаем на кнопку Да в форме подтверждения заказа
    public void clickYes() {
        driver.findElement(yesButton).click();
    }

    //проверка видимости кнопки "Статус заказа" в окне "Заказ оформлен"
    public boolean isVisibleStatusButton() {
        return driver.findElement(statusButton).isDisplayed();
    }

    //ввод данных на первой странице
    public void fillFirstPage(String name, String surname, String address, String metro,
                              String phone){
        UtilSamokat.waitForVisibility(driver, nameField); //ждем открытия первой страницы заказа
        enterName(name);
        enterSurname(surname);
        enterAddress(address);
        enterMetro(metro);
        enterPhone(phone);
        clickNext();
    }

    //ввод данных на второй странице
    public void fillSecondPage(String date, int period, String color, String comment ) {
        UtilSamokat.waitForVisibility(driver, periodField); //ждем открытия второй страницы заказа
        selectPeriod(period);
        selectColor(color);
        enterComment(comment);
        enterDate(date);
        clickOrder();
    }

    //подтверждаем заказ и проверяем что открылось окно "Заказ оформлен"
    public void confirmOrder() {
        UtilSamokat.waitForVisibility(driver, yesButton); //ждем открытия окна подтверждения заказа

        clickYes();
        UtilSamokat.waitForVisibility(driver, statusButton); //ждем открытия окна "Заказ оформлен"

    }

}
