package equipment;

import java.util.ArrayList;
import java.util.List;

public class Infantry extends Equipment{
    private final static int numberOfEquipments = 4;

    public static EquipmentGroup initializeGroup() {
        List<Equipment> equipments = new ArrayList<>();
        for (int i = 0; i < numberOfEquipments; i++) {
            equipments.add(new Infantry());
        }
        return new EquipmentGroup(equipments);
    }
}