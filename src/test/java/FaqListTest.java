import POM.MainPageSamokat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class FaqListTest extends BaseWebTest{

    //эталонные тексты ответов
    private String[] standardAnswerText = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, " +
                    "можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. " +
                    "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
                    "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете " +
                    "кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };

    @Test
    public void checkFaqListText() {

        //запускаем драйвер браузера и открываем страницу
        initWebDriver("Chrome", "https://qa-scooter.praktikum-services.ru/");

        //создаем объект класса главной страницы
        MainPageSamokat objMainPage = new MainPageSamokat(driver);

        //кликаем на кнопку про куки
        objMainPage.clickCookieButton();

        //прокручиваем страницу до списка вопросов
        objMainPage.scrollToQuestions();

        //кликаем по вопросам и проверяем соответствие ответов
        for (int i = 0; i < 8; i++) {
            objMainPage.clickQuestion(i);
            Assert.assertEquals("Текст ответа № "+ i + " не соответствует образцу", standardAnswerText[i],
                    objMainPage.getAnswerText(i));
        }

    }

   @After
   public void tearDown() {
   //закрываем драйвер
       driver.quit();
   }

}