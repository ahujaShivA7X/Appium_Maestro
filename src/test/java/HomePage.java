import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Bigbasket\"])[1]")
    private MobileElement bigBasketLogo;

    @AndroidFindBy(id = "com.bigbasket.mobileapp:id/button_done")
    private MobileElement doneButton;

    @AndroidFindBy(id = "com.bigbasket.mobileapp:id/home_search_bar_container")
    private MobileElement searchContainerHome;

    @AndroidFindBy(id = "com.bigbasket.mobileapp:id/productPageSearchBox")
    private MobileElement searchContainerResults;

    public HomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, 60);

    public void goToHomePage() {

        wait.until(ExpectedConditions.visibilityOf(bigBasketLogo)).click();
        wait.until(ExpectedConditions.elementToBeClickable(doneButton)).click();
    }

    public void searchForItemHomePage(String itemName) throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(searchContainerHome)).click();
        Thread.sleep(3000);
        Actions action = new Actions(driver);
        action.sendKeys(itemName).perform();
        action.sendKeys(Keys.ENTER).perform();
    }

    public void searchForItemResultsPage(String itemName) throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(searchContainerResults)).click();
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL)
                .sendKeys("a")
                .sendKeys(Keys.BACK_SPACE)
                .build()
                .perform();
        action.sendKeys(itemName).perform();
        action.sendKeys(Keys.ENTER).perform();
    }
}