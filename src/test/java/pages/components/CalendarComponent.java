package pages.components;

import static com.codeborne.selenide.Selenide.$x;

public class CalendarComponent {
    public void setDate(String day, String month, String year) {
        $x("//select[@class='react-datepicker__month-select']").selectOption(month);
        $x("//select[@class='react-datepicker__year-select']").selectOption(year);
        $x("//div[contains(@aria-label,'"+month+" "+day+"')]").click();
        //$(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)").click();
    }
}