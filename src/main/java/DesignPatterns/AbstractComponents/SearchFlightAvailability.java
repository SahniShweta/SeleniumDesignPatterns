package DesignPatterns.AbstractComponents;

import java.util.HashMap;

public interface SearchFlightAvailability {

    void checkAvailability(HashMap<String, String> reservationDetails);
}
