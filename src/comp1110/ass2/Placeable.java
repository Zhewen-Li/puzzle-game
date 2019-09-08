package comp1110.ass2;

import java.util.ArrayList;
/* Created by Saffron Bannister */
public class Placeable {

    //Create string of the 4-character piece
    public String pieceEncoding;
    public int column;
    public int row;
    public ArrayList<Position> shapeVals;
    public int bp;

    public Placeable(String pieceEncoding){
        this.pieceEncoding = pieceEncoding;
        this.column = Integer.parseInt(String.valueOf(pieceEncoding.charAt(1)));
        this.row = getRow(pieceEncoding.charAt(2));
        boardPlacement();
    }


    public ArrayList<Position> getShapeVals(){
        return shapeVals;
    }

    private int getRow(char c) {
        switch (c) {
            case 'A': return 0;
            case 'B': return 1;
            case 'C': return 2;
            case 'D': return 3;
        }
        System.out.println("ERROR: inappropriate row value");
        return 255;
    }

    private void boardPlacement() {
        bp = (row * 8) + column;
        //System.out.println(row + ", " +column +": "+bp);
    }

    void setShape() { }

    //split the string by groups of four
    static String[] splitIntoFours(String s) {
        assert (s.length() % 4 == 0);
        String[] fours = new String[s.length()/4];
        int j = 0;
        for (int i = 0; i < s.length(); i+=4) {
            fours[j] = s.substring(i,i+4);
            j++;
        }
        return fours;
    }

    public static String getPiece(String s) {
        return String.valueOf(s.charAt(0)) + s.charAt(3);
    }

}
