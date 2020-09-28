import org.junit.jupiter.api.Test;
import reducedfractionapp.ReducedFraction;
import org.junit.Assert;

public class ReducedFractionConstructorTest {

    /** Передается нулевой знаменатель*/
    @Test
    void divisionByZero() {
        boolean isException = false;
        try {
            ReducedFraction f = new ReducedFraction(4, 0);
        } catch (ArithmeticException e) {
            isException = true;
        }
        Assert.assertTrue(isException);
    }

    /** Передана сократимая дробь*/
    @Test
    void NoReducedFraction() {
        ReducedFraction f = new ReducedFraction(3, 9);
        ReducedFraction exp_f = new ReducedFraction(1, 3);

        Assert.assertEquals(f, exp_f);
    }

    /** Передана дробь с отрицательным числителем и знаменателем*/
    @Test
    void fractionWithNegativeNumeratorAndDenominator() {
        ReducedFraction f = new ReducedFraction(-4, -7);
        ReducedFraction exp_f = new ReducedFraction(4, 7);

        Assert.assertEquals(f, exp_f);
    }

    /** Передана дробь с отрицательным знаменателем*/
    @Test
    void fractionWithNegativeDenominator() {
        ReducedFraction f = new ReducedFraction(4, -7);
        ReducedFraction exp_f = new ReducedFraction(-4, 7);

        Assert.assertEquals(f, exp_f);
    }

    /** Передана дробь, являющаяся целым числом*/
    @Test
    void IntegerFraction() {
        ReducedFraction f = new ReducedFraction(2);
        ReducedFraction exp_f = new ReducedFraction(2, 1);

        Assert.assertEquals(f, exp_f);
    }
}
