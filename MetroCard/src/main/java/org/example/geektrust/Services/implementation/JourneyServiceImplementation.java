package org.example.geektrust.Services.implementation;

import org.example.geektrust.Constants.MetroCardConstants;
import org.example.geektrust.Entities.Journey;
import org.example.geektrust.EntityManager.StationManager;
import org.example.geektrust.Services.CardService;
import org.example.geektrust.Services.JourneyService;

import java.util.HashMap;
import java.util.Map;

public class JourneyServiceImplementation implements JourneyService {

    private final StationManager stationManager;
    private final CardService cardService;
    private final Map<String, String> passengerJourneyMap;

    public JourneyServiceImplementation() {
        stationManager = StationManager.getInstance();
        cardService = new CardServiceImplementation();
        passengerJourneyMap = new HashMap<>();
    }


    @Override
    public void checkInPassenger(Journey journey) {
        boolean isReturnJourney = passengerJourneyMap.containsKey(journey.getCardId());
        computeFare(journey, isReturnJourney);
        if(isReturnJourney) {
            passengerJourneyMap.remove(journey.getCardId());
        }else{
            passengerJourneyMap.put(journey.getCardId(), journey.getSourceStation().name());
        }
    }

    private void computeFare(Journey journey, boolean isReturnJourney) {

        int baseFare = journey.getPassengerType().getVal();
        int discount = isReturnJourney ? baseFare/MetroCardConstants.TWO : 0;
        if(isReturnJourney)
            baseFare /= MetroCardConstants.TWO;

        int journeyCollection = baseFare;
        int addedAmount = cardService.transact(journey.getCardId(), baseFare);
        if(addedAmount > 0){
            journeyCollection += (int) (addedAmount * MetroCardConstants.SERVICE_CHARGE);
        }

        stationManager.updateStationMetrics(journey, journeyCollection, discount);
    }
}
