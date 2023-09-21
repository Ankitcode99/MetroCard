package com.example.geektrust.ServiceImplementationTests;

import org.example.geektrust.Entities.Journey;
import org.example.geektrust.EntityManager.StationManager;
import org.example.geektrust.Enums.PassengerType;
import org.example.geektrust.Enums.Station;
import org.example.geektrust.Services.CardService;
import org.example.geektrust.Services.implementation.CardServiceImplementation;
import org.example.geektrust.Services.implementation.JourneyServiceImplementation;
import org.junit.jupiter.api.*;

import static org.example.geektrust.Constants.MetroCardConstants.TWO;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JourneyServiceImplementationTest {
    private JourneyServiceImplementation journeyService;
    private StationManager stationManager;
    private CardService cardService;

    @BeforeEach
    public void setUp() {
        stationManager = StationManager.getInstance();
        cardService = new CardServiceImplementation();
        journeyService = new JourneyServiceImplementation();
    }

    @Order(1)
    @Test
    public void testCheckInPassengerNewJourney() {
        cardService.addCard("12345", 500);

        Journey journey = new Journey("12345", PassengerType.ADULT, Station.CENTRAL);

        journeyService.checkInPassenger(journey);

        Assertions.assertEquals(stationManager.getStationCollectionMap().get(Station.CENTRAL.name()), PassengerType.ADULT.getVal());
    }

    @Order(2)
    @Test
    public void testCheckInPassengerReturnJourney() {

        cardService.addCard("45454", 500);

        Journey journey1 = new Journey("45454", PassengerType.ADULT, Station.CENTRAL);
        Journey journey2 = new Journey("45454", PassengerType.ADULT, Station.AIRPORT);

        journeyService.checkInPassenger(journey1);
        journeyService.checkInPassenger(journey2);

        Assertions.assertEquals(stationManager.getStationCollectionMap().get(Station.CENTRAL.name()), PassengerType.ADULT.getVal()*TWO);

        Assertions.assertEquals(stationManager.getStationCollectionMap().get(Station.AIRPORT.name()), PassengerType.ADULT.getVal() / TWO);
    }

    @Order(3)
    @Test
    public void testCheckInPassengerServiceCharge() {
        Journey journey = new Journey("55555", PassengerType.ADULT, Station.CENTRAL);

        cardService.addCard("55555", 100);

        journeyService.checkInPassenger(journey);

        Assertions.assertEquals(stationManager.getStationCollectionMap().get(Station.CENTRAL.name()), 602);
    }
}
