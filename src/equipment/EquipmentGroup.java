package equipment;

import java.util.List;

public class EquipmentGroup {
    private List<Equipment> equipment;

    public EquipmentGroup(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }
}
