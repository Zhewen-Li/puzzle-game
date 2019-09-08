package comp1110.ass2;
// created for TwistGame task 6 by Zhewen Li

import java.util.ArrayList;

public class Symmetric {

    public static String getSymmetric(String piece){
        switch(piece.charAt(0)){
            case 'a': return piece;
            case 'b':
                switch(piece.charAt(3)){
                    case '2': return piece.substring(0,3)+'0';
                    case '3': return piece.substring(0,3)+'1';
                    case '6': return piece.substring(0,3)+'4';
                    case '7': return piece.substring(0,3)+'5';
                    default: return piece;
                }
            case 'c': case 'h':
                switch(piece.charAt(3)){
                    case '2': case '4':case '6':return piece.substring(0,3)+'0';
                    case '3': case '5': case '7': return piece.substring(0,3)+'1';
                }
            case 'd': return piece;
            case 'e':
                switch(piece.charAt(3)) {
                    case '4': return piece.substring(0, 3) + '1';
                    case '5': return piece.substring(0, 3) + '2';
                    case '6': return piece.substring(0, 3) + '3';
                    case '7': return piece.substring(0, 3) + '0';
                    default: return piece;
                }
            case 'f':
                switch(piece.charAt(3)) {
                    case '4': return piece.substring(0, 3) + '2';
                    case '5': return piece.substring(0, 3) + '3';
                    case '6': return piece.substring(0, 3) + '0';
                    case '7': return piece.substring(0, 3) + '1';
                    default : return piece;
                }
            case 'g': return piece;
            default: return piece;
        }

    }

    public static String getStrictSymmetric(String piece){
        switch(piece.charAt(0)){
            case 'c': case 'h':
                switch(piece.charAt(3)){
                    case '4': return piece.substring(0,3)+'0';
                    case '5': return piece.substring(0,3)+'1';
                    case '6': return piece.substring(0,3)+'2';
                    case '7': return piece.substring(0,3)+'3';
                }
        }
        return piece;

    }

    //test
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("a6A0");
        list.add("a6A2");
        list.add("a6A4");
        list.add("a6A7");
        list.add("a6B0");
        list.add("a7A1");
        list.add("a7A3");
        list.add("a7A5");
        list.add("a7A7");
        list.add("b6A0");
        list.add("b6A2");
        list.add("b6A7");
        list.add("b7A1");
        list.add("b7A3");
        list.add("b7A5");
        list.add("b7A7");

        ArrayList<String> temp1 = new ArrayList<>();
        for(String s: list){
            if(list.contains(getSymmetric(s)) && s!=getSymmetric(s)){
                temp1.add(s);

            }
        }
        list.removeAll(temp1);
        System.out.println(temp1);
        System.out.println(list);
    }
// a6A0, a6A2, a6A4, a6A7, a6B0, a7A1, a7A3, a7A5, a7A7, b6A0, b6A7, b7A1, b7A5

}
