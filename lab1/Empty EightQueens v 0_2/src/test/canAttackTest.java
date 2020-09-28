<<<<<<< HEAD
import eightqueens.Desk;
import eightqueens.Queen;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class canAttackTest {
    @Test
    void twoQueensCannotAttack() {
        Point[] queen_pos = {
                new Point(1, 1 ),
                new Point(2, 3 )  };
        Desk d = Desk.buildDesk(queen_pos);

        Queen q = d.getQueen(1);

        boolean can_attack = q.canAttack(2, 3);

        Assert.assertFalse(can_attack);
    }

    @Test
    void twoQueensAttackVertical() {
        Point[] queen_pos = {
                new Point(1, 1 ),
                new Point(1, 3 )  };
        Desk d = Desk.buildDesk(queen_pos);

        Queen q = d.getQueen(1);

        boolean can_attack = q.canAttack(1, 3);

        Assert.assertTrue(can_attack);
    }

    @Test
    void twoQueensAttackHorizontal() {
        Point[] queen_pos = {
                new Point(1, 3 ),
                new Point(2, 3 )  };
        Desk d = Desk.buildDesk(queen_pos);

        Queen q = d.getQueen(1);

        boolean can_attack = q.canAttack(2, 3);

        Assert.assertTrue(can_attack);
    }

    @Test
    void twoQueensAttackDiagonal() {
        Point[] queen_pos = {
                new Point(1, 1 ),
                new Point(2, 2 )  };
        Desk d = Desk.buildDesk(queen_pos);

        Queen q = d.getQueen(1);

        boolean can_attack = q.canAttack(2, 2);

        Assert.assertTrue(can_attack);
    }

    @Test
    void coupleOfQueensCannotAttack() {
        Point[] queen_pos = {
                new Point(1, 1),
                new Point(2, 3),
                new Point(3, 5),
                new Point(4, 7),
                new Point(5, 2),
                new Point(6, 4)  };
        Desk d = Desk.buildDesk(queen_pos);

        Queen q = d.getQueen(5);

        boolean can_attack = q.canAttack(6, 4);

        Assert.assertFalse(can_attack);
    }

    @Test
    void coupleOfQueensCanAttack() {
        Point[] queen_pos = {
                new Point(1, 1 ),
                new Point(2, 5 ),
                new Point(3, 8 ),
                new Point(4, 8 ),
                new Point(5, 3 ),
                new Point(6, 4 ),
                new Point(7, 2 ),
                new Point(8, 4 )};
        Desk d = Desk.buildDesk(queen_pos);

        Queen q = d.getQueen(7);

        boolean can_attack = q.canAttack(6, 4);

        Assert.assertTrue(can_attack);
    }
}
=======
import eightqueens.Desk;
import eightqueens.Queen;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class canAttackTest {
    @Test
    void twoQueensCannotAttack() {
        Point[] queen_pos = {
                new Point(1, 1 ),
                new Point(2, 3 )  };
        Desk d = Desk.buildDesk(queen_pos);

        Queen q = d.getQueen(1);

        boolean can_attack = q.canAttack(2, 3);

        Assert.assertFalse(can_attack);
    }

    @Test
    void twoQueensAttackVertical() {
        Point[] queen_pos = {
                new Point(1, 1 ),
                new Point(1, 3 )  };
        Desk d = Desk.buildDesk(queen_pos);

        Queen q = d.getQueen(1);

        boolean can_attack = q.canAttack(1, 3);

        Assert.assertTrue(can_attack);
    }

    @Test
    void twoQueensAttackHorizontal() {
        Point[] queen_pos = {
                new Point(1, 3 ),
                new Point(2, 3 )  };
        Desk d = Desk.buildDesk(queen_pos);

        Queen q = d.getQueen(1);

        boolean can_attack = q.canAttack(2, 3);

        Assert.assertTrue(can_attack);
    }

    @Test
    void twoQueensAttackDiagonal() {
        Point[] queen_pos = {
                new Point(1, 1 ),
                new Point(2, 2 )  };
        Desk d = Desk.buildDesk(queen_pos);

        Queen q = d.getQueen(1);

        boolean can_attack = q.canAttack(2, 2);

        Assert.assertTrue(can_attack);
    }

    @Test
    void coupleOfQueensCannotAttack() {
        Point[] queen_pos = {
                new Point(1, 1),
                new Point(2, 3),
                new Point(3, 5),
                new Point(4, 7),
                new Point(5, 2),
                new Point(6, 4)  };
        Desk d = Desk.buildDesk(queen_pos);

        Queen q = d.getQueen(5);

        boolean can_attack = q.canAttack(6, 4);

        Assert.assertFalse(can_attack);
    }

    @Test
    void coupleOfQueensCanAttack() {
        Point[] queen_pos = {
                new Point(1, 1 ),
                new Point(2, 5 ),
                new Point(3, 8 ),
                new Point(4, 8 ),
                new Point(5, 3 ),
                new Point(6, 4 ),
                new Point(7, 2 ),
                new Point(8, 4 )};
        Desk d = Desk.buildDesk(queen_pos);

        Queen q = d.getQueen(7);

        boolean can_attack = q.canAttack(6, 4);

        Assert.assertTrue(can_attack);
    }
}
>>>>>>> 1f63f30dff76cf73b515a105e34bd46f40b661a7
