package comp1110.ass2;

import java.util.ArrayList;
//Created by Saffron Bannister
public class Piece extends Placeable {

    //Characteristics of each piece
    public PieceShape myShape;
    public char shape;
    public int orientation;
    public String colour;

    public Piece(String encoding){
        super(encoding);
        this.shape = pieceEncoding.charAt(0);
        this.orientation = Integer.parseInt(String.valueOf(encoding.charAt(3)));
        //System.out.println(orientation);
        this.myShape = PieceShape.getShape(shape);
        setShape();
        colour = myShape.getColor();
        //System.out.println(this);
    }

    /*
    public static void main(String[] args) {
            Placeable p = new Piece("a1A0");

    }*/

    @Override
    void setShape() {
        String s = myShape.getRotatedPiece(orientation);
        //System.out.println(s);
        ArrayList<Position> positions = new ArrayList<>();
        char[] rotation = s.toCharArray();
        for (char c : rotation) {
            positions.add(getPos(c));
        }
        shapeVals = positions;
    }

    @Override
    public String toString() {
        return myShape + ", drawn at position " + bp + ", with rotation "+ orientation;
    }

    /*
    BP  +1  +2  +3
    +8  +9  +10 +11
    +16 +17 +18 +19
    +24 +25 +26 +27

    a  b  c  d
    e  f  g  h
    i  j  k  l
    m  n  o  p
     */
    private Position getPos(char a) {
        switch (a) {
            case 'a': return (new Position(2,bp));
            case 'b': return (new Position(2,bp+1));
            case 'c': return (new Position(2,bp+2));
            case 'd': return (new Position(2,bp+3));
            case 'e': return (new Position(2,bp+8));
            case 'f': return (new Position(2,bp+9));
            case 'g': return (new Position(2,bp+10));
            case 'h': return (new Position(2,bp+11));
            case 'i': return (new Position(2,bp+16));
            case 'j': return (new Position(2,bp+17));
            case 'k': return (new Position(2,bp+18));
            case 'l': return (new Position(2,bp+19));
            case 'm': return (new Position(2,bp+24));
            case 'n': return (new Position(2,bp+25));
            case 'o': return (new Position(2,bp+26));
            case 'p': return (new Position(2,bp+27));
            case 'A': return (new Position(getHoleVal(shape),bp));
            case 'B': return (new Position(getHoleVal(shape),bp+1));
            case 'C': return (new Position(getHoleVal(shape),bp+2));
            case 'D': return (new Position(getHoleVal(shape),bp+3));
            case 'E': return (new Position(getHoleVal(shape),bp+8));
            case 'F': return (new Position(getHoleVal(shape),bp+9));
            case 'G': return (new Position(getHoleVal(shape),bp+10));
            case 'H': return (new Position(getHoleVal(shape),bp+11));
            case 'I': return (new Position(getHoleVal(shape),bp+16));
            case 'J': return (new Position(getHoleVal(shape),bp+17));
            case 'K': return (new Position(getHoleVal(shape),bp+18));
            case 'L': return (new Position(getHoleVal(shape),bp+19));
            case 'M': return (new Position(getHoleVal(shape),bp+24));
            case 'N': return (new Position(getHoleVal(shape),bp+25));
            case 'O': return (new Position(getHoleVal(shape),bp+26));
            case 'P': return (new Position(getHoleVal(shape),bp+27));
            default:
                System.out.println("ERROR: incompatible char type");return null;
        }
    }

    //1 = red hole, 3 = blue hole, 4 = green hole, 5 = yellow hole
    //this is to be used to get possible peg placements
    private int getHoleVal(char shape) {
        switch (shape) {
            case 'a':
            case 'b': return 1; //red
            case 'c':
            case 'd': return 3; //blue
            case 'e':
            case 'f': return 4; //green
            default:return 5; //defaults to yellow
        }
    }

}
