package comp1110.ass2;

import java.util.ArrayList;
import java.util.HashMap;
import static comp1110.ass2.Placeable.splitIntoFours;

public class BoardState {
/*
    This class and most methods in it were created by Saffron Bannister
     getPegString and getPieceString written by Lauren Nelson-Lee

    A board is a size 32 array of integers
    Each spot in the array represents a place on the board
    for example, here is an empty board
    0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    This can be represented as:
    0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0
    A space of 0 represents an empty space
    1,3,4,5 represents a 'hole' section of a piece (red, blue, green and yellow)
    2 represents a solid section of a piece
    -1 represents a red peg
    -2 represents a blue peg
    -3 represents a green peg
    -4 represents a yellow peg
    a boardstate also holds the whole encoding as well as just the peg and piece encodings
 */
    private int BOARDSIZE = 32;
    public int[] board;
    public String pegPlacementString;
    public String piecePlacementString;
    public String encoding = "";
    public int prevPos;

    //prints the current board to the console
    void printBoard() {
        for (int i = 0; i < board.length; i++) {
            System.out.print(board[i] + " ");
            if (i == 7 || i == 15 || i == 23) {
                System.out.println();
            }
        }

        System.out.println();
        System.out.println();
    }

    public BoardState() {
        initialiseBoard();
    }

    public void setEncoding (String encoding) {
        this.encoding = encoding;
        this.pegPlacementString = getPegString(encoding);
        this.piecePlacementString = getPieceString(encoding);
        //System.out.println(encoding);
        this.placePegs(pegPlacementString);
        this.placePieces(piecePlacementString);
    }

    public String getPiecePlacementString() {
        return piecePlacementString;
    }
    public String getPegPlacementString() {
        return pegPlacementString;
    }

    void initialiseBoard(){
        //Set up 4x8 empty board
        board = new int[BOARDSIZE];
    }

    //Get peg placement string
     String getPegString(String encoding){
        char[] charEncoding = encoding.toCharArray();
        String pegChars = "ijkl";
        String last = "";

        for(int i = 0; i < encoding.length(); i++){
            if(pegChars.contains(String.valueOf(charEncoding[i]))){
                last += encoding.substring(i,i+4);
            }
        }

        return last;
    }

    private void placePieces (String encoding) {
         String[] pieces = splitIntoFours(encoding);
         for (String piece : pieces) {
             placePiece(piece);
         }
    }

    //Get piece placement string
    String getPieceString(String encoding){
        char[] charEncoding = encoding.toCharArray();
        String pieceChars = "abcdefgh";
        String last ="";
        // set order
        HashMap<Character,String> order = new HashMap();
        for(int i = 0; i < encoding.length(); i++){
            if(pieceChars.contains(String.valueOf(charEncoding[i]))){
                String a = encoding.substring(i,i+4);
                order.put(charEncoding[i],a);
            }
        }
        for(String s:order.values()){
            last+=s;
        }
        return last;
    }

    public void placePegs(String pegs){
        String[] splitPegs =  splitIntoFours(pegs);
        for (String p : splitPegs) {
            Peg peg = new Peg(p);
            //System.out.println(peg);
            if (board[peg.bp-1] == 0 && !outOfBounds(peg.bp)) {
                placePeg(peg);
            } else System.out.println("peg position was taken!?");
        }
        //Place pegs onto board
    }

    void placePeg(Peg p) {
        //System.out.println("Placing " + p.myType.getColor() + " peg in position " + p.bp);
        board[p.bp -1] = p.value;

    }

    //Check if board is empty
    boolean isEmpty(){
        for(int i = 0; i < board.length; i++) {
            if (board[i] != 0) return true;
        }
        return false;
    }

    //Put piece on board
    public void placePiece(String p){
        Piece place = new Piece(p);
        if (legalPlacement(place)) {
           // System.out.println("LEGAL!");
            for (Position pos : place.shapeVals) {
                board[pos.boardposition-1] = pos.value;
            }
        }
    }

