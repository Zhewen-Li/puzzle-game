package comp1110.ass2;

/**
 * Created by Saffron Bannister on 29/08/18.
 *
 * Simple class for holding a value at a position
 */
public class Position {
    /*
     *  boardposition is the place on the board where the value is (where the board is an int array of size 32)
     *  the value is an integer representation of a square part of a piece or of a peg
     *  where:
     *  - 1 = red peg
     *  - 2 = blue peg
     *  - 3 = green peg
     *  - 4 = yellow peg
     *    0 = empty space
     *    1 = red hole (previously any hole, later amended so I could check the colour of the hole)
     *    2 = filled space
     *    3 = blue hole
     *    4 = green hole
     *    5 = yellow hole
     *    See also BoardState, as this directly affects BoardState
     *  */

    int boardposition;
    int value;

    public Position (int value, int boardposition) {
        this.boardposition = boardposition;
        this.value = value;
    }

    @Override
    public String toString() {
        return value+", drawn at position "+boardposition;
    }

    //returns true if the circle is a hole
    public boolean isHole () {
        if (value == 1 || value == 3 || value == 4 || value == 5) {
            return true;
        } else return false;
    }
}
