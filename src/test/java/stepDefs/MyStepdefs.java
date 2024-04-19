package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MyStepdefs {

    private WebDriver driver;

    private Random random = new Random();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    private String email = generateRandomString(15) + "@gmail.com";

    private String generateRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    @Given("I am on the registration page using {string}")
    public void userIsOnRegistrationPage(String browser) throws InterruptedException {
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equals("edge")) {
            driver = new EdgeDriver();
        }
        Thread.sleep(2000);
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        Thread.sleep(1000);
    }

    @Given("I have enterd a valid date of birth {string}")
    public void userEntersValidDateOfBirth(String dateofbirth) throws InterruptedException {
        WebElement DateofBirth = driver.findElement(By.name("DateOfBirth"));
        DateofBirth.sendKeys(dateofbirth);
        Thread.sleep(1000);
    }

    @Given("I have enterd a name {string} and a last name {string}")
    public void userEntersValidName(String firstName, String lastName) {
        WebElement FirstName = driver.findElement(By.id("member_firstname"));
        FirstName.sendKeys(firstName);

        WebElement LastName = driver.findElement(By.id("member_lastname"));
        LastName.sendKeys(lastName);
    }

    @Given("I have enterd a valid email address")
    public void userEntersValidEmailAddress() {
        WebElement EmailAddress = driver.findElement(By.id("member_emailaddress"));
        EmailAddress.sendKeys(email);


    }

    @Given("I have confirmed my email address")
    public void userConfirmsEmailAddress() {
        WebElement ConfirmEmail = driver.findElement(By.id("member_confirmemailaddress"));
        ConfirmEmail.sendKeys(email);
    }

    @Given("I choose a new password {string}")
    public void userChoosesNewPassword(String pass) {
        WebElement Password = driver.findElement(By.id("signupunlicenced_password"));
        Password.sendKeys(pass);
    }

    @Given("I confirm my password {string}")
    public void userConfirmsPassword(String pass) {
        WebElement ConformPass = driver.findElement(By.id("signupunlicenced_confirmpassword"));
        ConformPass.sendKeys(pass);

        WebElement cliclkskip = driver.findElement(By.xpath("//*[@id=\"signup_form\"]/div[9]"));
        cliclkskip.click();
    }

    @Given("I agree on {string}")
    public void userAgreesOnTermsAndConditions(String TermsandConditions) {
        WebElement Button1 = driver.findElement(By.cssSelector("label[for='sign_up_25'] .box"));
        if (TermsandConditions.equals("check")) {
            if (!Button1.isSelected()) {
                Button1.click();
            }
        } else if (TermsandConditions.equals("uncheck")) {
            if (Button1.isSelected()) {
                Button1.click();

            }

        }
    }

    @Given("I agree on CODE OF ETHICS AND CONDUCT and confirm Im over 18")
    public void userAgreesOnCodeOfEthicsAndConductandconfirmImover18() {
        WebElement Button3 = driver.findElement(By.cssSelector("label[for='sign_up_26'] .box"));
        if (!Button3.isSelected()) {
            Button3.click();
        }

        WebElement Button4 = driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] .box"));
        Button4.click();

    }

    @When("I click on Confirm and Join")
    public void userClicksOnConfirmAndJoin() {
        WebElement ConfirmAndJoin = driver.findElement(By.name("join"));
        ConfirmAndJoin.click();
    }

    @Then("I should receive confirmation or error message based on the {string}")
    public void iShouldReceiveConfirmationOrErrorMessageBasedOnThe(String expectedd) throws InterruptedException {
        if (expectedd.equals("THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND")) {
            WebElement h2Element = driver.findElement(By.cssSelector("h2.bold.gray.text-center.margin-bottom-40"));
            String actual = h2Element.getText();
            assertEquals(expectedd, actual);

        } else if (expectedd.equals("Last Name is required")) {
            WebElement h2Element = driver.findElement(By.xpath("//span[contains(text(), 'Last Name is required')]"));
            String actual = h2Element.getText();
            assertEquals(expectedd, actual);

        } else if (expectedd.equals("Password did not match")) {
            WebElement h2Element = driver.findElement(By.xpath("//span[contains(text(), 'Password did not match')]"));
            String actual = h2Element.getText();
            assertEquals(expectedd, actual);

        } else if (expectedd.equals("You must confirm that you have read and accepted our Terms and Conditions")) {
            WebElement h2Element = driver.findElement(By.xpath("//span[contains(text(), 'You must confirm that you have read and accepted our Terms and Conditions')]"));
            String actual = h2Element.getText();
            assertEquals(expectedd, actual);

        }
    }
}


