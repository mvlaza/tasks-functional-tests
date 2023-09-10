package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class TasksTest {

	private WebDriver acessaAplicacao() {
		FirefoxOptions ffopt = new FirefoxOptions() ;
		ffopt.addArguments("-headless");
		WebDriver driver = new FirefoxDriver(ffopt);
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso () {
		WebDriver driver = acessaAplicacao();
		
		try {
			// clicar em add todo
			driver.findElement(By.id("addTodo")).click();
			
			// preencher descrição
			driver.findElement(By.id("task")).sendKeys("Teste automático");
	
			// preencher data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2025");
			
			// clicar em save
			driver.findElement(By.id("saveButton")).click();
	
			// validar mensagem success
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
		} finally {			
			// fechar o browser
			driver.quit();
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemDescricao () {
		WebDriver driver = acessaAplicacao();
		
		try {
			// clicar em add todo
			driver.findElement(By.id("addTodo")).click();
			
			// preencher descrição
//			driver.findElement(By.id("task")).sendKeys("Teste automático");
	
			// preencher data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2025");
			
			// clicar em save
			driver.findElement(By.id("saveButton")).click();
	
			// validar mensagem success
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
		} finally {			
			// fechar o browser
			driver.quit();
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemData () {
		WebDriver driver = acessaAplicacao();
		
		try {
			// clicar em add todo
			driver.findElement(By.id("addTodo")).click();
			
			// preencher descrição
			driver.findElement(By.id("task")).sendKeys("Teste automático");
	
			// preencher data
//			driver.findElement(By.id("dueDate")).sendKeys("10/10/2025");
			
			// clicar em save
			driver.findElement(By.id("saveButton")).click();
	
			// validar mensagem success
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
		} finally {			
			// fechar o browser
			driver.quit();
		}
	}

	@Test
	public void naoDeveSalvarTarefaComDataNoPassado () {
		WebDriver driver = acessaAplicacao();
		
		try {
			// clicar em add todo
			driver.findElement(By.id("addTodo")).click();
			
			// preencher descrição
			driver.findElement(By.id("task")).sendKeys("Teste automático");
	
			// preencher data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2015");
			
			// clicar em save
			driver.findElement(By.id("saveButton")).click();
	
			// validar mensagem success
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
		} finally {			
			// fechar o browser
			driver.quit();
		}
	}

}
