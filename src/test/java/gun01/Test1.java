package gun01;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Test1 {
    //appium'u programaticly calistirmak icin
AppiumDriverLocalService service;
@BeforeTest
public void beforeTest(){
    //appium'u hangi IP ve Port'tan calistirilmasi gerektigini belirtiyorum
    service = new AppiumServiceBuilder()
            .withIPAddress("127.0.0.1")
            .usingPort(4723)   //4723 portundan calissin
            //.usingAnyFreePort
            .build();
    service.start();
}
    @Test
    public void tsetConnection() throws MalformedURLException, InterruptedException {

        //baglanti bilgileri icin desired capabilities olusturuldu
        DesiredCapabilities caps = new DesiredCapabilities();

        //baglanacak cihaz bilgileri girildi
        caps.setCapability("appium:udid", "emulator-5554");
        caps.setCapability("appium:version", "12");
        caps.setCapability("appium:deviceName", "pixel 2");
        caps.setCapability("platformName", "Android");

        // kullanilacak App'in package ve appActivity'si

        caps.setCapability("appium:appPackage","com.google.android.apps.messaging");
        caps.setCapability("appium:appActivity","com.google.android.apps.messaging.ui.ConversationListActivity");
        AppiumDriver<MobileElement> driver;

        //appium 4723 portunda calisiyor olmali
//        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver = new AndroidDriver<>(service.getUrl(), caps);


        driver.findElement(By.xpath("//*[@text='OK']")).click();
        driver.findElement(By.xpath("//*[@text='Start chat']")).click();

        driver.quit();
    }
    @AfterTest
    public void afterTest(){
    service.stop();
    }
}
