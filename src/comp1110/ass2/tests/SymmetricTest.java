package comp1110.ass2.tests;

/* created by Zhewen Li 26.9.2018 for test symmetric */
import comp1110.ass2.Symmetric;

import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SymmetricTest {
    @Test
    void testNoSymmetric() {
        String a = "d1A4";
        String b = "a7A7";
        String c = "g1C3";
        assertEquals("d1A4",Symmetric.getSymmetric(a));
        assertEquals("a7A7",Symmetric.getSymmetric(b));
        assertEquals("g1C3",Symmetric.getSymmetric(c));
    }

    @Test
    void testWeak() {
        String a = "b1C2";
        String b = "e1A5";
        String c = "f1E5";
        assertEquals("b1C0",Symmetric.getSymmetric(a));
        assertEquals("e1A2",Symmetric.getSymmetric(b));
        assertEquals("f1E3",Symmetric.getSymmetric(c));

    }

    @Test
    void testStrict() {
        String a = "c1C4";
        String b = "c3A5";
        String c = "h7A7";
        String d = "h6A6";
        assertEquals("c1C0",Symmetric.getStrictSymmetric(a));
        assertEquals("c3A1",Symmetric.getStrictSymmetric(b));
        assertEquals("h7A3",Symmetric.getStrictSymmetric(c));
        assertEquals("h6A2",Symmetric.getStrictSymmetric(d));

    }

    @Test
    void testList() {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("c1A0");
        list1.add("c1A4");
        list1.add("e3A0");
        list1.add("f4A2");
        list1.add("f4A4");
        list1.add("b6C3");
        list1.add("b6C1");
        list1.add("b6C0");
        list1.add("g2D0");

        ArrayList<String> list2 = new ArrayList<>();
        list2.add("c1A0");
        list2.add("e3A0");
        list2.add("f4A2");
        list2.add("b6C1");
        list2.add("b6C0");
        list2.add("g2D0");

        ArrayList<String> temp1 = new ArrayList<>();
        for(String s: list1){
            if(list1.contains(Symmetric.getSymmetric(s)) && s!=Symmetric.getSymmetric(s)){
                temp1.add(s);
            }
        }
        list1.removeAll(temp1);

        assertEquals(list2,list1);


    }
}
