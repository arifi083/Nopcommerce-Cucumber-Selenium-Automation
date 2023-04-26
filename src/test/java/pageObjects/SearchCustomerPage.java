package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

public class SearchCustomerPage {
    public WebDriver ldriver;

    public SearchCustomerPage(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(ldriver, this);
    }

    @FindBy(id ="SearchEmail")
    WebElement txtEmail;

    @FindBy(id ="SearchFirstName")
    WebElement txtFirstName;

    @FindBy(id="SearchLastName")
    WebElement txtLastName;

    @FindBy(id="search-customers")
    WebElement btnSearch;

    @FindBy(xpath = "//table[@role='grid']")
    WebElement tblSearchResults;

    @FindBy(xpath= "//table[@id='customers-grid']")
    WebElement table;

    @FindBy(xpath= "//table[@id='customers-grid']//tbody/tr")
    List<WebElement> tableRows;

    @FindBy(xpath= "//table[@id='customers-grid']//tbody/tr/td")
    List<WebElement> tableColumns;

    public void setEmail(String email) {

        System.out.println(email);
        txtEmail.sendKeys(email);
    }

    public void setFirstName(String fname) {

        txtFirstName.clear();
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname) {

        txtLastName.clear();
        txtLastName.sendKeys(lname);
    }

    public void clickSearch() throws InterruptedException {
        btnSearch.click();
        Thread.sleep(3000);

    }

    public int getNoOfRows() {
        return (tableRows.size());
    }

    public int getNoOfColumns() {
        return (tableColumns.size());
    }

    public boolean searchCustomerByEmail(String email) {
        boolean flag = false;

        for (int i = 1; i <= getNoOfRows(); i++) {
            String emailid = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[2]"))
                    .getText();



            System.out.println(emailid);

            if (emailid.equals(email)) {
                flag = true;
                break;
            }
        }

        return flag;

    }

    public boolean searchCustomerByName(String Name) {
        boolean flag = false;

        for (int i = 1; i <= getNoOfRows(); i++) {
            String name = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[3]"))
                    .getText();


            if (Name.equals(name)) {
                flag = true;
                break;
            }
        }

        return flag;

    }


}
