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

public class Test3 {
    //appium'u programaticly calistirmak icin
    AppiumDriverLocalService service;
    AppiumDriver<MobileElement> driver;
    DesiredCapabilities caps;
    WebDriverWait wait;
@BeforeTest
public void beforeTest(){
    //appium'u hangi IP ve Port'tan calistirilmasi gerektigini belirtiyorum
    service = new AppiumServiceBuilder()
            .withIPAddress("127.0.0.1")
            .usingPort(4723)   //4723 portundan calissin
            //.usingAnyFreePort
            .build();
    service.start();
    service.clearOutPutStreams();// consoldaki kalabalik ciktiyi engeller
    //baglanti bilgileri icin desired capabilities olusturuldu
     caps = new DesiredCapabilities();

    //baglanacak cihaz bilgileri girildi
    caps.setCapability("appium:udid", Devices.Pixel_2.getUdid());
    caps.setCapability("appium:version", Devices.Pixel_2.getVersion());
    caps.setCapability("appium:deviceName", Devices.Pixel_2.getDeviceName());
    caps.setCapability("platformName",Devices.Pixel_2.getPlatformName());

    // kullanilacak App'in package ve appActivity'si

    caps.setCapability("appium:appPackage", App.Phone.getAppPackage());
    caps.setCapability("appium:appActivity",App.Phone.getAppActivity());

    driver = new AndroidDriver<>(service.getUrl(), caps);
    wait = new WebDriverWait(driver,15);
}
    @Test
    public void tsetConnection() throws MalformedURLException, InterruptedException {


        //appium 4723 portunda calisiyor olmali
//        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);

        By okButton = By.xpath("//*[@text='OK']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(okButton)).click();

        By startChatButton = By.xpath("//*[@text='Start chat']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(startChatButton)).click();


    }
    @AfterTest
    public void afterTest(){
    service.stop();
    driver.quit();
    }
}
