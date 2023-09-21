package org.example.geektrust.Entities;

public class Card {
    private final String id;
    private int balance;

    public Card(String id, int balance){
        this.id = id;
        this.balance = balance;
    }

    public String getId(){
        return this.id;
    }

    public int getBalance(){
        return this.balance;
    }

    public void setBalance(int balance){
        this.balance = balance;
    }
}
