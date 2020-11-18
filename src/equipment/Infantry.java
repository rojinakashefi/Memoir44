package equipment;

import java.util.ArrayList;
import java.util.List;

/**
 * Infantry class
 * @author kashefi
 * @version 0.0
 */
public class Infantry extends Equipment{
    //number of infantires in thier equipment group is 4
    private final static int numberOfEquipments = 4;

     /**
     * make equipmentgroup for infantry of player
     * @return equipment group of infantry made by infantries
     */
    public static EquipmentGroup initializeGroup() {
        List<Equipment> equipments = new ArrayList<>();
        for (int i = 0; i < numberOfEquipments; i++) {
            equipments.add(new Infantry());
        }
        return new EquipmentGroup(equipments);
    }
}