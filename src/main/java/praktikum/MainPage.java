package praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class MainPage {
    //кнопка Cookie
    private final By cookie = By.id("rcc-confirm-button");
    //кнопка "Заказать", которая находится в шапке главной страницы
    private final By buttonOrderInHeader = By.xpath(".//div[contains(@class, 'Header_Nav')]/button[contains(@class, 'Button_Button')]");
    //кнопка "Заказать", которая находится в теле главной страницы
    private final By buttonOrderInBody = By.xpath(".//div[contains(@class, 'Home_FinishButton')]/button[contains(@class, 'Button_Middle')]");
    //блок "Вопросы о важном"
    private final By faqBlockQuestions = By.className("accordion");
    //ответ на выбранный вопрос
    private final By answerQuestion = By.xpath(".//*[@class='accordion__panel' and not(@hidden)]/p");

    private final WebDriver driver;
    public MainPage(WebDriver driver) {

        this.driver = driver;
    }

    //Принимаем Cookie
    public MainPage clickCookie() {
        driver.findElement(cookie).click();
        return this;
    }

    //Нажимаем на указанную кнопку "Заказать"
    public MainPage clickButtonOrder(String buttonOrder){
        switch (buttonOrder) {
            case "Header":
                driver.findElement(buttonOrderInHeader).click();
                break;
            case "Body":
                WebElement element = driver.findElement(buttonOrderInBody);
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
                driver.findElement(buttonOrderInBody).click();
                break;
        }
        return new MainPage(driver);
    }

    //Прокручиваем страницу до блока "Вопросы о важном"
    public MainPage scrollFaqBlockQuestions(){
        WebElement element = driver.findElement(faqBlockQuestions);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    //Нажимаем на вопрос
    public MainPage clickQuestion(int numberQuestion) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("accordion__heading-" + numberQuestion)));
        driver.findElement(By.id("accordion__heading-" + numberQuestion)).click();
        return this;
    }

    //Получаем текст ответа на выбранный вопрос
    public String getActualAnswer(){

        return driver.findElement(answerQuestion).getText();
    }
}
