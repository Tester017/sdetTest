package com.salesforce;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class SaleseForce {
	
	@Test
	public void saleseForce() throws InterruptedException {
		
		try {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");

			
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			ChromeDriver driver = new ChromeDriver(options);
			
			driver.get("https://login.salesforce.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			driver.findElementById("username").sendKeys("hari.radhakrishnan@testleaf.com");
			
			driver.findElementById("password").sendKeys("India@123");

			driver.findElementById("Login").click();
			
			WebElement profileEle = driver.findElementByXPath("//div[@class='profileTrigger branding-user-profile bgimg slds-avatar slds-avatar_profile-image-small circular forceEntityIcon']");		
			Actions builder = new Actions(driver);
			builder.moveToElement(profileEle).perform();
			
			try
			{
			WebElement viewProfileEle = driver.findElementByXPath("//span[text()='View profile']");	
			System.out.println(viewProfileEle.getText()+ " is present");
			}
			catch(Exception e)
			{
				System.out.println("view Profile element is not present");
			}
			
			driver.findElementByXPath("//a[@class='globalCreateTrigger slds-button slds-button_icon slds-button_icon slds-button_icon-container slds-button_icon-small slds-global-actions__task slds-global-actions__item-action']").click();
			
			driver.findElementByXPath("//span[text()='New Lead']").click();
			
			driver.findElementByXPath("//a[@class='select']").click();
			
			Robot r = new Robot();
			r.delay(100);
			r.keyPress(KeyEvent.VK_DOWN);
			r.delay(100);
			r.keyRelease(KeyEvent.VK_DOWN);
			
			r.keyPress(KeyEvent.VK_DOWN);
			r.delay(100);
			r.keyRelease(KeyEvent.VK_DOWN);
			
			r.keyPress(KeyEvent.VK_ENTER);
			r.delay(100);
			r.keyRelease(KeyEvent.VK_ENTER);
			
			driver.findElementByXPath("//input[@class='firstName compoundBorderBottom form-element__row input']").sendKeys("Daniel");
			
			driver.findElementByXPath("//input[@class='lastName compoundBLRadius compoundBRRadius form-element__row input']").sendKeys("Francis");

			driver.findElementByXPath("//span[text()='Company']/following::input").sendKeys("Maveric");

			driver.findElementByXPath("//button[@class='slds-button slds-button--brand cuf-publisherShareButton uiButton']/span[text()='Save']").click();

			String confirmationText = driver.findElementByXPath("//div[@class='slds-theme--success slds-notify--toast slds-notify slds-notify--toast forceToastMessage']//span[contains(text(),'Lead')]").getText();

			Thread.sleep(1000);

			System.out.println(confirmationText);
			String expectedText = "Daniel Francis"; 
			
			String ActualText = confirmationText.substring(5,expectedText.length()+5);
			System.out.println(ActualText);
			
			if(expectedText.equals(ActualText)) System.out.println("Matched and Lead is successfully created");
			else System.out.println("not matched");
			
			driver.findElementByXPath("//div[@class='slds-icon-waffle']").click();

			driver.findElementByXPath("//button[text()='View All']").click();

			driver.findElementByXPath("//p[text()='Sales']").click();
			
			String salesTextActual = driver.findElementByXPath("//p[text()='Sales']").getText();
			
			if(salesTextActual.equals("Sales")) System.out.println("Sales is present");
			else System.out.println("Sales is not present");

			Thread.sleep(3000);


			driver.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
