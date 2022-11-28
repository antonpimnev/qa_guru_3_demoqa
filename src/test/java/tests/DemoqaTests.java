package tests;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import testdata.TestData;

public class DemoqaTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();

    @Test
    void fillFormTest() {
        registrationPage.openPage()
                .setFirstName(testData.userFirstName)
                .setLastName(testData.userLastName)
                .setEmail(testData.userEmail)
                .setGender(testData.userGender)
                .setUserNumber(testData.userNumber)
                .setBDate(testData.userBDay, testData.userBMonth, testData.userBYear)
                .setSubject(testData.userSubject)
                .setHobbies(testData.userHobbies)
                .uploadPicture(testData.userPicture)
                .setAdress(testData.userAddress)
                .setState(testData.userState)
                .setCity(testData.userCity)
                .clickSubmit()
                .verifyResultsModalAppears()
                .verifyResult("Student Name", testData.userFirstName+" "+testData.userLastName)
                .verifyResult("Student Email", testData.userEmail)
                .verifyResult("Gender", testData.userGender)
                .verifyResult("Mobile", testData.userNumber)
                .verifyResult("Date of Birth", testData.userBDay+ " " +testData.userBMonth +"," +testData.userBYear)
                .verifyResult("Subjects", testData.userSubject)
                .verifyResult("Hobbies", testData.userHobbies)
                .verifyResult("Picture", testData.userPictureResult)
                .verifyResult("Address", testData.userAddress)
                .verifyResult("State and City", testData.userState+" "+testData.userCity)
                .clickClose();
    }
}
