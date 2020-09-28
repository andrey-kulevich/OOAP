import reducedfractionapp.ReducedFraction;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class toStringTest {
    /** Обычная дробь*/
    @Test
    void simpleFraction() {
        ReducedFraction f = new ReducedFraction(1, 2);
        String fToStr = f.toString();

        Assert.assertEquals(fToStr, "1/2");
    }

    /** Дробь равна нулю*/
    @Test
    void FractionIsZero() {
        ReducedFraction f = new ReducedFraction(0, 2);
        String fToStr = f.toString();

        Assert.assertEquals(fToStr, "0");
    }

    /** Дробь является целым числом*/
    @Test
    void FractionIsInteger() {
        ReducedFraction f = new ReducedFraction(5, 1);
        String fToStr = f.toString();

        Assert.assertEquals(fToStr, "5");
    }

    /** Дробь отрицательная*/
    @Test
    void FractionIsNegative() {
        ReducedFraction f = new ReducedFraction(-2, 3);
        String fToStr = f.toString();

        Assert.assertEquals(fToStr, "-2/3");
    }
}