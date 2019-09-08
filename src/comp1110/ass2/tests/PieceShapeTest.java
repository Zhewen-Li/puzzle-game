package comp1110.ass2.tests;

import comp1110.ass2.PieceShape;

import java.util.Objects;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/* Created by Saffi on 24/09/18. */
public class PieceShapeTest {
    @Test
    void testGetColor() {
        String[] pieceColours = {PieceShape.a.getColor(), PieceShape.b.getColor(), PieceShape.c.getColor(),
                PieceShape.d.getColor(), PieceShape.e.getColor(), PieceShape.f.getColor(), PieceShape.g.getColor(),
                PieceShape.h.getColor()};
        String[] targetColours = {"red", "red", "blue", "blue", "green", "green", "yellow", "yellow"};
        for (int i = 0; i < targetColours.length; i++) {
            assertTrue(Objects.equals(pieceColours[i],targetColours[i]));
        }
    }
    @Test
    void testGetRotation() {
        assertTrue(Objects.equals(PieceShape.e.getRotatedPiece(1),"EFb"));
        assertTrue(Objects.equals(PieceShape.a.getRotatedPiece(0),"AbCg"));
        assertTrue(Objects.equals(PieceShape.c.getRotatedPiece(5),"aEim"));
        assertTrue(Objects.equals(PieceShape.f.getRotatedPiece(4),"efBG"));
        assertFalse(Objects.equals(PieceShape.a.getRotatedPiece(0),"aaaa"));
    }
}
