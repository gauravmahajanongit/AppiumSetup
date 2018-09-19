package tests;


import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
 
public class AppiumTest {
 
		private static AndroidDriver driver;
		public static void main(String[] args) throws MalformedURLException, InterruptedException {
 
			
			// desired capabilities to run this test are listed out here, Desired Capabilities are keys and values encoded in a JSON object, sent by Appium clients to the server when a new automation session is requested
 
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("deviceName", "ZY223J3NSP"); // replace the device name with device id found in adb devices list here
			capabilities.setCapability("platformVersion", "7.0");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("app", "/Users/gmahajan/APKs/Viu.apk"); // replace this path with location of apk file on  machine where you would like to run this test
			capabilities.setCapability("appPackage", "com.vuclip.viu");
			capabilities.setCapability("appActivity", "com.vuclip.viu.ui.screens.MainActivity");
			
			// initiating appium driver here
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			Thread.sleep(10000);
		
			
			//actual tests starts here, app initiated and checking landing screen  
			
			while(!driver.findElementById("com.vuclip.viu:id/internal_layout_1").isDisplayed())   // waiting for the language selection page
			{
			        Thread.sleep(1000);} 
			
			MobileElement el0 = (MobileElement) driver.findElementById("com.vuclip.viu:id/internal_layout_1"); // choosing hindi as language
			el0.click();
			
			while(!driver.findElementById("com.vuclip.viu:id/iv_search").isEnabled())
			{
			        Thread.sleep(1000);}
			
			MobileElement el1 = (MobileElement) driver.findElementById("com.vuclip.viu:id/iv_search");
			el1.click();
			
			MobileElement el2 = (MobileElement) driver.findElementById("com.vuclip.viu:id/search_box"); // waiting for the content to load and then looking for search icon on top right
			el2.sendKeys("social");																		// sending social as input in search bar
			
			MobileElement el3 = (MobileElement) driver.findElementById("com.vuclip.viu:id/search_result");
			el3.click();

			Thread.sleep(15000);
			
			MobileElement el4 = (MobileElement) driver.findElementByXPath("(//android.widget.RelativeLayout[@content-desc=\"Social\"])[1]/android.widget.FrameLayout/android.widget.ImageView");
			el4.click(); // selecting the result for social series
			
		
			
			Thread.sleep(1000);

			System.out.println("waiting to scroll");
			
			(new TouchAction(driver))
			  .press(PointOption.point( 755, 1498))
			  .moveTo(PointOption.point(774, 832))
			  .release()
			  .perform();
		
			 MobileElement el5 = (MobileElement) driver.findElementByXPath("(//android.widget.LinearLayout[@content-desc=\"Social - Trailer (Telugu)\"])[2]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.ImageView");
			 el5.click();
			 MobileElement el6 = (MobileElement) driver.findElementById("com.vuclip.viu:id/tv_low");
			 el6.click();
           
        	while(!driver.findElementById("SUCCESSFUL").isDisplayed())   // waiting for the download to complete by checking for completion icon
			{
			        Thread.sleep(1000);}  
        	
        	MobileElement el8 = (MobileElement) driver.findElementByAccessibilityId("SUCCESSFUL"); // clicking on the check to initiate the video
        	el8.click();
        	
        	MobileElement el9 = (MobileElement) driver.findElementById("com.vuclip.viu:id/iv_yes");
        	el9.click();
  
        	
        	MobileElement el11 = (MobileElement) driver.findElementById("com.vuclip.viu:id/surface_view"); // click to intiate the forward by 10 sec button
        	el11.click();
        	
        	(new TouchAction(driver)).tap(PointOption.point( 883, 302)).perform(); // this is to forward the video by 10 sec
        	
        	driver.quit();
			
	}
 
}

