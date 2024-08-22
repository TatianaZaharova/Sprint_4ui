package praktikum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderScooterTest {
    private final String buttonOrder;
    private final String inputName;
    private final String inputSurname;
    private final String inputAddress;
    private final int stationNumber;
    private final String inputPhoneNumber;
    private final String inputDateDelivery;
    private final String period;
    private final String colour;
    private final String inputCommentCourier;

    public OrderScooterTest(String buttonOrder, String inputName, String inputSurname, String inputAddress, int stationNumber, String inputPhoneNumber, String inputDateDelivery, String period, String colour, String inputCommentCourier) {
        this.buttonOrder = buttonOrder;
        this.inputName = inputName;
        this.inputSurname = inputSurname;
        this.inputAddress = inputAddress;
        this.stationNumber = stationNumber;
        this.inputPhoneNumber = inputPhoneNumber;
        this.inputDateDelivery = inputDateDelivery;
        this.period = period;
        this.colour = colour;
        this.inputCommentCourier = inputCommentCourier;
    }

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Parameterized.Parameters
    public static Object [][] getScooterOrder(){
        return new Object[][] {
                {"Body", "Иваныч", "Иваныч", "Бабаевская, 7", 10, "+79998885522", "28.03.2024", "двое суток", "grey", "Позвоните мне"},
                {"Header", "Марья", "Иванна", "Арбат, 40", 77, "+78882226677", "27.03.2024", "трое суток", "black", "Только наличные"}
        };
    }


    @Test
    public void scooterOrder(){
        WebDriver driver = driverRule.getDriver();
        driver.get(EnvConfig.BASE_URL);

        new MainPage(driver)
                .clickCookie()
                .clickButtonOrder(buttonOrder);
        boolean isOrderScooterCreated = new OrderPage(driver)
                .setName(inputName)
                .setSurname(inputSurname)
                .setAddress(inputAddress)
                .choiceMetro(stationNumber)
                .setPhone(inputPhoneNumber)
                .clickButtonNext()
                .setDateDelivery(inputDateDelivery)
                .choiceRentalPeriod(period)
                .choiceColour(colour)
                .leaveComment(inputCommentCourier)
                .clickButtonOrder()
                .clickButtonYes()
                .successfulOrderCreated();

        assertTrue("Заказ не создан", isOrderScooterCreated);
    }
}
