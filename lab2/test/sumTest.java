import reducedfractionapp.ReducedFraction;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class sumTest {
    /** Две различные дроби*/
    @Test
    void twoDifferentFractions() {
        ReducedFraction f1 = new ReducedFraction(1, 2);
        ReducedFraction f2 = new ReducedFraction(6, 8);
        ReducedFraction result = f1.sum(f2);

        Assert.assertEquals(result, new ReducedFraction(5, 4));
    }

    /** Дроби одинаковые*/
    @Test
    void twoEqualFractions() {
        ReducedFraction f1 = new ReducedFraction(1, 2);
        ReducedFraction f2 = new ReducedFraction(1, 2);
        ReducedFraction result = f1.sum(f2);

        Assert.assertEquals(result, new ReducedFraction(1, 1));
    }

    /** Сумма дробей равна нулю*/
    @Test
    void resultOfSummIsZero() {
        ReducedFraction f1 = new ReducedFraction(1, 2);
        ReducedFraction f2 = new ReducedFraction(-1, 2);
        ReducedFraction result = f1.sum(f2);

        Assert.assertEquals(result, new ReducedFraction(0, 2));
    }

    /** Одна из дробей равна нулю*/
    @Test
    void oneFractionIsZero() {
        ReducedFraction f1 = new ReducedFraction(0, 2);
        ReducedFraction f2 = new ReducedFraction(1, 2);
        ReducedFraction result = f1.sum(f2);

        Assert.assertEquals(result, new ReducedFraction(1, 2));
    }

    /** Обе дроби равны нулю*/
    @Test
    void bothFractionsIsZero() {
        ReducedFraction f1 = new ReducedFraction(0, 2);
        ReducedFraction f2 = new ReducedFraction(0, 2);
        ReducedFraction result = f1.sum(f2);

        Assert.assertEquals(result, new ReducedFraction(0, 2));
    }
}