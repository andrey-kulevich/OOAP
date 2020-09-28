<<<<<<< HEAD



import eightqueens.Desk;
import eightqueens.Queen;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;
class findNewAcceptablePositionTest {

    @Test
    //Единственная королева стоит в начальной позиции
    void singleQueenFirstPos() {

        Point[] queen_pos = { new Point(1, Desk.posBelowDesk() ) };
        Desk d = Desk.buildDesk(queen_pos);

        Point exp_pos = new Point(1, 1);
        boolean exp_ok = true;

        Queen q = d.getQueen(exp_pos.x);
        boolean ok = q.findNewAcceptablePosition();

        Assert.assertEquals(exp_ok, ok);
        Assert.assertEquals(exp_pos.x, q.col());
        Assert.assertEquals(exp_pos.y, q.row());
    }

    @Test
    //Две королевы стоят в начальной позиции
    void twoQueensFirstPos() {

        Point[] queen_pos = {   new Point(1, Desk.posBelowDesk() ),
                new Point(2, Desk.posBelowDesk() )  };
        Desk d = Desk.buildDesk(queen_pos);

        Point[] exp_pos = { new Point(1, 1), new Point(2, 3) };
        boolean exp_ok = true;

        boolean ok = false;
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            ok = q.findNewAcceptablePosition();
        }

        Assert.assertEquals(exp_ok, ok);
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            Assert.assertEquals(exp_pos[i].x, q.col());
            Assert.assertEquals(exp_pos[i].y, q.row());
        }
    }

    @Test
    //Происходит несколько запросов к соседям
    void coupleOfRequestsToNeighbour() {

        Point[] queen_pos = {
                new Point(1, Desk.posBelowDesk() ),
                new Point(2, Desk.posBelowDesk() ),
                new Point(3, Desk.posBelowDesk() ),
                new Point(4, Desk.posBelowDesk() ),
                new Point(5, Desk.posBelowDesk() ),
                new Point(6, Desk.posBelowDesk() )};
        Desk d = Desk.buildDesk(queen_pos);

        Point[] exp_pos = {
                new Point(1, 1),
                new Point(2, 3),
                new Point(3, 5),
                new Point(4, 7),
                new Point(5, 2),
                new Point(6, 4) };
        boolean exp_ok = true;

        boolean ok = false;
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            ok = q.findNewAcceptablePosition();
        }

        Assert.assertEquals(exp_ok, ok);
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            Assert.assertEquals(exp_pos[i].x, q.col());
            Assert.assertEquals(exp_pos[i].y, q.row());
        }
    }

    @Test
    //Все ферзи находятся в приемлемой позиции
    void queensAlreadyHaveAcceptablePos() {

        Point[] queen_pos = {
                new Point(1, 1 ),
                new Point(2, 3 ),
                new Point(3, 5 ),
                new Point(4, 7 ),
                new Point(5, 2 ),
                new Point(6, 4 )};
        Desk d = Desk.buildDesk(queen_pos);

        Point[] exp_pos = {
                new Point(1, 2),
                new Point(2, 4),
                new Point(3, 6),
                new Point(4, 8),
                new Point(5, 3),
                new Point(6, 5) };
        boolean exp_ok = true;

        boolean ok = false;
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            ok = q.findNewAcceptablePosition();
        }

        Assert.assertEquals(exp_ok, ok);
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            Assert.assertEquals(exp_pos[i].x, q.col());
            Assert.assertEquals(exp_pos[i].y, q.row());
        }
    }

    @Test
    //Один из ферзей вышел за пределы доски
    void queenAboveTheDesk() {

        Point[] queen_pos = {
                new Point(1, 1 ),
                new Point(2, 9 ),
        };
        Desk d = Desk.buildDesk(queen_pos);

        Point[] exp_pos = {
                new Point(1, 3),
                new Point(2, 1),
        };
        boolean exp_ok = true;

        boolean ok = false;
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            ok = q.findNewAcceptablePosition();
        }

        Assert.assertEquals(exp_ok, ok);
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            Assert.assertEquals(exp_pos[i].x, q.col());
            Assert.assertEquals(exp_pos[i].y, q.row());
        }
    }

    @Test
    //Приемлемых позиций больше нет
    void acceptablePosNotFound() {

        Point[] queen_pos = {
                new Point(1, 8 ),
                new Point(2, 6 ),
        };
        Desk d = Desk.buildDesk(queen_pos);

        Point[] exp_pos = {
                new Point(1, 9),
                new Point(2, 7),
        };
        boolean exp_ok = true;

        boolean ok = false;
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            ok = q.findNewAcceptablePosition();
        }

        Assert.assertEquals(exp_ok, ok);
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            Assert.assertEquals(exp_pos[i].x, q.col());
            Assert.assertEquals(exp_pos[i].y, q.row());
        }
    }

    @Test
    //Ферзи изначально находятся в неприемлемой позиции
    void queensHaveNotAcceptablePos() {

        Point[] queen_pos = {
                new Point(1, 6 ),
                new Point(2, 6 ),
        };
        Desk d = Desk.buildDesk(queen_pos);

        Point[] exp_pos = {
                new Point(1, 8),
                new Point(2, 1),
        };
        boolean exp_ok = true;

        boolean ok = false;
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            ok = q.findNewAcceptablePosition();
        }

        Assert.assertEquals(exp_ok, ok);
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            Assert.assertEquals(exp_pos[i].x, q.col());
            Assert.assertEquals(exp_pos[i].y, q.row());
        }
    }

=======



