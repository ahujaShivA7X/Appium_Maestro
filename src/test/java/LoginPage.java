import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    @AndroidFindBy(id = "com.bigbasket.mobileapp:id/btn_select_location")
    private MobileElement selectLocationButton;

    @AndroidFindBy(id = "com.bigbasket.mobileapp:id/txtLocationSearch")
    private MobileElement locationSearchInput;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.bigbasket.mobileapp:id/text1\" and @text=\"AVJ Heights Market\"]")
    private MobileElement avjHeightsMarket;

    @AndroidFindBy(id = "com.bigbasket.mobileapp:id/btnUseThisLocation")
    private MobileElement useThisLocationButton;

    public LoginPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public void setLocation(String locationName) {
        WebDriverWait wait = new WebDriverWait(driver, 60);

        wait.until(ExpectedConditions.visibilityOf(selectLocationButton)).click();
        wait.until(ExpectedConditions.visibilityOf(locationSearchInput)).sendKeys(locationName);
        wait.until(ExpectedConditions.elementToBeClickable(avjHeightsMarket)).click();
        wait.until(ExpectedConditions.elementToBeClickable(useThisLocationButton)).click();
    }
}