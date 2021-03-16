package DesignPatterns;

import DesignPatterns.PageObjects.TravelHomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DemoTest extends BaseTest{
    WebDriver driver;
    TravelHomePage travelHomePage;

    @BeforeTest
    public void setUp(){
        driver = initializeDriver();
        travelHomePage = new TravelHomePage(driver);
    }
    @Test(dataProvider = "getData")
    public void flightTest(HashMap<String,String> reservationDetails){

        travelHomePage.goTo();
        System.out.println(travelHomePage.getFooterNav().getFlightAttribute());
        System.out.println(travelHomePage.getNavigationBar().getFlightAttribute());
        System.out.println(travelHomePage.getFooterNav().getLinkCount());
        System.out.println(travelHomePage.getNavigationBar().getLinkCount());
//Strategy design pattern
        //SearchFlightAvailability roundTrip = new RoundTrip(); ---setBookingStrategy method created
        //SearchFlightAvailability multiTrip = new MultiTrip(); ---which will set the interface object to implement checkavailability
       // travelHomePage.setBookingStrategy(new RoundTrip(driver, sectionElement));
        //travelHomePage.setBookingStrategy(new MultiTrip(driver, sectionElement));
        travelHomePage.setBookingStrategy("multitrip");

        travelHomePage.checkAvail(reservationDetails);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
       /* HashMap<String,String> reservationDetails = new <String, String>HashMap();
        reservationDetails.put("origin","MAA");
        reservationDetails.put("destination","DEL");
        reservationDetails.put("destination_2","HYD");
        HashMap<String,String> reservationDetails1 = new <String, String>HashMap();
        reservationDetails1.put("origin","DEL");
        reservationDetails1.put("destination","HYD");
        reservationDetails1.put("destination_2","MAA");

        List<HashMap<String,String>> l = new ArrayList();
        l.add(reservationDetails);
        l.add(reservationDetails1);*/
        //instead of above give below code
        List<HashMap<String,String>> l = getJsonData(System.getProperty("user.dir")+"//src//test//java//DesignPatterns//DataLoads//reservationDetails.json");
        return new Object[][]
                {
                        {l.get(0)}, {l.get(1)}
                };
    }
}
