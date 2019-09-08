package comp1110.ass2.tests;

import comp1110.ass2.Solutions;
import comp1110.ass2.TwistGame;

import java.util.ArrayList;
import java.util.Objects;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/** Created by Saffi on 24/09/18. */
public class TwistGameTest {

    @Test
    void testRemoveDupes () {
        ArrayList<String> start = new ArrayList<>();
        start.add("one");
        start.add("one");
        start.add("two");
        start.add("one");
        start.add("two");
        ArrayList<String> target = new ArrayList<>();
        target.add("one");
        target.add("two");

        assertFalse(Objects.equals(start, target));

        ArrayList<String> dupesRemoved = Solutions.removeDupes(start);

        assertTrue(Objects.equals(dupesRemoved,target));

    }
}
