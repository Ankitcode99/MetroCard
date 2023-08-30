package org.example.EntityManager;

import org.example.Entities.Journey;
import org.example.Enums.Station;

import java.util.HashMap;
import java.util.Map;

public class StationManager {

    private static final StationManager INSTANCE = new StationManager();

    private final Map<String, Integer> stationCollectionMap;
    private final Map<String, Integer> stationDiscountMap;
    private final Map<String, Map<String, Integer>> stationPassengerTypeMap;

    private StationManager(){
        stationCollectionMap = new HashMap<>();
        stationDiscountMap = new HashMap<>();
        stationPassengerTypeMap = new HashMap<>();
        for(Station station: Station.values()){
            stationCollectionMap.put(station.name(), 0);
            stationDiscountMap.put(station.name(), 0);
            stationPassengerTypeMap.put(station.name(), new HashMap<>());
        }
    }

    public static StationManager getInstance(){
        return INSTANCE;
    }

    public void updateStationMetrics(Journey journey, int collectionAmount, int discount){
        String stationName = journey.getSourceStation().name();
        String passengerType = journey.getPassengerType().name();

        stationCollectionMap.put(stationName, stationCollectionMap.get(stationName) + collectionAmount);
        if(discount > 0){
            stationDiscountMap.put(stationName, stationDiscountMap.get(stationName) + discount);
        }

        Map<String, Integer> passengerTypeCountMap = stationPassengerTypeMap.get(stationName);
        passengerTypeCountMap.put(passengerType, passengerTypeCountMap.get(passengerType)+1);

        stationPassengerTypeMap.put(stationName, passengerTypeCountMap);
    }

    public Map<String, Integer> getStationCollectionMap() {
        return this.stationCollectionMap;
    }

    public Map<String, Integer> getStationDiscountMap() {
        return this.stationDiscountMap;
    }

    public Map<String, Map<String, Integer>> getStationPassengerTypeMap() {
        return this.stationPassengerTypeMap;
    }
}
