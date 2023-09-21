package org.example.geektrust.Services.implementation;

import org.example.geektrust.Constants.MetroCardConstants;
import org.example.geektrust.Entities.Card;
import org.example.geektrust.EntityManager.CardManager;
import org.example.geektrust.Services.CardService;

public class CardServiceImplementation implements CardService {

    private final CardManager cardManager;
    public CardServiceImplementation() {
        cardManager = CardManager.getInstance();
    }

    @Override
    public void addCard(String cardId, int balance) {
        cardManager.addNewCard(cardId, new Card(cardId, balance));
    }

    @Override
    public int transact(String cardId, int amount) {
        int cardBalance = cardManager.getCardBalance(cardId);
        int difference = cardBalance - amount;
        if(difference < 0){
            cardManager.updateCardBalance(cardId, MetroCardConstants.ZERO);
            return Math.abs(difference);
        }
        cardManager.updateCardBalance(cardId, difference);
        return MetroCardConstants.ZERO;
    }
}
