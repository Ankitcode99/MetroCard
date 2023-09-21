package com.example.geektrust.EntityManagerTests;

import org.example.geektrust.Entities.Card;
import org.example.geektrust.EntityManager.CardManager;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestCardManager{

    private CardManager cardManager;

    @BeforeEach
    public void setUp() {
        cardManager = CardManager.getInstance();
    }

    @Test
    public void testAddNewCard() {
        Card card = new Card("1234", 100);
        cardManager.addNewCard(card.getId(), card);
        assertEquals(card, cardManager.getCardById(card.getId()));
    }

    @Test
    public void testGetCardBalance() {
        Card card = new Card("5678", 200);
        cardManager.addNewCard(card.getId(), card);
        int balance = cardManager.getCardBalance(card.getId());
        assertEquals(card.getBalance(), balance);
    }

    @Test
    public void testUpdateCardBalance() {
        Card card = new Card("9101", 150);
        cardManager.addNewCard(card.getId(), card);

        int newBalance = 200;
        cardManager.updateCardBalance(card.getId(), newBalance);

        int updatedBalance = cardManager.getCardBalance(card.getId());
        assertEquals(newBalance, updatedBalance);
    }

    @Test
    public void testGetInstance() {
        CardManager instance1 = CardManager.getInstance();
        CardManager instance2 = CardManager.getInstance();

        assertEquals(instance1, instance2); // Ensure the same instance is returned
    }

    @Test
    public void testGetCardBalanceInvalidCardId() {
        assertThrows(NullPointerException.class, () -> cardManager.getCardBalance(null));
        assertThrows(NullPointerException.class, () -> cardManager.getCardBalance("nonExistentCardId"));
    }

    @Test
    public void testUpdateCardBalanceInvalidCardId() {
        assertThrows(NullPointerException.class, () -> cardManager.updateCardBalance(null, 150));
        assertThrows(NullPointerException.class, () -> cardManager.updateCardBalance("nonExistentCardId", 150));
    }
}

