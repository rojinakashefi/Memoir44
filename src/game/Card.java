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