package org.example.EntityManager;

import java.util.HashMap;
import java.util.Map;

public class CardManager {

    public static final CardManager INSTANCE = new CardManager();
    private final Map<String, Integer> cardsMap;

    private CardManager() {
        cardsMap = new HashMap<>();
    }

    public static CardManager getInstance(){
        return INSTANCE;
    }

    public void addNewCard(String cardId, int balance) {
        cardsMap.put(cardId, balance);
    }

    public int getCardBalance(String cardId) {
        return cardsMap.get(cardId);
    }

    public void updateCardBalance(String cardId, int newBalance){
        cardsMap.put(cardId, newBalance);
    }
}