import eightqueens.Desk;
import eightqueens.Queen;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;
class findNewAcceptablePositionTest {

    @Test
    //Единственная королева стоит в начальной позиции
    void singleQueenFirstPos() {

        Point[] queen_pos = { new Point(1, Desk.posBelowDesk() ) };
        Desk d = Desk.buildDesk(queen_pos);

        Point exp_pos = new Point(1, 1);
        boolean exp_ok = true;

        Queen q = d.getQueen(exp_pos.x);
        boolean ok = q.findNewAcceptablePosition();

        Assert.assertEquals(exp_ok, ok);
        Assert.assertEquals(exp_pos.x, q.col());
        Assert.assertEquals(exp_pos.y, q.row());
    }

    @Test
    //Две королевы стоят в начальной позиции
    void twoQueensFirstPos() {

        Point[] queen_pos = {   new Point(1, Desk.posBelowDesk() ),
                new Point(2, Desk.posBelowDesk() )  };
        Desk d = Desk.buildDesk(queen_pos);

        Point[] exp_pos = { new Point(1, 1), new Point(2, 3) };
        boolean exp_ok = true;

        boolean ok = false;
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            ok = q.findNewAcceptablePosition();
        }

        Assert.assertEquals(exp_ok, ok);
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            Assert.assertEquals(exp_pos[i].x, q.col());
            Assert.assertEquals(exp_pos[i].y, q.row());
        }
    }

    @Test
    //Происходит несколько запросов к соседям
    void coupleOfRequestsToNeighbour() {

        Point[] queen_pos = {
                new Point(1, Desk.posBelowDesk() ),
                new Point(2, Desk.posBelowDesk() ),
                new Point(3, Desk.posBelowDesk() ),
                new Point(4, Desk.posBelowDesk() ),
                new Point(5, Desk.posBelowDesk() ),
                new Point(6, Desk.posBelowDesk() )};
        Desk d = Desk.buildDesk(queen_pos);

        Point[] exp_pos = {
                new Point(1, 1),
                new Point(2, 3),
                new Point(3, 5),
                new Point(4, 7),
                new Point(5, 2),
                new Point(6, 4) };
        boolean exp_ok = true;

        boolean ok = false;
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            ok = q.findNewAcceptablePosition();
        }

        Assert.assertEquals(exp_ok, ok);
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            Assert.assertEquals(exp_pos[i].x, q.col());
            Assert.assertEquals(exp_pos[i].y, q.row());
        }
    }

    @Test
    //Все ферзи находятся в приемлемой позиции
    void queensAlreadyHaveAcceptablePos() {

        Point[] queen_pos = {
                new Point(1, 1 ),
                new Point(2, 3 ),
                new Point(3, 5 ),
                new Point(4, 7 ),
                new Point(5, 2 ),
                new Point(6, 4 )};
        Desk d = Desk.buildDesk(queen_pos);

        Point[] exp_pos = {
                new Point(1, 2),
                new Point(2, 4),
                new Point(3, 6),
                new Point(4, 8),
                new Point(5, 3),
                new Point(6, 5) };
        boolean exp_ok = true;

        boolean ok = false;
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            ok = q.findNewAcceptablePosition();
        }

        Assert.assertEquals(exp_ok, ok);
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            Assert.assertEquals(exp_pos[i].x, q.col());
            Assert.assertEquals(exp_pos[i].y, q.row());
        }
    }

    @Test
    //Один из ферзей вышел за пределы доски
    void queenAboveTheDesk() {

        Point[] queen_pos = {
                new Point(1, 1 ),
                new Point(2, 9 ),
        };
        Desk d = Desk.buildDesk(queen_pos);

        Point[] exp_pos = {
                new Point(1, 3),
                new Point(2, 1),
        };
        boolean exp_ok = true;

        boolean ok = false;
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            ok = q.findNewAcceptablePosition();
        }

        Assert.assertEquals(exp_ok, ok);
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            Assert.assertEquals(exp_pos[i].x, q.col());
            Assert.assertEquals(exp_pos[i].y, q.row());
        }
    }

    @Test
    //Приемлемых позиций больше нет
    void acceptablePosNotFound() {

        Point[] queen_pos = {
                new Point(1, 8 ),
                new Point(2, 6 ),
        };
        Desk d = Desk.buildDesk(queen_pos);

        Point[] exp_pos = {
                new Point(1, 9),
                new Point(2, 7),
        };
        boolean exp_ok = true;

        boolean ok = false;
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            ok = q.findNewAcceptablePosition();
        }

        Assert.assertEquals(exp_ok, ok);
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            Assert.assertEquals(exp_pos[i].x, q.col());
            Assert.assertEquals(exp_pos[i].y, q.row());
        }
    }

    @Test
    //Ферзи изначально находятся в неприемлемой позиции
    void queensHaveNotAcceptablePos() {

        Point[] queen_pos = {
                new Point(1, 6 ),
                new Point(2, 6 ),
        };
        Desk d = Desk.buildDesk(queen_pos);

        Point[] exp_pos = {
                new Point(1, 8),
                new Point(2, 1),
        };
        boolean exp_ok = true;

        boolean ok = false;
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            ok = q.findNewAcceptablePosition();
        }

        Assert.assertEquals(exp_ok, ok);
        for(int i = 0; i < d.queenCount(); i++) {
            Queen q = d.getQueen(i+1);
            Assert.assertEquals(exp_pos[i].x, q.col());
            Assert.assertEquals(exp_pos[i].y, q.row());
        }
    }

>>>>>>> 1f63f30dff76cf73b515a105e34bd46f40b661a7
}