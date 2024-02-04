import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class SearchResultsPage extends BasePage {

    @AndroidFindBy(id = "com.bigbasket.mobileapp:id/btnAddToBasket")
    private MobileElement addToBasketButton;

    @AndroidFindBy(id = "com.bigbasket.mobileapp:id/cartcountView")
    private MobileElement cartItemCount;

    @AndroidFindBy(id = "com.bigbasket.mobileapp:id/productPageSearchBox")
    private MobileElement searchTextBox;

    @AndroidFindBy(id = "com.bigbasket.mobileapp:id/basketCountViewContainer")
    private MobileElement cartButton;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Remove\"])[1]")
    private MobileElement removeFirstItem;

    @AndroidFindBy(id = "com.bigbasket.mobileapp:id/deleteView")
    private MobileElement deleteItem;

    @AndroidFindBy(id = "com.bigbasket.mobileapp:id/topCatTotalItems")
    private MobileElement itemsLeft;

    public MobileElement getCartButtonByIndex(int index) {
        String xpath = "(//com.bigbasket.mobileapp:id/txtProductDesc)[" + index + "]";
        return driver.findElement(By.xpath(xpath));
    }



    WebDriverWait wait = new WebDriverWait(driver, 20);


    public SearchResultsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public void addToBasket() {
        wait.until(ExpectedConditions.elementToBeClickable(addToBasketButton)).click();
    }

    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
    }

    public String getCartItemCount() {
        return cartItemCount.getText();
    }

    public void assertTextOfItems(int noOfItems) throws InterruptedException {
        Thread.sleep(8000);
        for (int i = 1; i <= noOfItems; i++) {
            //MobileElement cartButton = getCartButtonByIndex(i);
            // Get the actual text from the cartButton
            String actualText = getCartButtonByIndex(i).getText();
            System.out.println(actualText);

            String expectedText = "";
            switch (i) {
                case 1:
                    expectedText = "Rice";
                    break;
                case 2:
                    expectedText = "Onion";
                    break;
                case 3:
                    expectedText = "Apple";
                    break;
                case 4:
                    expectedText = "Chicken";
                    break;
                // Add more cases as needed

            }
            // Perform assertion
            Assert.assertTrue(actualText.toLowerCase().contains(expectedText.toLowerCase()),
                    "Text does not contain the expected value: " + expectedText);
        }
    }

    public void removeFirstItem() throws InterruptedException {

        Thread.sleep(10000);
        // Get the screen dimensions
        int screenHeight = driver.manage().window().getSize().getHeight();
        int screenWidth = driver.manage().window().getSize().getWidth();

        // Define start and end points for the swipe (adjust as needed)
        int startX = (int) (screenWidth * 0.9); // 80% from the right
        int endX = (int) (screenWidth * 0.1);   // 20% from the right
        int centerY = screenHeight / 2;

        // Perform the swipe using TouchAction
        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.press(PointOption.point(startX, centerY))
                .moveTo(PointOption.point(endX, centerY))
                .release()
                .perform();

        wait.until(ExpectedConditions.visibilityOf(deleteItem)).click();
    }

    public int totalItemsInCart(){
        String items = itemsLeft.getText();
        System.out.println("Items left in the cart after deletion = " +items);
        String extractedNumberString = items.replaceAll("[^0-9]", ""); // Remove non-digit characters
        return Integer.parseInt(extractedNumberString);
    }
}
