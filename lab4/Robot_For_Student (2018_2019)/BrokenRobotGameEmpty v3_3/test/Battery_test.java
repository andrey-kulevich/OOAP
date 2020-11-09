import brokenrobotgame.model.Battery;
import brokenrobotgame.model.GameField;
import brokenrobotgame.model.navigation.CellPosition;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class Battery_test {

    /** ���������� ��������� ��� ���� */
    @Test
    void invalidNullParamInConstructor() {

        GameField field = new GameField();
        Battery battery = new Battery(field, 1, 1);
        battery.setPosition(null);
        Assert.assertNull(battery.position());
    }

    /** ������� ��� ������ ������ ���������� */
    @Test
    void anotherBatteryOnThisPosition() {

        GameField field = new GameField();
        Battery battery1 = new Battery(field, 1, 1);
        battery1.setPosition(new CellPosition(1,1));
        field.addBattery(battery1.position(), battery1);
        Battery battery2 = new Battery(field, 1, 1);
        boolean isOk = battery2.setPosition(new CellPosition(1,1));
        Assert.assertFalse(isOk);
    }

}
