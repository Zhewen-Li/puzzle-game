package comp1110.ass2;


import java.lang.reflect.Array;
import java.util.*;


/**
 * This class provides the text interface for the Twist Game
 * <p>
 * The game is based directly on Smart Games' IQ-Twist game
 * (http://www.smartgames.eu/en/smartgames/iq-twist)
 */
public class TwistGame {

    /**
     * Determine whether a piece or peg placement is well-formed according to the following:
     * - it consists of exactly four characters
     * - the first character is in the range a .. l (pieces and pegs)
     * - the second character is in the range 1 .. 8 (columns)
     * - the third character is in the range A .. D (rows)
     * - the fourth character is in the range 0 .. 7 (if a piece) or is 0 (if a peg)
     *
     * @param piecePlacement A string describing a single piece or peg placement
     * @return True if the placement is well-formed
     */

    //Task 2 created by Zhewen Li
    public static boolean isPlacementWellFormed(String piecePlacement) {
        // FIXED Task 2: determine whether a piece or peg placement is well-formed
        // set range base for each character
        String first = "abcdefghijkl";
        String second = "12345678";
        String third = "ABCD";
        String fourth = "01234567";

        // check isWellFormed for a peg
        if (piecePlacement.length() == 4 &&  //  ensure input consists exactly 4 characters
                first.substring(8, 12).contains(String.valueOf(piecePlacement.charAt(0))) &&  // ensure first character from 'i' to 'l'
                second.contains(String.valueOf(piecePlacement.charAt(1))) &&
                third.contains(String.valueOf(piecePlacement.charAt(2))) &&
                piecePlacement.charAt(3) == '0') {   //  ensure the fourth character for a peg must be 0
            return true;
        }

        // check isWellFormed for a piece
        if (piecePlacement.length() == 4 &&    //  ensure input consists exactly 4 characters
                first.substring(0, 8).contains(String.valueOf(piecePlacement.charAt(0))) &&  // ensure first character from 'a' to 'h'
                second.contains(String.valueOf(piecePlacement.charAt(1))) &&
                third.contains(String.valueOf(piecePlacement.charAt(2))) &&
                fourth.contains(String.valueOf(piecePlacement.charAt(3)))) {
            return true;
        }
        return false;
    }


