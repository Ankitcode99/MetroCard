package org.example.geektrust.Services.implementation;

import org.example.geektrust.Entities.PassengerCount;
import org.example.geektrust.EntityManager.StationManager;
import org.example.geektrust.Enums.Station;
import org.example.geektrust.Services.PrintingService;

import java.util.Map;
import java.util.PriorityQueue;

public class PrintingServiceImplementation implements PrintingService {

    private StationManager stationManager;

    public PrintingServiceImplementation(){
        stationManager = StationManager.getInstance();
    }

    @Override
    public void printSummary() {
        Map<String, Integer> stationCollectionMap = stationManager.getStationCollectionMap();
        Map<String, Integer> stationDiscountMap = stationManager.getStationDiscountMap();
        Map<String, Map<String, Integer>> stationPassengerTypeMap = stationManager.getStationPassengerTypeMap();

        for(Station station: Station.values()){
            System.out.println("TOTAL_COLLECTION " + station.name() + " " + stationCollectionMap.get(station.name()) + " " + stationDiscountMap.get(station.name()));
            System.out.println("PASSENGER_TYPE_SUMMARY");

            PriorityQueue<PassengerCount> passengerCounts = createPassengerCountFromMap(stationPassengerTypeMap.get(station.name()));
            while (!passengerCounts.isEmpty()) {
                PassengerCount passengerCount = passengerCounts.poll();
                System.out.println(passengerCount.getPassengerType() + " " + passengerCount.getCount());
            }
        }

    }

    private PriorityQueue<PassengerCount> createPassengerCountFromMap(Map<String, Integer> passengerCountMap) {
        PriorityQueue<PassengerCount> passengerCounts = new PriorityQueue<>();
        for(Map.Entry<String, Integer> entry: passengerCountMap.entrySet()) {
            passengerCounts.add(new PassengerCount(entry.getKey(), entry.getValue()));
        }
        return passengerCounts;
    }
}
