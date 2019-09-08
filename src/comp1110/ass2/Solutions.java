package comp1110.ass2;


import java.util.*;

import static comp1110.ass2.TwistGame.getViablePiecePlacements;
import static comp1110.ass2.TwistGame.isPlacementStringValid;
// originally created by Saffron Bannister, mostly completed by Zhewhen Li

public class Solutions {
    //Class to determine possible solutions for current challenge

    public static ArrayList<String> solutionList (String placement) {
        //System.out.println(placement);
        BoardState boardState = new BoardState();
        String piecePlacement = boardState.getPieceString(placement);
        String pegPlacement = boardState.getPegString(placement);
        boardState.placePegs(pegPlacement);
        if (!Objects.equals(piecePlacement, "")) {
            boardState.placePiece(piecePlacement);
        }


        ArrayList<String> solution = new ArrayList<>();
        ArrayList<String> pegs =new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        Set<String> nexts = getViablePiecePlacements(placement);
        //System.out.println(nexts);

        if (nexts != null ) {
            for (String p : nexts) {
                String s = placement + p;
                String n = boardState.getPieceString(s);
                solution.addAll(solutionList(n+pegPlacement));
            }
        }

        if(piecePlacement.length()==32 ){
            solution.add(piecePlacement);
        }

        if(pegPlacement!=""){
            pegs.add(pegPlacement);

            for(String s:solution){
                if(!isPlacementStringValid(s+pegs.get(0))){
                    System.out.println(s+pegs.get(0));
                    temp.add(s);
                }
            }
        }

        //System.out.println(temp);


        solution.removeAll(temp);


        return solution;

    }

    public static ArrayList<String> removeDupes (ArrayList<String> s) {
        ArrayList<String> result = new ArrayList<>(s);

        for(int i=0; i<result.size()-1; i++) {
            for (int j=i+1; j<result.size(); j++) {
                if(Objects.equals(result.get(i), result.get(j))) {
                    result.remove(j);
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        //using this to generate a list of all possible placements for use in pruning
        // wish me luck!
        /*

         */

        // - Saffi

        long startTime = System.currentTimeMillis();

        String a ="c1B2d4C4e1C3f4A0g6A1h1A0j3B0j5C0";
        System.out.println(getViablePiecePlacements(a));
        System.out.println("solutions: ");
        System.out.println(Arrays.toString(TwistGame.getSolutions(a)));

        long endTime = System.currentTimeMillis();

        System.out.println("That took " + (endTime - startTime) + " milliseconds");

    }

}
