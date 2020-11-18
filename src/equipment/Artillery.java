package equipment;

import java.util.ArrayList;
import java.util.List;

/**
 *Artillery class
 * @author kashefi
 * @version 0.0
 */
//توپ خانه
public class Artillery extends Equipment{
    //each equipmentgroup of Artillery has 2 Artillery
    private final static int numberOfEquipments = 2;

    /**
     * initializing equipment group
     * @return Equipment group of Artilleries which is made of 2 artillary
     */
    public static EquipmentGroup initializeGroup() {
        List<Equipment> equipments = new ArrayList<>();
        for (int i = 0; i < numberOfEquipments; i++) {
            equipments.add(new Artillery());
        }
        return new EquipmentGroup(equipments);
    }
}