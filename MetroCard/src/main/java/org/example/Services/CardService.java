package org.example.Services;

public interface CardService {
    public void addCard(String cardId, int balance);
    public int transact(String cardId, int amount);
}
