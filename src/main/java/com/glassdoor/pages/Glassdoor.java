package com.glassdoor.pages;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.glassdoor.base.TestUtilities;

public class Glassdoor extends TestUtilities{
	

	public Glassdoor() {
		
	}

	@Test
	public void test() throws InterruptedException, IOException {

		System.out.println("Starting Chrome Webdriver");
		System.setProperty("webdriver.chrome.driver", "/Users/muhamedsalah/Downloads/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("Opening URL");
		driver.get(
				"https://www.glassdoor.com/Job/san-francisco-data-scientist-jobs-SRCH_IL.0,13_IC1147401_KO14,28.htm?radius=100&includeNoSalaryJobs=true");
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// WebDriverWait wait = new WebDriverWait(driver, 5);
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/section[1]/article[1]/div[1]/ul[1]/li[1]/div[2]/a[1]/span[1]")));
		// List<WebElement> elements =
		// driver.findElements(By.className("job-search-key-l2wjgv e1n63ojh0 jobLink"));

		
		// Locating the web elements of jobs, salaries, company names
		List<WebElement> elements = driver
				.findElements(By.xpath("//a[@class='jobLink job-search-key-1rd3saf eigr9kq1']"));
		List<WebElement> salaries = driver.findElements(By.xpath("//span[@class='job-search-key-1hbqxax e1wijj240']"));
		List<WebElement> companies = driver
				.findElements(By.xpath("//a[@class=' job-search-key-l2wjgv e1n63ojh0 jobLink']"));
		System.out.println(elements.size());
//		String[] jobs = new String[elements.size()];
//		String[] salary = new String[salaries.size()];
//		String[] company = new String[companies.size()];
//		for (WebElement element : elements) {
//
//			System.out.println(element.getText());
//			
//		}

//		System.out.println(jobs.length);
//		for(int i=0; i<elements.size(); i++) {
//			jobs[i] = elements.get(i).getText();
//			salary[i] = salaries.get(i).getText();
//			company[i] = companies.get(i).getText();
//			System.out.println(jobs[i]);
//			//System.out.println("The Salary of " + elements.get(i).getText() + " in Company " + companies.get(i).getText() +  " is in Range " + salaries.get(i).getText());
//		}

		// creating a csv file using arraylist
		FileWriter writer = new FileWriter("/Users/muhamedsalah/Downloads/csvFile.csv");

		List<String> arrayListToCsv = new ArrayList<>();
		for (int i = 0; i < elements.size(); i++) {

			arrayListToCsv.add(elements.get(i).getText());
			arrayListToCsv.add(salaries.get(i).getText());
			arrayListToCsv.add(companies.get(i).getText());
		}

		String collect = arrayListToCsv.stream().collect(Collectors.joining(","));
		System.out.println(collect);

		writer.write(collect);
		writer.close();
		driver.quit();
	}

	

}
