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
        String userFirstName = "Anton";
        String userLastName = "Pimnev";
        String userNumber = "5865264115";
        String userEmail = "anton.pimnev@pochta.com";
        String address = "Test 123";

        //preconditions
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        //actions
        $("#firstName").setValue(userFirstName);
        $("#lastName").setValue(userLastName);
        $("#userEmail").setValue(userEmail);
        $(byXpath("//label[contains(text(),'Male')]")).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(byXpath("//option[contains(text(),'2000')]")).click();
        $("[class=react-datepicker__month-select]").click();
        $("[class=react-datepicker__month-select]").selectOption("September");
        $(byXpath("//div[contains(text(),'10')]")).click();

        //or
        //$(".react-datepicker__month-select").selectOption("August");
        //$(".react-datepicker__year-select").selectOption("1995");
        //$(".react-datepicker__day--009:not(.react-datepicker__day--outside-month)").click();

        $("#subjectsInput").sendKeys("Acc");
        $("#subjectsInput").pressTab();
        $(byXpath("//label[contains(text(),'Sports')]")).click();
        $(byXpath("//label[contains(text(),'Music')]")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/1.png"));
        $("#currentAddress").setValue(address);
        $("#react-select-3-input").sendKeys("ncr");
        $("#react-select-3-input").pressTab();
        $("#react-select-4-input").sendKeys("delh");
        $("#react-select-4-input").pressTab();
        $("#submit").click();

        //asserts
        $("[class=modal-content]").shouldHave(text("Thanks for submitting the form"));
        $("[class=modal-content]").shouldBe(Condition.visible);
        $(".table-responsive").shouldHave(text(userFirstName+" "+userLastName));
        $("[class=modal-content]").shouldHave(text(userEmail));
        $("[class=modal-content]").shouldHave(text("Male"));
        $("[class=modal-content]").shouldHave(text(userNumber));
        $("[class=modal-content]").shouldHave(text("10 September,2000"));
        $("[class=modal-content]").shouldHave(text("Accounting"));
        $("[class=modal-content]").shouldHave(text("Sports, Music"));
        $("[class=modal-content]").shouldHave(text("1.png"));
        $("[class=modal-content]").shouldHave(text(address));
        $("[class=modal-content]").shouldHave(text("NCR Delhi"));
    }
}
