package comp1110.ass2.tests;
import comp1110.ass2.InitialiseBoard;
import comp1110.ass2.TwistGame;


import java.util.Objects;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/* Created by saff on 25/09/18. */
public class InitializeBoardTest {

    @Test
    public void testMinMaxHasPiece() {
        InitialiseBoard diff0 = new InitialiseBoard(0);
        assertTrue("diff0 expected min 7, got min " + diff0.getMin(), diff0.getMin() == 7);
        assertTrue("diff0 expected max 7, got max " + diff0.getMax(), diff0.getMax() == 7);
        assertTrue("diff0 expected there to be a piece but there was no piece", diff0.getHasPiece());
        InitialiseBoard diff1 = new InitialiseBoard(1);
        if (diff1.getHasPiece()) {
            assertTrue("diff1 expected max 6, got max " + diff1.getMax(), diff1.getMax() == 6);
            assertTrue("diff1 expected min 5, got min " + diff1.getMin(), diff1.getMin() == 5);

        } else {
            assertTrue("diff1 expected min 6, got min " + diff1.getMin(), diff1.getMin() == 6);
            assertTrue("diff1 expected max 7, got max " + diff1.getMax(), diff1.getMax() == 7);
        }
        InitialiseBoard diff2 = new InitialiseBoard(2);
        if (diff2.getHasPiece()) {
            assertTrue("diff2 expected min 3, got min " + diff2.getMin(), diff2.getMin() == 3);
            assertTrue("diff2 expected max 4, got max " + diff2.getMax(), diff2.getMax() == 4);
        } else {
            assertTrue("diff2 expected min 4, got min " + diff2.getMin(), diff2.getMin() == 4);
            assertTrue("diff2 expected max 5, got max " + diff2.getMax(), diff2.getMax() == 5);
        }

        InitialiseBoard diff3 = new InitialiseBoard(3);
        assertTrue("diff3 expected min 3, got min " + diff3.getMin(), diff3.getMin() == 3);
        assertTrue("diff3 expected max 3, got max " + diff3.getMax(), diff3.getMax() == 3);
        assertFalse("diff0 expected there to be no piece but there was a piece", diff3.getHasPiece());
    }

    /* this test takes too long to run (because of the amount of time it takes to run getSolutions)
    @Test
    public void testHasOneAnswer() throws Exception {
        InitialiseBoard board = new InitialiseBoard(0);
        assertFalse("no placement generated", (Objects.equals(board.getPlacement(), "")));
        String[] sols = TwistGame.getSolutions(board.getPlacement());
        if (sols == null) {
            throw new Exception("no solutions!");
        } else {
            assertTrue(sols.length == 1);
        }
    } */

    @Test
    public void testUsesRightNumberOfPegs() {
        InitialiseBoard board = new InitialiseBoard(3);
        assertFalse("no placement generated", (Objects.equals(board.getPlacement(), "")));

    }
}
