package comp1110.ass2;
//Created by Saffron Bannister
public class Peg extends Placeable{
    public PegType myType;
    public int value;
    public String colour;
    public final char orientation = '0';

    Peg(String encoding){
        super (encoding);
        char type = encoding.charAt(0);
        this.myType = PegType.getType(type);
        this.colour = myType.getColor();
        setShape();
    }


    @Override
    void setShape() {
        switch (myType) {
            case i: value = -1; break; // RED
            case j: value = -2; break; // BLUE
            case k: value = -3; break;// GREEN
            case l: value = -4; break; // YELLOW
        }
    }

    @Override
    public String toString() {
        return this.pieceEncoding + ", a " + colour + " peg";
    }
}
