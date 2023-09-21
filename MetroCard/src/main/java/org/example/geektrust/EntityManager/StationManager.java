package org.example.geektrust.EntityManager;

import org.example.geektrust.Entities.Journey;
import org.example.geektrust.Enums.Station;


import java.util.HashMap;
import java.util.Map;

import static org.example.geektrust.Constants.MetroCardConstants.ONE;
import static org.example.geektrust.Constants.MetroCardConstants.ZERO;

public class StationManager {

    private static final StationManager INSTANCE = new StationManager();

    private Map<String, Integer> stationCollectionMap;
    private Map<String, Integer> stationDiscountMap;
    private Map<String, Map<String, Integer>> stationPassengerTypeMap;

    private StationManager(){
        stationCollectionMap = new HashMap<>();
        stationDiscountMap = new HashMap<>();
        stationPassengerTypeMap = new HashMap<>();
        for(Station station: Station.values()){
            stationCollectionMap.put(station.name(), ZERO);
            stationDiscountMap.put(station.name(), ZERO);
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
        if(passengerTypeCountMap.containsKey(passengerType)){
            passengerTypeCountMap.put(passengerType, passengerTypeCountMap.get(passengerType)+1);
        }else{
            passengerTypeCountMap.put(passengerType, ONE);
        }

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
