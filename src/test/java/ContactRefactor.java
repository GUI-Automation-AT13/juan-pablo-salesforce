import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ContactRefactor {
    private static ChromeDriver driver;
    private static JavascriptExecutor js;
    WebElement element;
    Actions builder;
    String urlBase = "https://jpcorp-dev-ed.lightning.force.com";
    String username = "juampi7237@gmail.com";
    String password = "JP123456jp";
    String firstName = "Juan";
    String lastName = "Gonzales";
    WebDriverWait wait;

    @BeforeClass
    public static void setup() throws Exception {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Before
    public void Login() {
        driver.get(urlBase);
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("Login")).click();
    }


    @Test
    public void createContact() {
        wait = new WebDriverWait(driver, 30);
        driver.findElement(By.cssSelector(".slds-icon-waffle")).click();
        driver.findElement(By.xpath("//input[@placeholder='Search apps and items...']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Search apps and items...']")).sendKeys("Contacts");
        WebElement element = driver.findElement(By.xpath("//a[@data-label='Contacts']"));
        js.executeScript("arguments[0].click();", element);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[contains(text(),'New')]")).click();
        driver.findElement(By.xpath("//input[@name='lastName']")).click();
        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lastName);
        driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".slds-theme--success")));
        Assert.assertEquals("success\nContact " + "\"" + lastName + "\"" + " was created.\nClose",
                driver.findElement(By.cssSelector(".slds-theme--success")).getText());
    }

    @Test
    public void createContactAll() {
        wait = new WebDriverWait(driver, 30);
        driver.findElement(By.cssSelector(".slds-icon-waffle")).click();
        driver.findElement(By.xpath("//input[@placeholder='Search apps and items...']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Search apps and items...']")).sendKeys("Contacts");
        WebElement element = driver.findElement(By.xpath("//a[@data-label='Contacts']"));
        js.executeScript("arguments[0].click();", element);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[contains(text(),'New')]")).click();
        driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lastName);
//        driver.findElement(By.xpath("//input[@placeholder='Search Accounts...']")).click();
//        driver.findElement(By.xpath("(//ul[@role=\"group\"]//lightning-base-combobox-item[contains(@class, 'slds-listbox__option')])[1]")).click();
        driver.findElement(By.xpath("//input[@name='Title']")).sendKeys("Title");
        driver.findElement(By.xpath("//input[@name='Department']")).sendKeys("Department");
        driver.findElement(By.xpath("//input[@name='Phone']")).sendKeys("Phone");
        driver.findElement(By.xpath("//input[@name='HomePhone']")).sendKeys("HomePhone");
        driver.findElement(By.xpath("//input[@name='MobilePhone']")).sendKeys("MobilePhone");
        driver.findElement(By.xpath("//input[@name='OtherPhone']")).sendKeys("OtherPhone");
        driver.findElement(By.xpath("//input[@name='Fax']")).sendKeys("Fax");
        driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("email@gmail.com");
        driver.findElement(By.xpath("//input[@name='AssistantName']")).sendKeys("AssistantName");
        driver.findElement(By.xpath("//input[@name='AssistantPhone']")).sendKeys("AssistantPhone");
        driver.findElement(By.xpath("(//textarea[@name='street'])[1]")).sendKeys("street1");
        driver.findElement(By.xpath("(//input[@name='postalCode'])[1]")).sendKeys("postalcode");
        driver.findElement(By.xpath("(//input[@name='city'])[1]")).sendKeys("city");
        driver.findElement(By.xpath("(//input[@name='province'])[1]")).sendKeys("Province");
        driver.findElement(By.xpath("(//input[@name='country'])[1]")).sendKeys("country");
        driver.findElement(By.xpath("(//textarea[@name='street'])[2]")).sendKeys("strret2");
        driver.findElement(By.xpath("(//input[@name='postalCode'])[2]")).sendKeys("postalcode2");
        driver.findElement(By.xpath("(//input[@name='city'])[2]")).sendKeys("city2");
        driver.findElement(By.xpath("(//input[@name='province'])[2]")).sendKeys("Province2");
        driver.findElement(By.xpath("(//input[@name='country'])[2]")).sendKeys("country2");
        driver.findElement(By.xpath("//input[@name='Languages__c']")).sendKeys("Languages__c");
        driver.findElement(By.xpath("(//force-record-layout-section//textarea)[3]")).sendKeys("description");
        driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".slds-theme--success")));
        assertThat(driver.findElement(By.cssSelector(".slds-theme--success")).getText(),
                is("success"+"\nContact "+ "\"" + firstName+" "+lastName+ "\"" + " was created."+"\nClose"));
    }

    @After
    public void logout() throws Exception {
        element = driver.findElement(By.cssSelector(".photoContainer"));
        builder = new Actions(driver);
        builder.moveToElement(element).perform();
        element = driver.findElement(By.tagName("body"));
        builder = new Actions(driver);
        builder.moveToElement(element, 0, 0).perform();
        driver.findElement(By.cssSelector(".profileTrigger > .uiImage")).click();
        driver.findElement(By.linkText("Log Out")).click();
    }

    @AfterClass
    public static void closeDriver() throws Exception {
        driver.quit();
    }

}