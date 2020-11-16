package game;

import equipment.Equipment;
import equipment.EquipmentGroup;

public class GameUnit {
    private EquipmentGroup equipmentGroup;
    private NaturalEntities unitType;
    private String playerName;

    public GameUnit(NaturalEntities unitType) {
        this.unitType = unitType;
    }

    public EquipmentGroup getEquipmentGroup() {
        return equipmentGroup;
    }

    public void setEquipmentGroup(EquipmentGroup equipmentGroup) {
        this.equipmentGroup = equipmentGroup;
    }

    public NaturalEntities getUnitType() {
        return unitType;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setUnitType(NaturalEntities unitType) {
        this.unitType = unitType;
    }
}
