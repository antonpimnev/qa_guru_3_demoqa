import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;

public class DemoqaTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillFormTest() {
        //consts
        String userName = "Anton";
        String userNumber = "586526411577";
        String userEmail = "anton.pimnev@pochta.com";

        //preconditions
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        //actions
        $("#firstName").setValue(userName);
        $("#lastName").setValue("Pimnev");
        $("#userEmail").setValue(userEmail);
        $(byXpath("//label[contains(text(),'Male')]")).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(byXpath("//option[contains(text(),'2000')]")).click();
        $(byXpath("//div[contains(text(),'10')]")).click();

        //or
        //$(".react-datepicker__month-select").selectOption("August");
        //$(".react-datepicker__year-select").selectOption("1995");
        //$(".react-datepicker__day--009:not(.react-datepicker__day--outside-month)").click();

        $("#subjectsInput").setValue("Ну" + "приехали");
        $(byXpath("//label[contains(text(),'Sports')]")).click();
        $(byXpath("//label[contains(text(),'Music')]")).click();
        $("#uploadPicture").uploadFile(new File("src/resources/1.png"));
        $("#currentAddress").sendKeys("Test"+"123");
        $("#react-select-3-input").sendKeys("ncr");
        $("#react-select-3-input").pressTab();
        $("#react-select-4-input").sendKeys("delh");
        $("#react-select-4-input").pressTab();
        $("#submit").click();

        //asserts
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(byXpath("//td[contains(text(),'anton.pimnev@pochta.com')]")).shouldBe(Condition.visible);
    }
}
