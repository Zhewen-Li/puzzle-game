package comp1110.ass2.tests;

import comp1110.ass2.gui.Board;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class BoardTest {
    //Testing methods used in Board gui
    // Created by Lauren Nelson-Lee to test Board
    @Test
    void testNumAfterRotation(){
        Board testBoard = new Board();
        assertTrue(testBoard.NumAfterRotation("0").equals("1"));
        assertTrue(testBoard.NumAfterRotation("1").equals("2"));
        assertTrue(testBoard.NumAfterRotation("2").equals("3"));
        assertTrue(testBoard.NumAfterRotation("4").equals("5"));
        assertTrue(testBoard.NumAfterRotation("5").equals("6"));
        assertTrue(testBoard.NumAfterRotation("6").equals("7"));
        assertTrue(testBoard.NumAfterRotation("3").equals("0"));
        assertTrue(testBoard.NumAfterRotation("7").equals("4"));
    }

    @Test
    void testFlipNum(){
        Board testBoard = new Board();
        assertTrue(testBoard.newFlip("0").equals("6"));
        assertTrue(testBoard.newFlip("1").equals("5"));
        assertTrue(testBoard.newFlip("2").equals("4"));
        assertTrue(testBoard.newFlip("3").equals("7"));
        assertTrue(testBoard.newFlip("4").equals("2"));
        assertTrue(testBoard.newFlip("5").equals("1"));
        assertTrue(testBoard.newFlip("6").equals("0"));
        assertTrue(testBoard.newFlip("7").equals("3"));

    }

    @Test
    void testgetBoardCircleCol(){
        Board testBoard = new Board();
        assertTrue(testBoard.getBoardCircleCol(50) == 60);
        assertTrue(testBoard.getBoardCircleCol(50.5) == 60);
        assertTrue(testBoard.getBoardCircleCol(75) == 60);
        assertTrue(testBoard.getBoardCircleCol(90) == 120);
        assertTrue(testBoard.getBoardCircleCol(120) == 120);
        assertTrue(testBoard.getBoardCircleCol(150) == 180);
        assertTrue(testBoard.getBoardCircleCol(220) == 240);
        assertTrue(testBoard.getBoardCircleCol(395) == 420);
        assertTrue(testBoard.getBoardCircleCol(485) == 480);
        assertTrue(testBoard.getBoardCircleCol(600) == -1);
        assertTrue(testBoard.getBoardCircleCol(-6) == -1);

    }

    @Test
    void testgetBoardCircleRow(){
        Board testBoard = new Board();
        assertTrue(testBoard.getBoardCircleRow(238) == 238);
        assertTrue(testBoard.getBoardCircleRow(238.56) == 238);
        assertTrue(testBoard.getBoardCircleRow(250) == 238);
        assertTrue(testBoard.getBoardCircleRow(150) == 178);
        assertTrue(testBoard.getBoardCircleRow(103) == 118);
        assertTrue(testBoard.getBoardCircleRow(76) == 58);
        assertTrue(testBoard.getBoardCircleRow(58) == 58);
        assertTrue(testBoard.getBoardCircleRow(0) == -1);
        assertTrue(testBoard.getBoardCircleRow(500) == -1);
    }

    @Test
    void testgetBoardCircleColX(){
        //x position of each column:
        //40 100 160 220 280 340 400 460
        Board testBoard = new Board();
        assertTrue(testBoard.getBoardCircleColX("1") == 40);
        assertTrue(testBoard.getBoardCircleColX("2") == 100);
        assertTrue(testBoard.getBoardCircleColX("3") == 160);
        assertTrue(testBoard.getBoardCircleColX("4") == 220);
        assertTrue(testBoard.getBoardCircleColX("5") == 280);
        assertTrue(testBoard.getBoardCircleColX("6") == 340);
        assertTrue(testBoard.getBoardCircleColX("7") == 400);
    }

    @Test
    void testgetBoardCircleRowY() {
        //y position of each column:
        //38 98 158 218
        Board testBoard = new Board();
        assertTrue(testBoard.getBoardCircleRowY("A") == 38);
        assertTrue(testBoard.getBoardCircleRowY("B") == 98);
        assertTrue(testBoard.getBoardCircleRowY("C") == 158);
        assertTrue(testBoard.getBoardCircleRowY("D") == 218);
        assertTrue(testBoard.getBoardCircleRowY("E") == -1);
    }

    @Test
    void testgetBoardLetter(){
        Board testBoard = new Board();
        assertTrue(testBoard.getBoardLetter(58).equals("A"));
        assertTrue(testBoard.getBoardLetter(118).equals("B"));
        assertTrue(testBoard.getBoardLetter(178).equals("C"));
        assertTrue(testBoard.getBoardLetter(238).equals("D"));
        assertFalse(testBoard.getBoardLetter(20).equals("A"));
        assertTrue(testBoard.getBoardLetter(59).equals(""));
    }

    @Test
    void testgetBoardNumber() {
        Board testBoard = new Board();
        assertTrue(testBoard.getBoardNumber(60).equals("1"));
        assertTrue(testBoard.getBoardNumber(120).equals("2"));
        assertTrue(testBoard.getBoardNumber(180).equals("3"));
        assertTrue(testBoard.getBoardNumber(240).equals("4"));
        assertTrue(testBoard.getBoardNumber(300).equals("5"));
        assertTrue(testBoard.getBoardNumber(360).equals("6"));
        assertTrue(testBoard.getBoardNumber(420).equals("7"));
        assertTrue(testBoard.getBoardNumber(76).equals(""));
        assertTrue(testBoard.getBoardNumber(-8).equals(""));
        assertTrue(testBoard.getBoardNumber(760).equals(""));
    }

}
