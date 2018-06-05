package ual.hmis.sesion06;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class RegistroRandom {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testRegistroRandom() throws Exception {
    driver.get("https://logappf1hmis2018.azurewebsites.net/");
    driver.findElement(By.linkText("Register")).click();
    int numberRandom = (int) Math.floor(Math.random()*1500000);
    String s = "" + numberRandom + "@ual.es";
    CharSequence email = s.toString();
    // ERROR: Caught exception [ERROR: Unsupported command [getExpression | javascript{"ual" + Math.floor(Math.random()*1500000) + "@ual.es";} | ]]
    System.out.println(email);
    driver.findElement(By.id("Email")).clear();
    driver.findElement(By.id("Email")).sendKeys(email);
    driver.findElement(By.id("Password")).clear();
    driver.findElement(By.id("Password")).sendKeys("ABab12!!");
    driver.findElement(By.id("ConfirmPassword")).clear();
    driver.findElement(By.id("ConfirmPassword")).sendKeys("ABab12!!");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    assertTrue(isElementPresent(By.linkText("Hello " + email + "!")));
    driver.findElement(By.xpath("//form[@id='logoutForm']/ul/li[2]/button")).click();
    assertEquals("Log in", driver.findElement(By.linkText("Log in")).getText());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
