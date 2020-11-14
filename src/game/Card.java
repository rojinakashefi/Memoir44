package game;
enum CardType {
    SingleGroupCommand ,
    TwoGroupCommand,
    ThreeGroupCommand,
    FourGroupCommand,
    ThreeUnitFromOneEquipmentCommand
}
public class Card {
    private CardType cardType;

    public Card() {
    }

    public Card(CardType cardType) {
        this.cardType = cardType;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
}