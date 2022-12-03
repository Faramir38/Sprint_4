import POM.MainPageSamokat;
import POM.OrderPageSamokat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class OrderTest extends BaseWebTest {

    private final boolean buttonPlace; //верхняя (MainPageSamokat.TOP_ORDER) или
                                        //нижняя кнопка заказа (MainPageSamokat.BOTTOM_ORDER)
    private final String name;         //имя
    private final String surname;      //фамилия
    private final String address;      //адрес
    private final String metro;        //метро
    private final String phone;        //телефон
    private final String date;         //дата
    private final int period;          //срок заказа (1-7 суток)
    private final String color;        //цвет самоката ("black", "grey", "both", "none")
    private final String comment;      //текст комментария


    public OrderTest (boolean buttonPlace, String name, String surname, String address, String metro,
                      String phone, String date, int period, String color, String comment) {
        this.buttonPlace = buttonPlace;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][] {
                {MainPageSamokat.TOP_ORDER, "Один", "Первый", "Один Первый, 1", "Лубянка", "+79119119191",
                    "12.12.2022", 2, "both", "Первый комментарий"},
                {MainPageSamokat.BOTTOM_ORDER, "Два", "Второй", "Два Второй, 2", "Сокол", "+79229229292",
                    "07.12.2022", 1, "grey", "Второй комментарий"}
        };
    }
    @Test
    public void CheckOrder() {

        //запускаем драйвер браузера и открываем страницу
        initWebDriver("Firefox", "https://qa-scooter.praktikum-services.ru/");

        //создаем объект главной страницы
        MainPageSamokat objMainPage = new MainPageSamokat(driver);

        //создаем объект страницы заказа
        OrderPageSamokat objOrderPage = new OrderPageSamokat(driver);

        //кликаем на кнопку про куки
        objMainPage.clickCookieButton();

        //нажимаем кнопку заказа
        objMainPage.clickOrderButton(MainPageSamokat.TOP_ORDER);

        //заполняем первую страницу заказа
        objOrderPage.fillFirstPage(name, surname, address, metro, phone);

        //заполняем вторую страницу заказа
        objOrderPage.fillSecondPage(date, period, color, comment);

        //подтверждаем заказ
        objOrderPage.confirmOrder();

        //проверяем, что открылось окно "Заказ оформлен"
        Assert.assertTrue("Окно подтверждения оформления заказа не открылось",
                objOrderPage.isVisibleStatusButton());

    }

    @After
    public void tearDown() {
        //закрываем драйвер
        driver.quit();
    }



}
