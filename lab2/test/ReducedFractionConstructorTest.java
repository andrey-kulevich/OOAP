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

        Assert.assertEquals(f.getNumerator(), 1);
        Assert.assertEquals(f.getDenominator(), 3);
    }

    /** Передана дробь с отрицательным числителем и знаменателем*/
    @Test
    void fractionWithNegativeNumeratorAndDenominator() {
        ReducedFraction f = new ReducedFraction(-4, -7);

        Assert.assertEquals(f.getNumerator(), 4);
        Assert.assertEquals(f.getDenominator(), 7);
    }

    /** Передана дробь с отрицательным знаменателем*/
    @Test
    void fractionWithNegativeDenominator() {
        ReducedFraction f = new ReducedFraction(4, -7);

        Assert.assertEquals(f.getNumerator(), -4);
        Assert.assertEquals(f.getDenominator(), 7);
    }

    /** Передана дробь, являющаяся целым числом*/
    @Test
    void IntegerFraction() {
        ReducedFraction f = new ReducedFraction(2);

        Assert.assertEquals(f.getNumerator(), 2);
        Assert.assertEquals(f.getDenominator(), 1);
    }
}