    public boolean legalPlacement(Piece p){
        //Check if piece is able to be places in given position
        for (Position pos : p.shapeVals) {
            //return false if out of bounds
            if (pos.boardposition > board.length || pos.boardposition < 1) {
                //System.out.println("ILLEGAL: out of bounds");
                return false;
            } else if ((prevPos == 8 && pos.boardposition == 9 ) || (prevPos == 16 && pos.boardposition == 17 ) ||
                    (prevPos == 24 && pos.boardposition == 25 ) || (prevPos == 9 && pos.boardposition == 8 ) ||
                    (prevPos == 17 && pos.boardposition == 16 ) || (prevPos == 25 && pos.boardposition == 24 ) ) {
                //System.out.println("ILLEGAL: out of bounds");
                return false;
            }else {
                //System.out.println("in bounds");
                if (pos == p.shapeVals.get(p.shapeVals.size()-1)){
                    prevPos = 0;
                } else{
                    prevPos = pos.boardposition;
                }

            }
            //get the board position of the circle we're looking to place
            int b = board[pos.boardposition -1];

            boolean hole = pos.isHole(); //get whether the circle we want to place is a hole

            //if the board position is taken by another piece, or a peg and the circle isn't a hole
            // then return false
            if (b == 1 || b == 2 || b == 3 || b == 4 || b == 5 ) {
                //System.out.println("ILLEGAL: position taken by another piece!");
                return false;
            }
            if (!hole && b==-1 ||!hole && b==-2 ||!hole && b==-3 ||!hole && b==-4) {
                //System.out.println("ILLEGAL: cannot place a solid circle over a peg");
                return false;
            }
            //if it is a hole and the board is not taken by another piece,
            // return false if the colours don't match
            if (hole) {
                //System.out.println(p.colour + b);
                switch (b) {
                    case -1: if(!p.colour.equals("red")) {
                       // System.out.println("wrong colour");
                        return false;
                    } break;
                    case -2: if(!p.colour.equals("blue")) {
                        //System.out.println("wrong colour");
                        return false;
                    } break;
                    case -3: if(!p.colour.equals("green")) {
                       // System.out.println("wrong colour");
                        return false;
                    } break;
                    case -4: if(!p.colour.equals("yellow")) {
                       // System.out.println("wrong colour");
                        return false;
                    } break;
                }
            } //else System.out.println("not hole");
        }

        //System.out.println("legal position");
        return true;
    }

    private boolean outOfBounds(int boardposition) {
        if (boardposition > board.length || boardposition < 1) {
            return true;
        }
        else return false;
    }

    //Check if all pieces have been placed
    public boolean isComplete(){
        for(int i=0;i<32;i++){
            return board[i] == 1 || board[i] == 2;
        }
        return false;
    }

    //checks if all pieces are legal
    public boolean allLegal(String pieces, String pegs) {
        initialiseBoard();
        String[] splitPegs = splitIntoFours(pegs);
        String[] splitPieces = splitIntoFours(pieces);
        for (String p: splitPegs) {
            Peg peg = new Peg(p);
            if (outOfBounds(peg.bp)) {
                return false;
            }
            if (board[peg.bp-1] != 0) {
                return false;
            }
            placePeg(peg);
        }
        for (String p : splitPieces) {
            Piece piece = new Piece(p);
            if (legalPlacement(piece)) {
               // System.out.println("legal placement");
                for (Position pos : piece.shapeVals) {
                    board[pos.boardposition-1] = pos.value;
                }
            } else return false;
        }
        return true;
    }

    public String toString() {
        return encoding;
    }

    //run after setting up the board to get possible holes where pegs could go
    // (used for generating starting positions!)
    public ArrayList<Integer> getPegPositions(char peg) {
        ArrayList<Integer> pegPositionOptions = new ArrayList<>();

        //which colour hole we're looking for
        int getter;
        switch (peg) {
            case 'i': getter = 1; break; //red
            case 'j': getter = 3; break; //blue
            case 'k': getter = 4; break; //green
            case 'l':
            default:getter = 5; break;
        }
        //iterate through the list and add board positions where the getter is got
        for (int i = 0; i < board.length; i++) {
            if (board[i] == getter) {
                pegPositionOptions.add(i);
            }
        }
        return pegPositionOptions;
    }
}
