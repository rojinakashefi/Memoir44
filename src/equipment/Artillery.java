package equipment;

import java.util.ArrayList;
import java.util.List;

public class Artillery extends Equipment{
    private final static int numberOfEquipments = 2;

    public static EquipmentGroup initializeGroup() {
        List<Equipment> equipments = new ArrayList<>();
        for (int i = 0; i < numberOfEquipments; i++) {
            equipments.add(new Artillery());
        }
        return new EquipmentGroup(equipments);
    }
}