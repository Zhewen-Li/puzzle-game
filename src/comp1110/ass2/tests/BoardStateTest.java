package comp1110.ass2.tests;

import comp1110.ass2.BoardState;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Objects;

/* Created by Saffi on 24/09/18. */
public class BoardStateTest {


    @Test
    public void testStrings() {
        String e = "a7A7b6A7c1A3d2A6e2C3f3C2g4A7h6D0i6B0j2B0j1C0k3C0l4B0l5C0";
        BoardState test = new BoardState();
        assertTrue("board doesn't start empty!",Objects.equals(test.toString(), ""));
        test.setEncoding(e);
        assertTrue("board's encoding doesn't et the encoding!",Objects.equals(test.toString(), e));
    }

    @Test
    public void testPegsAndPieces() {
        String e = "a7A7b6A7c1A3d2A6e2C3f3C2g4A7h6D0i6B0j2B0j1C0k3C0l4B0l5C0";
        String pieces = "a7A7b6A7c1A3d2A6e2C3f3C2g4A7h6D0";
        String pegs = "i6B0j2B0j1C0k3C0l4B0l5C0";
        BoardState test = new BoardState();
        test.setEncoding(e);
        test.setEncoding(e);
        assertTrue(Objects.equals(pieces, test.getPiecePlacementString()));
        assertTrue(Objects.equals(pegs, test.getPegPlacementString()));
        assertFalse(!Objects.equals(pieces, test.getPiecePlacementString()));
        assertFalse(!Objects.equals(pegs, test.getPegPlacementString()));
    }

    @Test
    public void testLegalPlacements() {
        String e = "a7A7b6A7c1A3d2A6e2C3f3C2g4A7h6D0i6B0j2B0j1C0k3C0l4B0l5C0";
        BoardState test = new BoardState();
        test.setEncoding(e);
        String pieces = test.getPiecePlacementString();
        String pegs = test.getPegPlacementString();
        assertTrue("should be legal, but isn't",test.allLegal(pieces,pegs));
    }
}
