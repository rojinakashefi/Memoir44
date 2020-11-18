package game;

import equipment.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Player class
 * @author kashefi
 * @version 0.0
 */
public class Player {
    //name of player
    private String name;
    //list of cards each player have
    private List<Card> cards;
    //score of player
    private int score;


    //each player has list of equipment groups
    private List<EquipmentGroup> equipmentGroups;

    /**
     * @param name as player name
     */
    public Player(String name) {
        this.name = name;
        equipmentGroups = new ArrayList<>();
        cards = new ArrayList<>();
    }

    /**
     * getting name of player
     * @return name of player
     */
    public String getName() {
        return name;
    }

    /**
     * setting name of player
     * @param name of player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getting cards of player
     * @return cards as cards of each player have
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * setting cards of eachplayer
     * @param cards of each player
     */
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    /**
     * getting equipment of group
     * @return list of equipment group each player have
     */
    public List<EquipmentGroup> getEquipmentGroups() {
        return equipmentGroups;
    }

    /**
     * setting equipment group of player
     * @param equipmentGroups as equipments group each player have
     */
    public void setEquipmentGroups(List<EquipmentGroup> equipmentGroups) {
        this.equipmentGroups = equipmentGroups;
    }

    /**
     * setting equipments of allied
     */
    public void setAlliedEquipments() {
        //3 daste tank
        for (int i = 0; i < 3; i++) {
            equipmentGroups.add(Tank.initializeGroupForAllied());
        }
        //8 daste pyade nezam
        for (int i = 0; i < 8; i++) {
            equipmentGroups.add(Infantry.initializeGroup());
        }
        //2 daste toop
        for (int i = 0; i < 2; i++) {
            equipmentGroups.add(Artillery.initializeGroup());
        }
    }

    /**
     * setting equipment of axis
     */
    public void setAxisEquipments() {
        //6 daste tank
        for (int i = 0; i < 6; i++) {
            equipmentGroups.add(Tank.initializeGroupForAxis());
        }
        //7 daste pyade nezam
        for (int i = 0; i < 7; i++) {
            equipmentGroups.add(Infantry.initializeGroup());
        }
    }

    /**
     * print Available Cards
     */
    public void printAvailableCards(){
        List<Card> cards = getCards();
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(i+1 + "- "+ cards.get(i).getTitle());
        }
    }

    /**
     * removing a card from player list of cards
     * @param selectedCard as the card number we want to remove from player card
     * @return the card we want to remove
     */
    public Card remove(int selectedCard) {
        return cards.remove(selectedCard);
    }

    /**
     * getting score of player
     * @return score of player
     */
    public int getScore() {
        return score;
    }

    /**
     * setting score of player
     * @param score of player
     */
    public void setScore(int score) {
        this.score = score;
    }
}
