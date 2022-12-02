package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageSamokat {

    private WebDriver driver;

    public static final boolean TOP_ORDER = true; //опция верхней кнопки заказа
    public static final boolean BOTTOM_ORDER = false; //опция нижней кнопки заказа

    //Кнопка "Заказать" в хедере
    private By orderHeaderButton = By.xpath(".//div[@class='Header_Header__214zg']//button[@" +
            "class='Button_Button__ra12g']");
    //Кнопка "Заказать" внизу страницы
    private By orderBottomButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");
    //Локаторы стрелочек вопросов
    private By[]  questionHeader = {
            By.id("accordion__heading-0"),
            By.id("accordion__heading-1"),
            By.id("accordion__heading-2"),
            By.id("accordion__heading-3"),
            By.id("accordion__heading-4"),
            By.id("accordion__heading-5"),
            By.id("accordion__heading-6"),
            By.id("accordion__heading-7")
    };
    //Тексты ответов на вопросы
    private By[] answerText = {
            By.xpath(".//div[@id ='accordion__panel-0']/p"),
            By.xpath(".//div[@id ='accordion__panel-1']/p"),
            By.xpath(".//div[@id ='accordion__panel-2']/p"),
            By.xpath(".//div[@id ='accordion__panel-3']/p"),
            By.xpath(".//div[@id ='accordion__panel-4']/p"),
            By.xpath(".//div[@id ='accordion__panel-5']/p"),
            By.xpath(".//div[@id ='accordion__panel-6']/p"),
            By.xpath(".//div[@id ='accordion__panel-7']/p")
    };

    //Блок вопросов
    private By cookieButton = By.className("App_CookieButton__3cvqF");

    public MainPageSamokat(WebDriver driver){
        this.driver = driver;} // Инициализировали поле driver

    // нажать на кнопку про куки, если видна
    public void clickCookieButton() {
       if (driver.findElement(cookieButton).isEnabled()) {
           driver.findElement(cookieButton).click();
       }
    }

    //скролл главной страницы вниз до последнего вопроса
    public void scrollToQuestions() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(questionHeader[7]));
    }

    //клик по вопросу, нумерация от 0 до 7, сверху вниз, c ожиданием открытия
    public void clickQuestion(int questionNumber) {
        driver.findElement(questionHeader[questionNumber]).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(
                driver.findElement(answerText[questionNumber])));
    }

    //проверка видимости текста ответа на вопрос
    public boolean isVisibleAnswerText(int answerNumber) {
        return driver.findElement(answerText[answerNumber]).isDisplayed();
    }

    //запрос текста ответа
    public String getAnswerText(int answerNumber) {
        return driver.findElement(answerText[answerNumber]).getText();
    }

    //клик на кнопку заказа (параметр: TOP_ORDER - верхняя кнопка, BOTTOM_ORDER - нижняя)
    public void clickOrderButton(boolean orderButtonPlace) {
        //определяем какую кнопку нажимать
        if (orderButtonPlace) {
            driver.findElement(orderHeaderButton).click();
        } else {
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
                    driver.findElement(orderBottomButton));
            driver.findElement(orderBottomButton).click();
        }

    }


}
