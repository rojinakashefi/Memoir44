package game;

import equipment.Equipment;

public class GameUnit {
    private Equipment equipment;
    private NaturalEntities unitType;

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
}