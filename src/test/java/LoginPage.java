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

//    public static String getTextFromMessage(Message message) throws Exception {
//        if (message.isMimeType("text/plain")){
//            return message.getContent().toString();
//        }else if (message.isMimeType("multipart/*")) {
//            String result = "";
//            MimeMultipart mimeMultipart = (MimeMultipart)message.getContent();
//            int count = mimeMultipart.getCount();
//            for (int i = 0; i < count; i ++){
//                BodyPart bodyPart = mimeMultipart.getBodyPart(i);
//                if (bodyPart.isMimeType("text/plain")){
//                    result = result + "\n" + bodyPart.getContent();
//                    break;
//                } else if (bodyPart.isMimeType("text/html")){
//                    String html = (String) bodyPart.getContent();
//                    result = result + "\n" + Jsoup.parse(html).text();
//                }
//            }
//            return result;
//        }
//        return "";
//    }
}