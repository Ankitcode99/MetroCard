package org.example.geektrust.EntityManager;

import org.example.geektrust.Entities.Card;

import java.util.HashMap;
import java.util.Map;

public class CardManager {

    public static final CardManager INSTANCE = new CardManager();
    private final Map<String, Card> cardsMap;

    private CardManager() {
        cardsMap = new HashMap<>();
    }

    public static CardManager getInstance(){
        return INSTANCE;
    }

    public void addNewCard(String cardId, Card card) {
        cardsMap.put(cardId, card);
    }

    public int getCardBalance(String cardId) {
        return (cardsMap.get(cardId)).getBalance();
    }

    public void updateCardBalance(String cardId, int newBalance){
        (cardsMap.get(cardId)).setBalance(newBalance);
    }

    public Card getCardById(String cardId) {
        return cardsMap.get(cardId);
    }
}
