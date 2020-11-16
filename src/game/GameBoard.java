package game;

import equipment.Artillery;
import equipment.Equipment;
import equipment.Infantry;
import equipment.Tank;

import java.util.ArrayList;
import java.util.List;

//naghsheye bazi
public class GameBoard {

    public static List<List<GameUnit>> board;
    private static final int numberOfRows = 9;

    public void print(){
        for (int j = 0; j < board.size(); j++) {
            int numberOfCols = 13;
            if (j%2 == 1){
                numberOfCols = 12;
            }
            for (int i = 0; i < numberOfCols; i++) {
                if (j%2 == 1 && i==0) {
                    System.out.print("     ");
                }
                System.out.print("  * ** *  ");
            }
            System.out.println();
            for (int i = 0; i < numberOfCols; i++) {
                if (j%2 == 1 && i==0) {
                    System.out.print("     ");
                }
                System.out.print(" *      * ");
            }
            System.out.println();
            for (int i = 0; i < numberOfCols; i++) {
                if (j%2 == 1 && i==0) {
                    System.out.print("     ");
                }
                System.out.print("* ");
                int len = printUnit(board.get(j).get(i));
                for (int k = 0; k < 7-len; k++) {
                    System.out.print(" ");
                }
                System.out.print("*");
            }
            System.out.println();
            for (int i = 0; i < numberOfCols; i++) {
                if (j%2 == 1 && i==0) {
                    System.out.print("     ");
                }
                System.out.print(" *      * ");
            }
            System.out.println();
            for (int i = 0; i < numberOfCols; i++) {
                if (j%2 == 1 && i==0) {
                    System.out.print("     ");
                }
                System.out.print("  * ** *  ");
            }
            System.out.println();
        }

    }


//    public void printGameBoard() {
//        for (int i = 0; i < board.size(); i++) {
//            List<GameUnit> units = board.get(i);
//            for (int j = 0; j < units.size(); j++) {
//                printUnit(units.get(j));
//            }
//            System.out.println();
//        }
//    }

    private int printUnit(GameUnit gameUnit) {
        String str = "";
        switch (gameUnit.getUnitType()) {
            case Hill:
                str += "H";
                break;
            case Bridge:
                str += "B";
                break;
            case CityOrVillage:
                str += "C";
                break;
            case Forest:
                str += "F";
                break;
            case Land:
                str += "L";
                break;
            case River:
                str += "R";
                break;
            case Shelter:
                str += "S";
                break;
        }

        if (gameUnit.getEquipmentGroup() != null && gameUnit.getEquipmentGroup().getEquipment() != null)
            str += getEquipmentInfo(gameUnit);

        System.out.print(str);
        return str.length();
    }

    private String getEquipmentInfo(GameUnit unit) {
        String str = unit.getPlayerName().substring(0, 2);
        Equipment equipment = unit.getEquipmentGroup().getEquipment().get(0);
        str += String.valueOf(unit.getEquipmentGroup().getEquipment().size());
        if (equipment instanceof Tank) {
            str += "T";
        } else if (equipment instanceof Infantry) {
            str += "I";
        } else if (equipment instanceof Artillery) {
            str += "A";
        }
        return str;
    }

    public void initializeBoard() {
        //9*13 game baord
        this.createEmptyBoard();
        //naghsheye avalyie pishfarz
        this.createDefaultMap();
    }

