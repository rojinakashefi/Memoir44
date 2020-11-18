package game;

import equipment.EquipmentGroup;
import java.util.ArrayList;

/**
 * each game unit has its own natural entites which are enums
 */
enum NaturalEntities {
    Land,
    Hill,
    Forest,
    River,
    Bridge,
    CityOrVillage,
    Shelter
}
/**
 * each gameunit of game is made of equipments group and has its own natural  entities
 * @author kashefi
 * @version 0.0
 */
public class GameUnit {
    //equipment of game unit
    private EquipmentGroup equipmentGroup;
    //type of unit
    private NaturalEntities unitType;
    //player in unit
    private String playerName;

    /**
     * @param unitType as type of game unit type
     */
    public GameUnit(NaturalEntities unitType) {
        this.unitType = unitType;
    }

    /**
     *getting eqyipment group of game unit if there is no equipment group make one
     * @return equipment group of unit
     */
    public EquipmentGroup getEquipmentGroup() {
        if (equipmentGroup == null){
            equipmentGroup = new EquipmentGroup(new ArrayList<>());
        }
        return equipmentGroup;
    }

    /**
     * setting equipment group
     * @param equipmentGroup as equipment group we want to set in our gameunit
     */
    public void setEquipmentGroup(EquipmentGroup equipmentGroup) {
        this.equipmentGroup = equipmentGroup;
    }

    /**
     * getting unit type
     * @return unit type
     */
    public NaturalEntities getUnitType() {
        return unitType;
    }

    /**
     * getting name of player
     * @return name of player
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * setting player in gameunit
     * @param playerName as the player of each game unit
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * setting unit type of game unit
     * @param unitType of game unit
     */
    public void setUnitType(NaturalEntities unitType) {
        this.unitType = unitType;
    }
}

