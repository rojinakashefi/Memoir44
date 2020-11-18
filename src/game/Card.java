package game;

/**
 * showing different cards we have in a game
 */
enum CardType {
    SingleGroupCommand ,
    TwoGroupCommand,
    ThreeGroupCommand,
    FourGroupCommand,
    ThreeUnitFromOneEquipmentCommand
}

/**
 * cards of game info
 */
public class Card {
    private CardType cardType;

    public Card() {
    }

    /**
     * second constructor
     * @param cardType as to set what type of card we have want
     */
    public Card(CardType cardType) {
        this.cardType = cardType;
    }

    /**
     * getting cardtype
     * @return card type
     */
    public CardType getCardType() {
        return cardType;
    }

    /**
     * setting cardType
     * @param cardType as cardType we want to set
     */
    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    /**
     * @return title of cards(which shows what can a card do)
     */
    public String getTitle() {
        if (cardType == CardType.SingleGroupCommand)
            return "order 1 units";
        if (cardType == CardType.TwoGroupCommand)
            return "order 2 units";
        if (cardType == CardType.ThreeGroupCommand)
            return "order 3 units";
        if (cardType == CardType.FourGroupCommand)
            return "order 4 units";
        if (cardType == CardType.ThreeUnitFromOneEquipmentCommand)
            return "order 3 unit of one equipment";
        return "Nothing";
    }
}