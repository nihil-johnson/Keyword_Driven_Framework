package actionKeywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import executionCore.Engine;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelUtilities;

public class ActionKeywords {
	static WebDriver driver;
	public static void Openbrowser() {
		switch(ExcelUtilities.dataColumnValue) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		case "Edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			break;
		default:
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		}
	}
	public static void gotoURL() throws InterruptedException {
		driver.get(ExcelUtilities.dataColumnValue);
		Thread.sleep(3000);
	}
	public static void Type() {
		driver.findElement(Engine.locator).sendKeys(ExcelUtilities.dataColumnValue);
	}
	public static void Click() {
		driver.findElement(Engine.locator).click();
	}
}
