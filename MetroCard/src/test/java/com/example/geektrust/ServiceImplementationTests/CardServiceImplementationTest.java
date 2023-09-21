package com.example.geektrust.ServiceImplementationTests;

import org.example.geektrust.Entities.Card;
import org.example.geektrust.Entities.Journey;
import org.example.geektrust.EntityManager.CardManager;
import org.example.geektrust.Services.implementation.CardServiceImplementation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardServiceImplementationTest {

    private CardServiceImplementation cardService;
    private CardManager cardManager;

    @BeforeEach
    public void setUp() {
        cardService = new CardServiceImplementation();
        cardManager = CardManager.getInstance();
    }

    @Test
    public void testAddCard() {
        String cardId = "12345";
        int balance = 100;

        cardService.addCard(cardId, balance);

        Assertions.assertEquals((cardManager.getCardById(cardId)).getBalance(), balance);
    }

    @Test
    public void testTransactSufficientBalance() {
        String cardId = "12345";
        int initialBalance = 200;
        int amount = 150;

        cardManager.addNewCard(cardId, new Card(cardId, initialBalance));

        Assertions.assertEquals(cardManager.getCardBalance(cardId), initialBalance);

        int remainingBalance = cardService.transact(cardId, amount);

        assertEquals(0, remainingBalance);
    }

    @Test
    public void testTransactInsufficientBalance() {
        String cardId = "12345";
        int initialBalance = 100;
        int amount = 150;

        Assertions.assertEquals(cardManager.getCardBalance(cardId), initialBalance);

        int remainingBalance = cardService.transact(cardId, amount);

        assertEquals(50, remainingBalance); // Remaining balance will be negative but returned as positive
        assertEquals(cardManager.getCardById(cardId).getBalance(), 0);
    }


}
