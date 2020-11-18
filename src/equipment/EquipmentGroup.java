package equipment;

import java.util.List;

/**
 * EquipmentGroup consists of equipments of each group
 * @author kashefi
 * @version 0.0
 */
public class EquipmentGroup {
    private List<Equipment> equipment;

    /**
     * setting equipments
     * @param equipment as list of equipment in each group
     */
    public EquipmentGroup(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    /**
     * getting equipment group
     * @return list of equipment group
     */
    public List<Equipment> getEquipment() {
        return equipment;
    }

    /**
     * setting list of equipments in equipment of groups
     * @param equipment as list we want to set
     */
    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }
}
