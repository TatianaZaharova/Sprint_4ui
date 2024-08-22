package praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage {
    //Поле "Имя"
    private final By name = By.xpath(".//input[@placeholder='* Имя']");
    //Поле "Фамилия"
    private final By surname = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле "Адрес"
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле "Станция метро"
    private final By metroStation = By.xpath(".//input[@placeholder='* Станция метро']");
    //Поле "Телефон"
    private final By phone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка "Далее"
    private final By buttonNext = By.xpath(".//button[contains(@class, 'Button_Middle')]");
    //Поле "Когда привезти самокат"
    private final By dateDelivery = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Поле "Срок аренды"
    private final By rentalPeriod = By.className("Dropdown-placeholder");
    //Поле "Комментарий для курьера"
    private final By commentCourier = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка "Заказать" в форме "Про аренду"
    private final By buttonOrder = By.xpath(".//button[contains(@class, 'Button_Middle') and text()='Заказать']");
    //Кнопка "Да" в окне оформления заказа
    private final By buttonYes = By.xpath(".//button[contains(@class, 'Button_Middle') and text()='Да']");
    //Окно "Заказ оформлен"
    private final By successfulOrder = By.xpath(".//*[text() = 'Заказ оформлен']");

    private final WebDriver driver;
    public OrderPage(WebDriver driver) {

        this.driver = driver;
    }

    //Заполняем поле "Имя"
    public OrderPage setName(String inputName) {
        driver.findElement(name).sendKeys(inputName);
        return this;
    }

    //Заполняем поле "Фамилия"
    public OrderPage setSurname(String inputSurname) {
        driver.findElement(surname).sendKeys(inputSurname);
        return this;
    }

    //Заполняем поле "Адрес"
    public OrderPage setAddress(String inputAddress) {
        driver.findElement(address).sendKeys(inputAddress);
        return this;
    }

    //Нажимаем на поле "Станция метро" и выбираем станцию
    public OrderPage choiceMetro(int stationNumber) {
        driver.findElement(metroStation).click();
        WebElement element = driver.findElement(By.xpath("//button[@value = '" + stationNumber + "']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(By.xpath("//button[@value = '" + stationNumber + "']")).click();
        return this;
    }

    //Заполняем поле "Телефон"
    public OrderPage setPhone(String inputPhoneNumber) {
        driver.findElement(phone).sendKeys(inputPhoneNumber);
        return this;
    }

    //Нажимаем на кнопку "Далее"
    public OrderPage clickButtonNext(){
        driver.findElement(buttonNext).click();
        return this;
    }

    //Заполняем поле "Когда привезти самокат"
    public OrderPage setDateDelivery(String inputDateDelivery) {
        driver.findElement(dateDelivery).sendKeys(inputDateDelivery);
        driver.findElement(dateDelivery).sendKeys(Keys.ENTER);
        return this;
    }

    //Нажимаем на поле "Срок аренды" и выбираем период
    public OrderPage choiceRentalPeriod(String period) {
        driver.findElement(rentalPeriod).click();
        driver.findElement(By.xpath("//*[text() = '"+ period + "']")).click();
        return this;
    }

    //Выбираем цвет самоката
    public OrderPage choiceColour(String colour) {
        driver.findElement(By.xpath(".//input[contains(@class, 'Checkbox_Input') and (@id='" + colour + "')]")).click();
        return this;
    }

    //Заполняем поле "Комментарий для курьера"
    public OrderPage leaveComment(String inputCommentCourier) {
        driver.findElement(commentCourier).sendKeys(inputCommentCourier);
        return this;
    }

    //Нажимаем кнопку "Заказать" в форме "Про аренду"
    public OrderPage clickButtonOrder() {
        driver.findElement(buttonOrder).click();
        return this;
    }

    //Нажимаем кнопку "Да" в окне оформления заказа
    public OrderPage clickButtonYes() {
        driver.findElement(buttonYes).click();
        return this;
    }

    //Проверяем появление окна "Заказ оформлен"
    public boolean successfulOrderCreated() {

        return driver.findElement(successfulOrder).isDisplayed();
    }
}
