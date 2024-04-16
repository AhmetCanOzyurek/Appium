package gun01;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.App;
import utils.Devices;

import java.net.MalformedURLException;

public class Test4 {
    //appium'u programaticly calistirmak icin
    AppiumDriverLocalService service;
    AppiumDriver<?> driver;
    DesiredCapabilities caps;
    WebDriverWait wait;

    public void startAppium() {
        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4723)   //4723 portundan calissin
                //.usingAnyFreePort
                .build();
        service.start();
        service.clearOutPutStreams();
    }

    public AppiumDriver<?> getDriver(Devices device, App app) {
        caps = new DesiredCapabilities();

        //baglanacak cihaz bilgileri girildi
        caps.setCapability("appium:udid", device.getUdid());
        caps.setCapability("appium:version", device.getVersion());
        caps.setCapability("appium:deviceName", device.getDeviceName());
        caps.setCapability("platformName", device.getPlatformName());

        // kullanilacak App'in package ve appActivity'si

        caps.setCapability("appium:appPackage", app.getAppPackage());
        caps.setCapability("appium:appActivity", app.getAppActivity());

        return new AndroidDriver<>(service.getUrl(), caps);
    }

    @BeforeTest
    public void beforeTest() {

        driver = getDriver(Devices.Pixel_2, App.Phone);
        wait = new WebDriverWait(driver, 15);
    }

    @Test
    public void tsetConnection() throws MalformedURLException, InterruptedException {
        //appium 4723 portunda calisiyor olmali
//        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);

        By threeDotsMenu = By.id("com.android.dialer:id/main_options_menu_button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(threeDotsMenu)).click();

        By startChatButton = By.xpath("//*[@text='Start chat']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(startChatButton)).click();

    }

    @AfterTest
    public void afterTest() {
        service.stop();
        driver.quit();
    }
}
