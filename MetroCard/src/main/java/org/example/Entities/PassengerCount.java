package org.example.Entities;

import org.example.Enums.PassengerType;

public class PassengerCount implements Comparable<PassengerCount>{
    private final String passengerType;
    private final int count;

    public PassengerCount(String passengerType, int count){
        this.passengerType = passengerType;
        this.count = count;
    }

    public int getCount(){
        return this.count;
    }

    public String getPassengerType(){
        return this.passengerType;
    }


    @Override
    public int compareTo(PassengerCount o) {
        return o.getCount() != this.getCount() ?
                o.getCount() - this.getCount() :
                this.getPassengerType().charAt(0) - o.getPassengerType().charAt(0);
    }
}
