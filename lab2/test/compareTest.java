import reducedfractionapp.ReducedFraction;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class compareTest {
    /** Дроби равны*/
    @Test
    void fractionsAreEqual() {
        ReducedFraction f1 = new ReducedFraction(1, 2);
        ReducedFraction f2 = new ReducedFraction(1, 2);

        Assert.assertEquals(0, f1.compare(f2));
    }

    /** Левая дробь больше правой*/
    @Test
    void thisFractionIsGreater() {
        ReducedFraction f1 = new ReducedFraction(1, 2);
        ReducedFraction f2 = new ReducedFraction(3, 15);

        Assert.assertTrue(f1.compare(f2) > 0);
    }

    /** Левая дробь меньше правой*/
    @Test
    void thisFractionIsLess() {
        ReducedFraction f1 = new ReducedFraction(1, 2);
        ReducedFraction f2 = new ReducedFraction(6, 8);

        Assert.assertTrue(f1.compare(f2) < 0);
    }

    /** Обе дроби равны нулю*/
    @Test
    void bothFractionsIsZero() {
        ReducedFraction f1 = new ReducedFraction(0, 2);
        ReducedFraction f2 = new ReducedFraction(0, 4);

        Assert.assertEquals(0, f1.compare(f2));
    }

    /** Дроби одинаковые, одна из них отрицательная*/
    @Test
    void oneFractionsIsNegative() {
        ReducedFraction f1 = new ReducedFraction(-1, 2);
        ReducedFraction f2 = new ReducedFraction(1, 2);

        Assert.assertTrue(f1.compare(f2) < 0);
    }

    /** Обе дроби отрицательные и равны*/
    @Test
    void bothFractionsIsNegative() {
        ReducedFraction f1 = new ReducedFraction(-1, 2);
        ReducedFraction f2 = new ReducedFraction(-1, 2);

        Assert.assertEquals(0, f1.compare(f2));
    }
}