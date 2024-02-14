import static org.junit.Assert.*;

import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class IsMoon implements StringChecker {
    public boolean checkString(String s) {
        return s.equalsIgnoreCase("moon");
    }
}

class StringContains implements StringChecker {
    public boolean checkString(String s) {
        return s.contains("a");
    }
}

public class TestListExamples {
    @Test(timeout = 500)
        public void testMergeRightEnd() {
        List<String> left = Arrays.asList("a", "b", "c");
        List<String> right = Arrays.asList("a", "d");
        List<String> merged = ListExamples.merge(left, right);
        List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
        assertEquals(expected, merged);
    }

    @Test
    public void testFilterMultipleMatch() {
        ArrayList<String> input = new ArrayList<>();
        input.add("a");
        input.add("ab");
        input.add("abc");
        input.add("bcd");

        ArrayList<String> expected = new ArrayList<>();
        expected.add("a");
        expected.add("ab");
        expected.add("abc");

        StringContains contains = new StringContains();
        List<String> output = ListExamples.filter(input, contains);
        assertEquals(expected, output);
    }
}
