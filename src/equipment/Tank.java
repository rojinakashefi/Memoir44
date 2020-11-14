package equipment;

import java.util.ArrayList;
import java.util.List;

public class Tank extends Equipment {
    private final static int numberOfEquipmentsForAxis = 4;
    private final static int numberOfEquipmentsForAllied = 3;

    public static EquipmentGroup initializeGroupForAxis() {
        List<Equipment> equipments = new ArrayList<>();
        for (int i = 0; i < numberOfEquipmentsForAxis; i++) {
            equipments.add(new Tank());
        }
        return new EquipmentGroup(equipments);
    }

    public static EquipmentGroup initializeGroupForAllied() {
        List<Equipment> equipments = new ArrayList<>();
        for (int i = 0; i < numberOfEquipmentsForAllied; i++) {
            equipments.add(new Tank());
        }
        return new EquipmentGroup(equipments);
    }
}
