import brokenrobotgame.model.GameModel;
import brokenrobotgame.model.Robots.Robot;
import brokenrobotgame.model.Robots.RobotPainter;
import brokenrobotgame.model.Robots.RobotRadiationMeter;
import brokenrobotgame.model.Robots.RobotTemperatureMeter;

import brokenrobotgame.model.navigation.CellPosition;
import brokenrobotgame.model.navigation.Direction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameModel_test {

    /** ”становить случайного робота */
    @Test
    void setRandomRobotToGame() {
        GameModel model = new GameModel();
        model.setRandomRobot();
        Assertions.assertTrue(model.robot().getClass() == Robot.class ||
                model.robot().getClass() == RobotPainter.class ||
                model.robot().getClass() == RobotRadiationMeter.class ||
                model.robot().getClass() == RobotTemperatureMeter.class);
    }

    /** —генерировать радиацию дл€ каждой клетки пол€ */
    @Test
    void generateRadiationToCells() {
        GameModel model = new GameModel();
        model.initRadiationPollutionToCells();
        boolean isRadiation = true;

        CellPosition pos = new  CellPosition(1,1);
        Direction direct = Direction.east();
        boolean hasNextInCol;
        boolean hasNextInRow;
        do {
            do {
                if (model.field().getRadiation(pos) == null) isRadiation = false;
                hasNextInRow = pos.hasNext(direct);
                if(pos.hasNext(direct)) pos = pos.next(direct);
            } while(hasNextInRow);

            direct = direct.opposite();
            hasNextInCol = pos.hasNext(Direction.south());
            if(pos.hasNext(Direction.south())) pos = pos.next(Direction.south());
        } while(hasNextInCol);

        Assertions.assertTrue(isRadiation);
    }

    /** —генерировать температуру дл€ каждой клетки пол€ */
    @Test
    void generateTemperatureToCells() {
        GameModel model = new GameModel();
        model.initTemperatureLevelsToCells();
        boolean isTemperature = true;

        CellPosition pos = new  CellPosition(1,1);
        Direction direct = Direction.east();
        boolean hasNextInCol;
        boolean hasNextInRow;
        do {
            do {
                if (model.field().getTemperature(pos) == null) isTemperature = false;
                hasNextInRow = pos.hasNext(direct);
                if(pos.hasNext(direct)) pos = pos.next(direct);
            } while(hasNextInRow);

            direct = direct.opposite();
            hasNextInCol = pos.hasNext(Direction.south());
            if(pos.hasNext(Direction.south())) pos = pos.next(Direction.south());
        } while(hasNextInCol);

        Assertions.assertTrue(isTemperature);
    }

}
