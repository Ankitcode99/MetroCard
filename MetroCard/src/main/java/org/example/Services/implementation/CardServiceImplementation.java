package org.example.Services.implementation;

import org.example.Constants.MetroCardConstants;
import org.example.EntityManager.CardManager;
import org.example.Services.CardService;

public class CardServiceImplementation implements CardService {

    private final CardManager cardManager;
    public CardServiceImplementation() {
        cardManager = CardManager.getInstance();
    }

    @Override
    public void addCard(String cardId, int balance) {
        cardManager.addNewCard(cardId, balance);
    }

    @Override
    public int transact(String cardId, int amount) {
        int cardBalance = cardManager.getCardBalance(cardId);
        int difference = cardBalance - amount;
        if(difference < 0){
            cardManager.updateCardBalance(cardId, MetroCardConstants.ZERO);
            return difference;
        }
        cardManager.updateCardBalance(cardId, difference);
        return MetroCardConstants.ZERO;
    }
}
