package equipment;

import java.util.ArrayList;
import java.util.List;

/**
 * Tank class
 * @author kashefi
 * @version 0.0
 */
public class Tank extends Equipment {
    private final static int numberOfEquipmentsForAxis = 4;
    private final static int numberOfEquipmentsForAllied = 3;

    /**
     * make equipmentgroup for tanks
     * @return equipmentgroup of tank for axis player made by tanks
     */
    public static EquipmentGroup initializeGroupForAxis() {
        List<Equipment> equipments = new ArrayList<>();
        for (int i = 0; i < numberOfEquipmentsForAxis; i++) {
            equipments.add(new Tank());
        }
        return new EquipmentGroup(equipments);
    }

    /**
     * make equipmentgroup for tanks
     * @return equipment group of tank for allied made by tanks
     */
    public static EquipmentGroup initializeGroupForAllied() {
        List<Equipment> equipments = new ArrayList<>();
        for (int i = 0; i < numberOfEquipmentsForAllied; i++) {
            equipments.add(new Tank());
        }
        return new EquipmentGroup(equipments);
    }
}
