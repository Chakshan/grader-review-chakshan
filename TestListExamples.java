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

    @Test(timeout = 500)
    public void testMergeLeftEnd() {
        List<String> right = Arrays.asList("a", "b", "c");
        List<String> left = Arrays.asList("a", "d");
        List<String> merged = ListExamples.merge(left, right);
        List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
        assertEquals(expected, merged);
    }

    @Test(timeout = 500)
    public void testMergeMultipExecution() {
	List<String> r1 = Arrays.asList("a", "b");
	List<String> l1 = Arrays.asList("c", "d");
	List<String> r2 = Arrays.asList("a");
	List<String> l2 = Arrays.asList("b");
	List<String> expect1 = Arrays.asList("a", "b", "c", "d");
	List<String> expect2 = Arrays.asList("a", "b");
    }

    @Test(timeout = 500)
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
    
    @Test(timeout = 500)
    public void testFilterMultipleExecution() {
	List<String> s1 = Arrays.asList("a", "b", "c");
	List<String> s2 = Arrays.asList("d", "a", "a");

	List<String> expect1 = Arrays.asList("a");
	List<String> expect2 = Arrays.asList("a", "a");

	List<String> result1 = ListExamples.filter(s1, new StringContains());
	List<String> result2 = ListExamples.filter(s2, new StringContains());

	assertEquals(expect1, result1);
	assertEquals(expect2, result2);
    }
}
