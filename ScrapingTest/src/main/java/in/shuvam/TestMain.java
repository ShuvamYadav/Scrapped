package in.shuvam;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestMain {
	public static void main(String args[]) {
		String year = "2020";
		String month = "Jan";
		int date = 13, rowid = 0;
		boolean flag = true;
		List<WebElement> dates;
		System.setProperty("webdriver.chrome.driver", "C:\\selenium-java-3.141.59\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
		options.setCapability(ChromeOptions.CAPABILITY, options);
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.addArguments("--disable-blink-features");
		options.addArguments("--disable-blink-features=AutomationControlled");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://economictimes.indiatimes.com/news/economy/finance");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement el = driver.findElement(By.className("autoload_continue"));
		js.executeScript("arguments[0].scrollIntoView();", el);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		do {
			js.executeScript("arguments[0].click()", el);
			dates = driver.findElements(By.className("date-format"));
			String[] s = dates.get(dates.size() - 1).getText().split(" ");
			s[1] = s[1].replaceAll(",", "");
			if (s[2].trim().equals(year) && s[0].trim().equals(month) && Integer.parseInt(s[1]) <= date)
				flag = false;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (flag);
		List<WebElement> list = driver.findElements(By.tagName("h3"));
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Sheet1");// creating a blank sheet
		for(int i=0;i<list.size()-2;i++) {
			Row row= sheet.createRow(rowid++);
			row.createCell(0).setCellValue(list.get(i).getText().toString());
			row.createCell(1).setCellValue(dates.get(i).getAttribute("data-time").toString());
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
		}
		try {
			FileOutputStream out = new FileOutputStream(new File("D:/Finance.xlsx"));

			workbook.write(out);
			out.close();
		} catch (Exception e) {
		}
		;
		System.out.println("Successful");

	}

}