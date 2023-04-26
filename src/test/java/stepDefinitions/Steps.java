package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;
import utils.Utils;

public class Steps extends BaseClass {



    @Given("User Launch Chrome browser")
    public void user_Launch_Chrome_browser() {
       System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        lp = new LoginPage(driver);
    }

    @When("User opens URL {string}")
    public void user_opens_URL(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    @When("User enters Email as {string} and Password as {string}")
    public void user_enters_Email_as_and_Password_as(String email, String password) {
       lp.setUserName(email);
       lp.setPassword(password);

    }

    @When("Click on Login")
    public void click_on_Login() throws InterruptedException{
        lp.clickLogin();
    }

    @Then("Page Title should be {string}")
    public void page_Title_should_be(String exptitle) throws InterruptedException
    {

        if(driver.getPageSource().contains("Login was unsuccessful"))
        {


            Assert.assertTrue(true);
            driver.close();
        }
        else
        {

            Assert.assertEquals(exptitle, driver.getTitle());
        }
        Thread.sleep(5000);
    }

    @When("User click on Log out link")
    public void user_click_on_Log_out_link() throws InterruptedException {
       lp.clickLogout();
       Thread.sleep(3000);
    }


    @Then("close browser")
    public void close_browser() {
        driver.quit();
    }


    //Customer feature step definitions..........................................
    //Adding Customer


    @Then("User can view Dashboard")
    public void user_can_view_dashboard() {
        addCust=new AddcustomerPage(driver);
        Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle());
    }
    @When("User click on customers Menu")
    public void user_click_on_customers_menu() throws InterruptedException {
        Thread.sleep(3000);
        addCust.clickOnCustomersMenu();
    }
    @When("click on customers Menu Item")
    public void click_on_customers_menu_item() throws InterruptedException {
        Thread.sleep(2000);
        addCust.clickOnCustomersMenuItem();
    }
    @When("click on Add new button")
    public void click_on_add_new_button() {
        addCust.clickOnAddnew();
    }
    @Then("User can view Add new customer page")
    public void user_can_view_add_new_customer_page() {
        Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
    }
    @When("User enter customer info")
    public void user_enter_customer_info() throws InterruptedException {
        String email = "arif"+ Utils.generateRandomNumber(100, 999)+"@gmail.com";
        addCust.setEmail(email);
        addCust.setPassword("test123");
        addCust.setCustomerRoles("Guest");
        Thread.sleep(3000);
        addCust.setManagerOfVendor("Vendor 1");
        addCust.setGender("Male");
        addCust.setFirstName("Arif");
        addCust.setLastName("Miay");
        addCust.setDob("7/05/1985"); // Format: D/MM/YYY
        addCust.setCompanyName("busyQA");
        addCust.setAdminContent("This is for testing.........");
    }
    @When("click on Save button")
    public void click_on_save_button() {
        addCust.clickOnSave();
    }
    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String string) {
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
                .contains("The new customer has been added successfully"));
    }



    //Searching customers using EMail ID ...................................


    @When("Enter customer EMail")
    public void enter_customer_e_mail() {
        searchCust=new SearchCustomerPage(driver);
        searchCust.setEmail("victoria_victoria@nopCommerce.com");
    }
    @When("Click on search button")
    public void click_on_search_button() throws InterruptedException {
        searchCust.clickSearch();
        Thread.sleep(3000);
    }
    @Then("User should found Email in the Search table")
    public void user_should_found_email_in_the_search_table() {
        boolean status=searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
        Assert.assertEquals(true, status);
    }


    //Searching customers using Name ...................................


    @When("Enter customer FirstName")
    public void enter_customer_first_name() {
        searchCust=new SearchCustomerPage(driver);
        searchCust.setFirstName("Victoria");
    }
    @When("Enter customer LastName")
    public void enter_customer_last_name() {
        searchCust.setLastName("Terces");
    }
    @Then("User should found Name in the Search table")
    public void user_should_found_name_in_the_search_table() {
        boolean status=searchCust.searchCustomerByName("Victoria Terces");
        Assert.assertEquals(true, status);
    }


}
