package org.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;

	public static WebDriver launchBrowser(String brosername) {
		 if(brosername.equals("Chrome")) {
		 WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		 }
		 else if(brosername.equals("Firefox")) {
		 WebDriverManager.firefoxdriver().setup();
		 driver = new FirefoxDriver();
		 }
		 else if(brosername.equals("Edge")) {
		 WebDriverManager.edgedriver().setup();
		 driver = new EdgeDriver();
		 }

//		switch (brosername) {
//		case "Chrome":
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
//			break;
//		case "Firefox":
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
//			break;
//		case "Edge":
//			WebDriverManager.chromedriver().setup();
//			driver = new EdgeDriver();
//			break;
//
//		default:
//			System.err.println("Pls Enter the Valid Browser Name");
//			break;
//		}
		return driver;

	}

	public static void launchUrl(String url) {
		driver.get(url);

	}

	public static void maximize() {
		driver.manage().window().maximize();

	}

	public static void implicitWait(long sec) {
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);

	}

	public static void sendKeys(WebElement e, String value) {
		e.sendKeys(value);
	}

	public static void btnClick(WebElement e) {
		e.click();
	}

	public static void quit() {
		driver.quit();

	}

	public static String getCurrentUrl() {
		String url = driver.getCurrentUrl();
		return url;
	}

	public static String getTitle() {
		return driver.getTitle();
	}

	public static String getAttribute(WebElement e) {
		return e.getAttribute("value");

	}

	public static String getText(WebElement e) {
		return e.getText();

	}

	public static void moveToElement(WebElement target) {
		Actions a = new Actions(driver);
		a.moveToElement(target).perform();
	}

	public static void dragAndDrop(WebElement source, WebElement target) {
		Actions a = new Actions(driver);
		a.dragAndDrop(source, target).perform();

	}

	public static void selectByIndex(WebElement element, int index) {
		Select s = new Select(element);
		s.selectByIndex(index);

	}

	public static WebElement findElement(String locatorname, String locValue) {
		WebElement e = null;
		if (locatorname.equals("id")) {
			e = driver.findElement(By.id(locValue));
		} else if (locatorname.equals("name")) {
			e = driver.findElement(By.name(locValue));
		} else if (locatorname.equals("xpath")) {
			e = driver.findElement(By.xpath(locValue));
		}
		return e;
	}

	public static String getExcelData(String filename, String sheetname, int rowno, int cellno) throws IOException {
		File loc = new File(
				"C:\\Users\\sekar\\eclipse-workspace\\MavenProject07.30AM_Dec21\\src\\test\\resources\\Excel\\"
						+ filename + ".xlsx");
		FileInputStream st = new FileInputStream(loc);
		Workbook w = new XSSFWorkbook(st);
		Sheet sheet = w.getSheet(sheetname);
		Row row = sheet.getRow(rowno);
		Cell cell = row.getCell(cellno);
		int type = cell.getCellType();

		// type---1 String
		// type---0 Number,date
		String value = null;
		if (type == 1) {
			value = cell.getStringCellValue();
		} else {
			if (DateUtil.isCellDateFormatted(cell)) {
				value =  new SimpleDateFormat("dd-MMM-yyyy").format(cell.getDateCellValue());
			} else {
				value = String.valueOf((long) cell.getNumericCellValue());
			}
		}
		return value;

	}
}
