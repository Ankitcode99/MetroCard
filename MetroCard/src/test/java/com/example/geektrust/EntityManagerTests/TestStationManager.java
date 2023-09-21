package com.example.geektrust.EntityManagerTests;

import org.example.geektrust.Entities.Journey;
import org.example.geektrust.EntityManager.StationManager;
import org.example.geektrust.Enums.PassengerType;
import org.example.geektrust.Enums.Station;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

import static org.example.geektrust.Constants.MetroCardConstants.ONE;
import static org.example.geektrust.Constants.MetroCardConstants.TWO;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestStationManager {

    private StationManager stationManager;

    @BeforeEach
    public void setUp() {
        stationManager = StationManager.getInstance();
    }

    @Test
    public void testUpdateStationMetrics() {
        Journey journey = new Journey("12345", PassengerType.ADULT, Station.CENTRAL);
        int collectionAmount = 100;
        int discount = 10;

        stationManager.updateStationMetrics(journey, collectionAmount, discount);

        Map<String, Integer> collectionMap = stationManager.getStationCollectionMap();
        Map<String, Integer> discountMap = stationManager.getStationDiscountMap();
        Map<String, Map<String, Integer>> passengerTypeMap = stationManager.getStationPassengerTypeMap();

        assertEquals(collectionAmount, collectionMap.get(Station.CENTRAL.name()));
        assertEquals(discount, discountMap.get(Station.CENTRAL.name()));
        assertEquals(1, passengerTypeMap.get(Station.CENTRAL.name()).get(PassengerType.ADULT.name()));
    }

    @Test
    public void testGetStationMetrics() {
        Map<String, Integer> collectionMap = stationManager.getStationCollectionMap();
        Map<String, Integer> discountMap = stationManager.getStationDiscountMap();
        Map<String, Map<String, Integer>> passengerTypeMap = stationManager.getStationPassengerTypeMap();

        assertNotNull(collectionMap);
        assertNotNull(discountMap);
        assertNotNull(passengerTypeMap);

        assertTrue(collectionMap.containsKey(Station.CENTRAL.name()));
        assertTrue(discountMap.containsKey(Station.CENTRAL.name()));
        assertTrue(passengerTypeMap.containsKey(Station.CENTRAL.name()));
    }

    @Test
    public void testGetInstance() {
        StationManager instance1 = StationManager.getInstance();
        StationManager instance2 = StationManager.getInstance();

        assertSame(instance1, instance2); // Ensure the same instance is returned
    }

    @AfterAll
    public void resetStationManager() {
        Journey fakeJourney = new Journey("12345", PassengerType.ADULT, Station.CENTRAL);

        stationManager.updateStationMetrics(fakeJourney, -(ONE)*(PassengerType.ADULT.getVal())/TWO, -10);
    }

}
