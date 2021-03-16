package DesignPatterns.PageComponents;

import DesignPatterns.AbstractComponents.AbstractComponent;
import DesignPatterns.AbstractComponents.SearchFlightAvailability;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.function.Consumer;

public class MultiTrip extends AbstractComponent implements SearchFlightAvailability {

    private By multiCity_rdo = By.id("ctl00_mainContent_rbtnl_Trip_2");
    private By modalPopUp = By.id("MultiCityModelAlert");
    private By from = By.id("ctl00_mainContent_ddl_originStation1_CTXT");
    private By to = By.id("ctl00_mainContent_ddl_destinationStation1_CTXT");
    private By destiantion_2 = By.id("ctl00_mainContent_ddl_originStation2_CTXT");
    private By submit = By.id("ctl00_mainContent_btn_FindFlights");

    public MultiTrip(WebDriver driver, By sectionElement) {
        super(driver, sectionElement);
    }

    @Override
    public void checkAvailability(HashMap<String, String> reservtaionDetails) {
        //execute around pattern
        makeStateReady(s->selectOriginCity(reservtaionDetails.get("origin")));
        selectDestinationCity(reservtaionDetails.get("destination"));
        selectDestinationCity2(reservtaionDetails.get("destination_2"));
    }

    public void selectOriginCity(String origin){
        findElement(from).click();
        findElement(By.xpath("//a[@value='"+origin+"']")).click();
    }

    public void selectDestinationCity(String destination){
        findElement(to).click();
        findElement(By.xpath("(//a[@value='"+destination+"'])[2]")).click();
    }

    public void selectDestinationCity2(String destination2){
        findElement(destiantion_2).click();
        findElement(By.xpath("(//a[@value='"+destination2+"'])[3]")).click();
    }

    public void makeStateReady(Consumer<MultiTrip> consumer){
        //common prerequisite code
        System.out.println("I am inside multi trip");
        findElement(multiCity_rdo).click();
        findElement(modalPopUp).click();
        waitForElementToDisappear(modalPopUp);
        //accepts any method of the current class
        //execute actual function
        consumer.accept(this);
        //tear down method
        System.out.println("I am done");
    }

}