    /**
     * Determine whether a placement string is well-formed:
     * - it consists of exactly N four-character piece placements (where N = 1 .. 15);
     * - each piece or peg placement is well-formed
     * - each piece or peg placement occurs in the correct alphabetical order (duplicate pegs can be in either order)
     * - no piece or red peg appears more than once in the placement
     * - no green, blue or yellow peg appears more than twice in the placement
     *
     * @param placement A string describing a placement of one or more pieces and pegs
     * @return True if the placement is well-formed
     */
    //Task 3 written by Lauren Nelson-Lee
    public static boolean isPlacementStringWellFormed(String placement) {

        // FIXED Task 3: determine whether a placement is well-formed
        //change to char array so that we can iterate through string
        char[] placementArr = placement.toCharArray();

        //Create empty string array to store all sets of 4 in
        String[] setsArr = new String[placement.length() / 4];
        int index = 0;

        //Ensure there are N (< 15) 4-character placements in the string
        if (placement.length() % 4 == 0 && placement.length() / 4 <= 15 && placement.length() != 0) {
            //Iterate through every fourth character
            for (int i = 0; i < placement.length(); i = i + 4) {

                //Check if the set of 4 is structured correctly
                String subset = placement.substring(i, i + 4);
                if (!isPlacementWellFormed(subset)) {
                    return false;
                }

                //Check for duplicates
                int redCount = 1;
                int yellowCount = 1;
                int greenCount = 1;
                int blueCount = 1;
                for (String s : setsArr) {
                    //See if piece has already been placed
                    if (subset.equals(s)) {
                        return false;
                    }

                    //Check duplicate pegs
                    if (s != null) {
                        String peg1 = subset.substring(0, 1);
                        String peg2 = s.substring(0, 1);
                        if (peg1.equals(peg2)) {
                            switch (peg1) {
                                case "i":
                                    redCount = redCount + 1;
                                    break;
                                case "j":
                                    blueCount = blueCount + 1;
                                    break;
                                case "k":
                                    greenCount = greenCount + 1;
                                    break;
                                case "l":
                                    yellowCount = yellowCount + 1;
                                    break;
                            }

                            //Too many certain colour pegs on board
                            if (redCount > 1 || blueCount > 2 || greenCount > 2 || yellowCount > 2) {
                                return false;
                            }
                        }
                    }
                }
                //Add set of 4 to sets array
                setsArr[index] = subset;
                index = index + 1;

                //Check if they are in alphabetical order (relative to character in the next 4 set)
                //Ensure the first character of the next set of 4 is greater than the current one
                if (placement.length() - i > 4 && (int) placementArr[i] > (int) placementArr[i + 4]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    /**
     * Determine whether a placement string is valid.  To be valid, the placement
     * string must be well-formed and each piece placement must be a valid placement
     * according to the rules of the game.
     * - pieces must be entirely on the board
     * - pieces must not overlap each other
     * - pieces may only overlap pegs when the a) peg is of the same color and b) the
     * point of overlap in the piece is a hole.
     *
     * @param placement A placement sequence string
     * @return True if the placement sequence is valid
     */
    //Task 5 written mostly by Saffi Bannister with input from Lauren Nelson-Lee
    public static boolean isPlacementStringValid(String placement) {
        // FIXED Task 5: determine whether a placement string is valid
        //Iterate through each piece and add to 'board'
        //Board is an array of integers: 0 = empty space, -1 = green peg, -2 = blue peg, -3 = red peg, -4 = yellow peg,
        //1 = occupied full, 2 = occupied empty/hole
        if(isPlacementStringWellFormed(placement)){
            //Split into piece and peg encoded strings
            BoardState placementState = new BoardState();
            String piecesEncoding = placementState.getPieceString(placement);
            String pegsEncoding = placementState.getPegString(placement);
            return placementState.allLegal(piecesEncoding,pegsEncoding);
        }
        return false;
    }

  /**
   * Given a string describing a placement of pieces and pegs, return a set
   * of all possible next viable piece placements.   To be viable, a piece
   * placement must be a valid placement of a single piece.  The piece must
   * not have already been placed (ie not already in the placement string),
   * and its placement must be valid.   If there are no valid piece placements
   * for the given placement string, return null.
   *
   * When symmetric placements of the same piece are viable, only the placement
   * with the lowest rotation should be included in the set.
   *
   * @param placement A valid placement string (comprised of peg and piece placements)
   * @return An set of viable piece placements, or null if there are none.
   */

  //Task 6 written by Zhewen Li
  public static Set<String> getViablePiecePlacements(String placement) {
    // FIXED Task 6: determine the set of valid next piece placements
      String first = "abcdefgh";
      String second = "12345678";
      String third = "ABCD";
      String fourth = "01234567";

      Set<String> viablePiecePlacement = new HashSet<>();
      if(isPlacementStringWellFormed(placement)) {
          BoardState placementState = new BoardState();
          String piecesEncoding = placementState.getPieceString(placement);
          String pegsEncoding = placementState.getPegString(placement);

          // get encoding of each piece
          String[] onePiece = new String[piecesEncoding.length()/4];
          if(placementState.allLegal(piecesEncoding,pegsEncoding)){
              for(int i =0;i <piecesEncoding.length()/4;){
                  for(int k=0;k+4<=piecesEncoding.length();k+=4){
                      onePiece[i]=piecesEncoding.substring(k,k+4);
                      i++;
                  }
              }
          }

          //  get a string of shapes of piece that already been placed
          String s ="";
          for(int i=0;i<onePiece.length;i++){
              char now = onePiece[i].charAt(0);
              s += now;
          }

          // find which shape of the pieces have not been placed
          String notPlaced="";
          for(int i=0;i<first.length();i++){
              if(!s.contains(String.valueOf(first.charAt(i)))){
                  notPlaced += first.charAt(i);
              }
          }

          // get an arraylist of all the combinations of pieces with shape that haven't been placed
          ArrayList<String> allCombination = new ArrayList<>();
          for(char one : notPlaced.toCharArray()){
              for(char sec : second.toCharArray()){
                  for(char thi : third.toCharArray()){
                      for(char fou : fourth.toCharArray()){
                          String a = String.valueOf(one)+ String.valueOf(sec)+String.valueOf(thi)+String.valueOf(fou);
                          allCombination.add(a);
                      }
                  }
              }
          }

          // select all the possible placement of pieces from allCombination
          for(String n : allCombination){
              Piece possible = new Piece(n);
              if(placementState.legalPlacement(possible)){
                  viablePiecePlacement.add(n);
              }
          }
          if(viablePiecePlacement.size() != 0){
              // set symmetry
              List<String> templist = new ArrayList<>();
              for(String n:viablePiecePlacement){
                  if(n.charAt(0) != 'c'&& n.charAt(0) != 'h'){
                      if(viablePiecePlacement.contains(Symmetric.getSymmetric(n)) && !n.contentEquals(Symmetric.getSymmetric(n))){
                          templist.add(n);
                      }
                  }
                  if(n.charAt(0) == 'c'|| n.charAt(0) == 'h'){
                      if(viablePiecePlacement.contains(Symmetric.getSymmetric(n)) && !n.contentEquals(Symmetric.getSymmetric(n))){
                          templist.add(n);
                      }
                      if(viablePiecePlacement.contains(Symmetric.getStrictSymmetric(n)) && !n.contentEquals(Symmetric.getStrictSymmetric(n))){
                          templist.add(n);
                      }
                  }
              }
              // remove all the symmetric pieces
              viablePiecePlacement.removeAll(templist);
              return viablePiecePlacement;
          }
      }else{
          System.out.println("placement string not well formed");
          return null;
      }

      return null;
  }

    public static void main(String[] args) {
      /*
        String a = "c1A3d2A6e2C3f3C4g4A7h6D0i6B0j2B0j1C0k3C0l4B0l5C0";

        System.out.println(getViablePiecePlacements(a));


        BoardState place = new BoardState();
        String placement1 = "d2A6e2C3f3C2g4A7h6D0i6B0";
        String placement2 = "j2B0j1C0k3C0l4B0l5C0";
        place.placePiece(placement1);
        place.placePegs(placement2);

        Piece a = new Piece("c1A1");*/

        //d2A6e2C3f3C2g4A7h6D0i6B0j2B0j1C0k3C0l4B0l5C0

        //a7A7b6A7c1A3d2A6e2C3f3C2g4A7h6D0

        // expected: [a6B0b6C0c5A2d1B3e4A5f4C2g2B3h1A2, a6B0b6C0c5A2d1B5e4A5f4C2g2B5h1A2]
        //System.out.println(place.getPieceString(placement1+placement2));
        //System.out.println(getViablePiecePlacements(place.getPieceString(placement1+placement2)));
        //System.out.println(solutionList(placement));
        String s = "h6B1i2D0j2A0j6A0k8D0k1B0l7B0l1A0";
        System.out.println(getViablePiecePlacements(s));
        String[] strings = getSolutions(s);
        for (String st : strings) {
            System.out.println(st);
        }
    }

  /**
   * Return an array of all unique solutions for a given starting placement.
   *
   * Each solution should be a 32-character string giving the placement sequence
   * of all eight pieces, given the starting placement.
   *
   * The set of solutions should not include any symmetric piece placements.
   *
   * In the IQ-Twist game, valid challenges can have only one solution, but
   * other starting placements that are not valid challenges may have more
   * than one solution.  The most obvious example is the unconstrained board,
   * which has very many solutions.
   *
   * @param placement A valid piece placement string.
   * @return An array of strings, each 32-characters long, describing a unique
   * unordered solution to the game given the starting point provided by placement.
   */

  //Task 9 written by Saffi Bannister and Zhewen Li
  public static String[] getSolutions(String placement) {
    // FIXED Task 9: determine all solutions to the game, given a particular starting placement
      ArrayList<String> solutionsNoRepeats = Solutions.removeDupes(Solutions.solutionList(placement));
      //make an array of solutions
      Object[] solutions = solutionsNoRepeats.toArray();

      //make a copy of solutions as a String array
      String[] strSolutions = Arrays.copyOf(solutions, solutions.length, String[].class);

      return strSolutions;
  }

}
