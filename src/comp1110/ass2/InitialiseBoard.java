package comp1110.ass2;

import java.util.*;
import static comp1110.ass2.Placeable.splitIntoFours;
// Created by Saffron Bannister 04/10 2018

public class InitialiseBoard {
    /*
    "A game starts with a challenge which involves zero or one pieces and
    one or more pegs being placed.

    Notice that this particular challenge starts with one piece placed and
    six pegs placed.  Note that the more constrained the player is, the
    fewer options they have, and consequently the solution to the
    challenge is, in general, simpler.  For example, many of the 'Wizard'
    level challenges that come with the game (e.g. numbers 118-120) have
    just three pegs placed, which leaves the player with a large number of
    placements to choose from, and thus creates a much more challenging
    game.  On the other hand, some of the 'Starter' challenges
    (e.g. number 17) have all seven pegs and one piece placed,
    significantly reducing the player's options and consequently making
    the challenge far easier.


        Rules for initial placement:
         - contains at least 3 pegs
         - is solveable
         - one answer

         Pegs: red, blue, blue, green, green, yellow, yellow

         Challenge levels:
         0 (easy) : 7 pegs & 1 piece
         1 (medium) : either 7 or 6 pegs and no piece or 5 or 6 pegs and 1 piece
         2 (hard) : either 5 or 4 pegs and no piece or 4 or 3 pegs and 1 piece
         3 (superhard): 3 pegs & no pieces


        How it works:
         - take a goal string from the array of possible goals (private final string[] GOALS)
         - based on the challenge level, set how many pegs and whether a piece is used, based on the above
         - place the appropriate number of pegs where the holes are
         - if we want to use a piece, choose a random piece from the goal string

         */
    private static final String[] GOALS = {
            "a7A7b6A7c1A3d2A6e2C3f3C2g4A7h6D0","a6B0b6C0c5A2d1B3e4A5f4C2g2B3h1A2","a6A0b4A2c3A3d1A3e1C4f4B3g6B2h5D0",
            "a4C4b2C4c1B2d7B1e1C6f6A0g4A5h1A0","a7B1b2C4c1B2d4C4e1C3f4A0g6A1h1A0","a7B1b2C4c1B2d4C4e1C3f4A0g6A1h1A0",
            "a1B5b2C0c5A2d7B7e5B0f1A6g3A7h5D0","a1C6b6A6c2D0d7B1e1A3f2A2g4B2h4A2","a6C4b7A1c2D0d1A0e5B4f1B3g3A3h5A0",
            "a1A3b5A4c5C0d3A6e7A1f3C4g1B3h6D0","a7A7b3B5c3A0d1A3e5C2f1C4g6B7h4B0" };

    private static String goal;
    private  int min;
    private  int max;
    private  int difficulty;
    private int numPegs;
    private  boolean hasPiece;

    private String placement = "";

    public InitialiseBoard (int difficulty) {
        this.difficulty = difficulty;
        generateNumPegsAndBoolPiece();
    }
    public static String getGoal() {return goal;}
    public int getMin() { return  min;}
    public int getMax() { return  max;}
    public int getDifficulty() {return difficulty;}
    public boolean getHasPiece() {return hasPiece;}
    public String getPlacement() {return placement;}

    //generates the number of pegs and whether there's a piece
    public void generateNumPegsAndBoolPiece() {
        Random r = new Random();

        hasPiece = r.nextBoolean();
        if (difficulty == 0) {
            hasPiece = true;
        } else if (difficulty == 3) {
            hasPiece = false;
        }

        int[] diff = getMinMax();
        min = diff[0];
        max = diff[1];
        numPegs = r.nextInt((max - min) + 1) + min;
        System.out.println(numPegs + ", " + hasPiece);

        generateNewChallenge();
    }

    //generates the minimum and maxiumum based on the difficulty and whether there's a piece
    public int[] getMinMax() {
        switch (difficulty) {
            case 1: if (!hasPiece) {
                return new int[] {6,7};
            }
                return new int[] {5,6};
            case 2: if (!hasPiece) {
                return new int[] {4,5};
            }
                return new int[] {3,4};
            case 3: return new int[] {3,3};
        }
        return new int[] {7,7};
    }

    //generates a new challenge
    public void generateNewChallenge() {

        Random r = new Random();

        //start by picking a solution to aim for
        this.goal = GOALS[r.nextInt(GOALS.length)];

        BoardState b = new BoardState();
        b.setEncoding(goal);
        b.printBoard();
        System.out.println(goal);

        //this is the pegs we're picking from
        char[] pegs = "ijjkkll".toCharArray();
        //this is the pegs we're using
        char[] pegsUsing = new char[numPegs];
        //fill out the pegs we're using
        for (int i = 0; i < pegsUsing.length; i++) {
            //generate an int representing which one we're grabbing
            int grab = r.nextInt(pegs.length);
            //if it's already been grabbed, go to the next one (wraps around)
            while (pegs[grab] == 'X') {
                grab++;
                if (grab >= pegs.length) {grab = 0;}
            }
            //grab the peg and update pegs to represent that the peg has been grabbed
            pegsUsing[i] = pegs[grab];
            pegs[grab] = 'X';
        }

        Arrays.sort(pegsUsing);
        System.out.println(pegsUsing);

        String[] possiblePositions = {
                "1A", "2A", "3A", "4A","5A", "6A", "7A", "8A",
                "1B", "2B", "3B", "4B","5B", "6B", "7B", "8B",
                "1C", "2C", "3C", "4C","5C", "6C", "7C", "8C",
                "1D", "2D", "3D", "4D","5D", "6D", "7D", "8D",
        };

        // places where there are holes in the goal for red (0), blue (1), green (2) and yellow (3)
        ArrayList<Integer>[] options = new ArrayList[4];
        options[0] = b.getPegPositions('i');
        options[1] = b.getPegPositions('j');
        options[2] = b.getPegPositions('k');
        options[3] = b.getPegPositions('l');
        String pegString = "";
        for (char c : pegsUsing) {
            boolean set = false;
            while (!set) {
                int pos = 0;
                switch (c) {
                    case 'i': pos = options[0].get(r.nextInt(options[0].size())); break;
                    case 'j': pos = options[1].get(r.nextInt(options[1].size())); break;
                    case 'k': pos = options[2].get(r.nextInt(options[2].size())); break;
                    case 'l': pos = options[3].get(r.nextInt(options[3].size())); break;
                }

                System.out.println(pos);
                System.out.println(possiblePositions[pos]);
                String e = "";
                while (Objects.equals(possiblePositions[pos], "X")) {
                    System.out.println("already taken");
                    switch (c) {
                        case 'i': pos = options[0].get(r.nextInt(options[0].size())); break;
                        case 'j': pos = options[1].get(r.nextInt(options[1].size())); break;
                        case 'k': pos = options[2].get(r.nextInt(options[2].size())); break;
                        case 'l': pos = options[3].get(r.nextInt(options[3].size())); break;
                    }
                }
                e = String.valueOf(c)+possiblePositions[pos]+"0";
                possiblePositions[pos] = "X";
                System.out.println(e);
                pegString = pegString+e;
                System.out.println(pegString);
                set = true;
            }
        }

        /* pieces don't work with our board initialiser code but just fyi we are able to generate a starting piece
        if (hasPiece) {
            String[] option = splitIntoFours(goal);
            String using = option[r.nextInt(option.length)];
            pegString = using + pegString;
        }*/

        System.out.println(pegString);
        placement = pegString;
    }
}
