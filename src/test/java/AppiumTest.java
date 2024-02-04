import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumTest {


    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "11.0");
        capabilities.setCapability("deviceName", "Pixel 7 Pro API 30");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("autoGrantPermissions", "true");
        capabilities.setCapability("unicodeKeyboard", false);
        capabilities.setCapability("resetKeyboard", false);
        capabilities.setCapability("app", "/Users/aakashjindal/Documents/untitled folder/appiumFw1/bigBAppium/bigbasket-7-11-6.apk");
        //caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "your.app.package");
        //caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "your.app.MainActivity");

        AndroidDriver<MobileElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        // Instantiate page objects
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);


        // Perform login
        loginPage.setLocation("AVJ Heights");
        homePage.goToHomePage();

        //Search and add items
        String[] itemsToSearch = {"Onion", "Apple", "Chicken"};
        int totalSearches = itemsToSearch.length;
        int searchCount = 0;

        homePage.searchForItemHomePage("Rice");
        searchResultsPage.addToBasket();
        searchCount++;

        for (String item : itemsToSearch) {
            homePage.searchForItemResultsPage(item);
            searchResultsPage.addToBasket();
            searchCount++;
        }

        // Verify cart item count
        String cartItemCount = searchResultsPage.getCartItemCount();
        System.out.println("total no of items in the cart are:" +cartItemCount);

        searchResultsPage.goToCart();
        //searchResultsPage.assertTextOfItems(searchCount);
        searchResultsPage.removeFirstItem();
        Assert.assertEquals(searchResultsPage.totalItemsInCart(), totalSearches);


        // Close the driver
        driver.quit();
    }
}