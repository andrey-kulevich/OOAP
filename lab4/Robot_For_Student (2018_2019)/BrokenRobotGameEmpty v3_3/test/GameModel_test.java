import brokenrobotgame.model.GameModel;
import brokenrobotgame.model.navigation.Direction;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class GameModel_test {

    /** ������ ���� */
    @Test
    void startGame() {

        GameModel model = new GameModel();
        model.start();

        Assert.assertTrue(model.robot() != null && model.field() != null);
    }

    /** ����� ������ ������� ������� */
    @Test
    void robotReachedTargetPos() {

        GameModel model = new GameModel();
        model.start();

        model.robot().makeMove(Direction.west());
        Assert.assertEquals(model.robot().position(), model.targetPosition());
    }

    /** � ������ �������� ����� */
    @Test
    void endOfChargeInRobot() {

        GameModel model = new GameModel();
        model.start();
        model.robot().reduceCharge(10);
        Assert.assertEquals(0, model.robot().amountOfCharge());
    }
}
