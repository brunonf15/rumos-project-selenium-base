package primeiroTesteSelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PrimeiroTeste {
	public static void main(String[] args) {
        // Configurar o caminho para o ChromeDriver (Linux)
        String chromeDriverPath = "drivers/chromedriver";

        // Definir a propriedade para o caminho do ChromeDriver
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        // Inicializar o WebDriver para o Chrome
        WebDriver driver = new ChromeDriver();

        // Abrir o navegador e acessar o site "https://www.example.com"
        driver.get("https://www.example.com");

        // Verificar o título da página
        String pageTitle = driver.getTitle();
        if (pageTitle.equals("Example Domain")) {
            System.out.println("Título da página está correto!");
        } else {
            System.out.println("Título da página não está correto.");
        }

        // Fechar o navegador
        driver.quit();
    }
}
