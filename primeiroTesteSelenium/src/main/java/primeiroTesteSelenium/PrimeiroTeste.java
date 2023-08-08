package primeiroTesteSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PrimeiroTeste {

	private final By searchInput = By.id("searchboxinput");
	private WebElement wait;
	private final By directions = By.cssSelector(".google-symbols.Cw1rxd");
	private final By searchButton = By.id("searchbox-searchbutton");
	private final By headline = By.xpath("(//input[@aria-label='Destino Dublin, Irlanda'])[1]");

	@Test
	public void testeTituloPagina() {
		// Configurar o caminho para o ChromeDriver (Linux)
		String chromeDriverPath = "drivers/chromedriver";

		// Definir a propriedade para o caminho do ChromeDriver
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);

		// Inicializar o WebDriver para o Chrome
		WebDriver driver = new ChromeDriver();

		// Go to https://www.google.com/maps
		driver.get("https://www.google.com/maps");

		// Assert title page before by pass
		String mapsTitle = driver.getTitle();
		// System.out.println("Value from page title " + mapsTitle);
		Assert.assertTrue(mapsTitle.contains("Maps"));

		By acceptCookiesButton = By.cssSelector("div[class='VtwTSb'] form:nth-child(2)");
		driver.findElement(acceptCookiesButton).click();

		Assert.assertTrue(mapsTitle.contains("Maps"));

		// 1. Wait to load the search box and after fill Dublin
		wait = new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.elementToBeClickable(searchInput));
		driver.findElement(searchInput).sendKeys("Dublin");

		// 2. Wait to load the search button and after click on this button
		wait = new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.elementToBeClickable(searchButton));
		driver.findElement(searchButton).click();


		// Wait for the first "google-symbols Cw1rxd" element to be clickable
		wait = new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.elementToBeClickable(directions));

		// Now, get all "google-symbols Cw1rxd" elements
		List<WebElement> directionsElements = driver.findElements(directions);

		// Ensure that at least one element is found before accessing the first one
		if (!directionsElements.isEmpty()) {
			// Click on the first "google-symbols Cw1rxd" element
			directionsElements.get(0).click();
		} else {
			System.out.println("No elements found with the specified class.");
		}

		try {
			Thread.sleep(2000); // 2000 milliseconds = 2 seconds
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String headText = driver.findElement(headline).getAttribute("value");
		System.out.println("Value from headline " + headText);
		Assert.assertTrue(headText.contains("Dublin"));

		// Fechar o navegador
		driver.quit();
	}
}