    private void createEmptyBoard() {
        board = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            List<GameUnit> units = new ArrayList<>();
            int numOfCols = 13;
            if (i % 2 == 1) {
                numOfCols = 12;
            }
            for (int j = 0; j < numOfCols; j++) {
                units.add(new GameUnit(NaturalEntities.Land));
            }
            board.add(units);
        }
    }

    private void createDefaultMap() {
        board.get(0).get(0).setUnitType(NaturalEntities.Hill);
        board.get(0).get(1).setUnitType(NaturalEntities.Hill);
        board.get(0).get(3).setUnitType(NaturalEntities.Shelter);
        board.get(0).get(4).setUnitType(NaturalEntities.Bridge);

        board.get(1).get(1).setUnitType(NaturalEntities.River);
        board.get(1).get(2).setUnitType(NaturalEntities.River);
        board.get(1).get(3).setUnitType(NaturalEntities.River);
        board.get(1).get(4).setUnitType(NaturalEntities.Shelter);
        board.get(1).get(6).setUnitType(NaturalEntities.Hill);

        board.get(2).get(0).setUnitType(NaturalEntities.CityOrVillage);
        board.get(2).get(1).setUnitType(NaturalEntities.River);
        board.get(2).get(6).setUnitType(NaturalEntities.Hill);
        board.get(2).get(9).setUnitType(NaturalEntities.Forest);
        board.get(2).get(12).setUnitType(NaturalEntities.Forest);

        board.get(3).get(0).setUnitType(NaturalEntities.Bridge);
        board.get(3).get(1).setUnitType(NaturalEntities.Forest);
        board.get(3).get(3).setUnitType(NaturalEntities.Forest);
        board.get(3).get(11).setUnitType(NaturalEntities.Forest);

        board.get(4).get(0).setUnitType(NaturalEntities.River);
        board.get(4).get(1).setUnitType(NaturalEntities.Forest);
        board.get(4).get(5).setUnitType(NaturalEntities.Hill);
        board.get(4).get(6).setUnitType(NaturalEntities.CityOrVillage);
        board.get(4).get(8).setUnitType(NaturalEntities.Forest);
        board.get(4).get(10).setUnitType(NaturalEntities.CityOrVillage);
        board.get(4).get(11).setUnitType(NaturalEntities.Hill);
        board.get(4).get(12).setUnitType(NaturalEntities.Forest);

        board.get(5).get(3).setUnitType(NaturalEntities.Forest);
        board.get(5).get(4).setUnitType(NaturalEntities.Hill);
        board.get(5).get(10).setUnitType(NaturalEntities.Hill);
        board.get(5).get(11).setUnitType(NaturalEntities.Forest);

        board.get(6).get(2).setUnitType(NaturalEntities.CityOrVillage);
        board.get(6).get(7).setUnitType(NaturalEntities.Forest);
        board.get(6).get(8).setUnitType(NaturalEntities.Forest);

        board.get(7).get(3).setUnitType(NaturalEntities.Forest);
        board.get(7).get(4).setUnitType(NaturalEntities.Forest);
        board.get(7).get(8).setUnitType(NaturalEntities.Forest);

        board.get(8).get(11).setUnitType(NaturalEntities.CityOrVillage);
    }

    public void initializeEquipments(Player axisPlayer, Player alliedPlayer) {
        //--Axis Equipments
        board.get(0).get(0).setPlayerName("Axis");
        board.get(0).get(0).setEquipmentGroup(Tank.initializeGroupForAxis());

        board.get(0).get(5).setPlayerName("Axis");
        board.get(0).get(5).setEquipmentGroup(Tank.initializeGroupForAxis());

        board.get(0).get(8).setPlayerName("Axis");
        board.get(0).get(8).setEquipmentGroup(Tank.initializeGroupForAxis());

        board.get(0).get(11).setPlayerName("Axis");
        board.get(0).get(11).setEquipmentGroup(Tank.initializeGroupForAxis());

        board.get(1).get(5).setPlayerName("Axis");
        board.get(1).get(5).setEquipmentGroup(Tank.initializeGroupForAxis());

        board.get(1).get(10).setPlayerName("Axis");
        board.get(1).get(10).setEquipmentGroup(Tank.initializeGroupForAxis());

        board.get(0).get(1).setPlayerName("Axis");
        board.get(0).get(1).setEquipmentGroup(Infantry.initializeGroup());

        board.get(0).get(2).setPlayerName("Axis");
        board.get(0).get(2).setEquipmentGroup(Infantry.initializeGroup());

        board.get(0).get(7).setPlayerName("Axis");
        board.get(0).get(7).setEquipmentGroup(Infantry.initializeGroup());

        board.get(0).get(10).setPlayerName("Axis");
        board.get(0).get(10).setEquipmentGroup(Infantry.initializeGroup());

        board.get(0).get(12).setPlayerName("Axis");
        board.get(0).get(12).setEquipmentGroup(Infantry.initializeGroup());

        board.get(1).get(4).setPlayerName("Axis");
        board.get(1).get(4).setEquipmentGroup(Infantry.initializeGroup());

        board.get(1).get(8).setPlayerName("Axis");
        board.get(1).get(8).setEquipmentGroup(Infantry.initializeGroup());

        //--Allied Equipments
        board.get(8).get(0).setPlayerName("Allied");
        board.get(8).get(0).setEquipmentGroup(Tank.initializeGroupForAllied());

        board.get(8).get(1).setPlayerName("Allied");
        board.get(8).get(1).setEquipmentGroup(Tank.initializeGroupForAllied());

        board.get(8).get(12).setPlayerName("Allied");
        board.get(8).get(12).setEquipmentGroup(Tank.initializeGroupForAllied());

        board.get(7).get(1).setPlayerName("Allied");
        board.get(7).get(1).setEquipmentGroup(Artillery.initializeGroup());

        board.get(7).get(5).setPlayerName("Allied");
        board.get(7).get(5).setEquipmentGroup(Artillery.initializeGroup());

        board.get(4).get(1).setPlayerName("Allied");
        board.get(4).get(1).setEquipmentGroup(Infantry.initializeGroup());

//        board.get(4).get(6).setPlayerName("Allied");
//        board.get(4).get(6).setEquipmentGroup(Infantry.initializeGroup());

        board.get(4).get(8).setPlayerName("Allied");
        board.get(4).get(8).setEquipmentGroup(Infantry.initializeGroup());

        board.get(4).get(11).setPlayerName("Allied");
        board.get(4).get(11).setEquipmentGroup(Infantry.initializeGroup());

        board.get(5).get(3).setPlayerName("Allied");
        board.get(5).get(3).setEquipmentGroup(Infantry.initializeGroup());

        board.get(6).get(7).setPlayerName("Allied");
        board.get(6).get(7).setEquipmentGroup(Infantry.initializeGroup());

        board.get(7).get(0).setPlayerName("Allied");
        board.get(7).get(0).setEquipmentGroup(Infantry.initializeGroup());

        board.get(7).get(9).setPlayerName("Allied");
        board.get(7).get(9).setEquipmentGroup(Infantry.initializeGroup());

        board.get(8).get(8).setPlayerName("Allied");
        board.get(8).get(8).setEquipmentGroup(Infantry.initializeGroup());
    }
}
