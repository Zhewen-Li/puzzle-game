package comp1110.ass2;

import comp1110.ass2.gui.Viewer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//Written by Lauren Nelson-Lee
//Used for java fx code for Board for implementation of pieces onto the board
public class PieceJFX extends Piece {

    //Characteristics of each piece
    public int initialX;
    public int initialY;
    public String pieceStr;
    private static final String URI_BASE = "assets/";
    public boolean offset;


    public PieceJFX(String encoding, int initialX, int initialY){
        super(encoding);
        colour = myShape.getColor();
        this.initialX = initialX;
        this.initialY = initialY;
        this.pieceStr = encoding;
        this.offset = false;
    }

    public void changePieceStr(String newStr){
        pieceStr = newStr;
    }

    public String getPieceStr(){
        return pieceStr;
    }

    //Gets the image corresponding to the piece letter
    public ImageView getImagePiece(){
        Image i = new Image(Viewer.class.getResource(URI_BASE + shape +".png").toString());
        ImageView pieceA = new ImageView();
        pieceA.setX(initialX);
        pieceA.setY(initialY);
        pieceA.setImage(i);
        pieceA.setPreserveRatio(true);
        pieceA.setSmooth(true);
        return pieceA;
    }

    //Changes offset of image piece on the board
    public void changeOffset(boolean newOffset){
        offset = newOffset;
    }

    @Override
    public String toString() {
        return "JFX info for "+ myShape + ", drawn at position " + bp + ", with rotation "+ orientation;
    }

}
