package DesignPatterns.PageObjects;

import DesignPatterns.AbstractComponents.SearchFlightAvailability;
import DesignPatterns.AbstractComponents.StrategyFactor;
import DesignPatterns.PageComponents.FooterNav;
import DesignPatterns.PageComponents.NavigationBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class TravelHomePage {
    By sectionElement = By.id("traveller-home");
    By navsectionElement = By.id("buttons");
    WebDriver driver;
    SearchFlightAvailability searchFlightAvailability;

    public TravelHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
    }
    public NavigationBar getNavigationBar()
    {
        return new NavigationBar(driver,navsectionElement);
    }

    public FooterNav getFooterNav(){
        return new FooterNav(driver,sectionElement);
    }
    //creating objects by calling factory method createStrategy()
    public void setBookingStrategy(String strategyType){

        StrategyFactor strategyFactor = new StrategyFactor(driver);
        SearchFlightAvailability searchFlightAvail = strategyFactor.createStrategy(strategyType);
        this.searchFlightAvailability = searchFlightAvail;
    }

    public void checkAvail(HashMap<String,String> reservationDetails){
        searchFlightAvailability.checkAvailability(reservationDetails);
    }
}
