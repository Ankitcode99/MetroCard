package org.example.geektrust.Entities;

import org.example.geektrust.Enums.PassengerType;
import org.example.geektrust.Enums.Station;

public class Journey {
    private String cardId;
    private PassengerType passengerType;
    private Station sourceStation;

    public Journey(String cardId, PassengerType passengerType, Station sourceStation){
        this.cardId = cardId;
        this.passengerType = passengerType;
        this.sourceStation = sourceStation;
    }

    public String getCardId() {
        return this.cardId;
    }

    public PassengerType getPassengerType(){
        return this.passengerType;
    }

    public Station getSourceStation(){
        return this.sourceStation;
    }
}
