package org.example.Services.implementation;

import org.example.Constants.MetroCardConstants;
import org.example.Entities.Journey;
import org.example.EntityManager.StationManager;
import org.example.Services.CardService;
import org.example.Services.JourneyService;

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
        if(passengerJourneyMap.containsKey(journey.getCardId())) {
            computeFare(journey, true);
            passengerJourneyMap.remove(journey.getCardId());
        }else{
            computeFare(journey, false);
            passengerJourneyMap.put(journey.getCardId(), journey.getSourceStation().name());
        }
    }

    private void computeFare(Journey journey, boolean isReturnJourney) {

        int baseFare = journey.getPassengerType().getVal();
        if(isReturnJourney)
            baseFare /= MetroCardConstants.DISCOUNT_FACTOR;

        int discount = isReturnJourney ? baseFare/MetroCardConstants.DISCOUNT_FACTOR : 0;

        int journeyCollection = baseFare;
        int addedAmount = cardService.transact(journey.getCardId(), baseFare);
        if(addedAmount > 0){
            journeyCollection += (int) (addedAmount * MetroCardConstants.SERVICE_CHARGE);
        }

        stationManager.updateStationMetrics(journey, journeyCollection, discount);
    }
}
