package comp1110.ass2;

import java.util.ArrayList;

//Created by Saffron Bannister

public enum PieceShape {
    //Identifies holes, structure/shape of a piece

    /*
     1 2   3  4
     5 6   7  8
     9 10 11 12
     */

    /*
    a b c d
    e f g h
    i j k l
    m n o p
     */

    //Enum fields of each piece type encoded into a string representing how the piece would fit onto the above board with respect to the top left corner
    //Capital letters represent holes in the piece
    //Written by Lauren Nelson-Lee and Zhewen Li
    a (new String[] {"AbCg", "BfJi", "aEfG", "AbeI", "EfGc", "AeIj", "eAbC", "aBfJ"}),
    b (new String[] {"abFg", "bfEi", "aBfg", "bFei", "cBfe", "aeFj", "eFbc", "aEfj"}),
    c (new String[] {"aBcd", "aEim", "abCd", "aeIm", "aBcd", "aEim", "abCd", "aeIm"}),
    d (new String[] {"abcFG", "bfjIE", "ABefg", "aBeFi", "BCefg", "aeFiJ", "abcEF", "AbEfj"}),
    e (new String[] {"aBF", "EFb", "AEf", "eAB", "eFB","aEF","EAb","ABf"}),
    f (new String[] {"abCF", "EbfJ","EBfg","AeiF","efBG","aeIF","AbcF","BEfj"}),
    g (new String[] {"AEfgJ","EBCfj","BefGK","bfGIJ","EIBfg","ABfjG","efGCJ","EbfJK"}),
    h (new String[] {"Abc","Aei","abC","aeI","Abc","Aei","abC","aeI"});

    String[] rotations;

    PieceShape(String[] rotations) {
        this.rotations = rotations;
    }

    //Method to get string from String array depending on which rotation it is at
    public String getRotatedPiece(int rotation){
        return rotations[rotation];
    }

    //get enum from char
    public static PieceShape getShape(char ch) {
        switch (ch) {
            case 'a': return a;
            case 'b': return b;
            case 'c': return c;
            case 'd': return d;
            case 'e': return e;
            case 'f': return f;
            case 'g': return g;
            case 'h': return h;
        }
        return null;
    }

    public String getColor(){
        switch(this){
            case a: case b: return "red";
            case c: case d: return "blue";
            case e: case f: return "green";
            case g: case h: return "yellow";
        }
        return null;
    }


    @Override
    public String toString() {
        return this.name()+ ", a " + getColor() + " piece";
    }


}
