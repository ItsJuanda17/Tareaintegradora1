package collections;

import collections.dataStructures.Stack;
import collections.interfaces.IStack;

import exception.StackException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StackTest {

    private IStack<String> stack;

    public void setUp1() {
        stack = new Stack<>();
    }

    public void setUp2() {
        stack = new Stack<>();
        try {
            stack.push("item");
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
    // Test methods for size() method
    @Test
    public void testSizeEmpty() {
        //setup
        setUp1();

        // act
        int size = stack.size();

        // assert
        assertEquals(0, size);
    }

    @Test
    public void testSizeFilled() {
        // setup
        setUp2();

        // act & assert
        assertEquals(1, stack.size());
    }

    // Test methods for isEmpty() method
    @Test
    public void testIsEmptyEmpty() {
        // setup
        setUp1();

        // act & assert
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testIsEmptyFilled() {
        // setup
        setUp2();

        // act & assert
        assertFalse(stack.isEmpty());
    }

    // Test methods for push(E item) method

    @Test
    public void testPushEmpty() {
        // setup
        setUp1();
        boolean isExceptionThrow = false;
        String top = "";

        // act
        try {
            stack.push("item");
            top = stack.top();
        }catch (StackException e){
            isExceptionThrow = true;
        }
        // assert
        assertFalse(isExceptionThrow);
        assertEquals(1, stack.size());
        assertEquals("item", top);
    }

    @Test
    public void testPushFilled() {
        // setup
        setUp2();
        boolean isExceptionThrow = false;
        String top = "";
        int size = stack.size();

        // act
        try {
            stack.push("item");
            top = stack.top();
        }catch (StackException e){
            isExceptionThrow = true;
        }
        // assert
        assertFalse(isExceptionThrow);
        assertEquals(size+1, stack.size());
        assertEquals("item", top);
    }

    @Test
    public void testPushNull(){
        // setup
        setUp1();

        // act & assert
        assertThrows(StackException.class, () -> stack.push(null));
    }

    // Test methods for top() method

    @Test
    public void testTopEmpty() {
        // setup
        setUp1();
        boolean isExceptionThrow = false;

        // act
        try {
            stack.top();
        }catch (StackException e){
            isExceptionThrow = true;
        }
        // assert
        assertTrue(isExceptionThrow);
    }

    @Test
    public void testTopFilled() {
        // setup
        setUp2();
        boolean isExceptionThrow = false;
        String top = "";

        // act
        try {
            top = stack.top();
        }catch (StackException e){
            isExceptionThrow = true;
        }
        // assert
        assertFalse(isExceptionThrow);
        assertEquals("item", top);
    }

    // Test methods for pop() method

    @Test
    public void testPopEmpty() {
        // setup
        setUp1();
        boolean isExceptionThrow = false;

        // act
        try {
            stack.pop();
        }catch (StackException e){
            isExceptionThrow = true;
        }
        // assert
        assertTrue(isExceptionThrow);
    }

    @Test
    public void testPopFilled() {
        // setup
        setUp2();
        boolean isExceptionThrow = false;
        String top = "";
        int size = stack.size();

        // act
        try {
            top = stack.pop();
        }catch (StackException e){
            isExceptionThrow = true;
        }
        // assert
        assertFalse(isExceptionThrow);
        assertEquals("item", top);
        assertEquals(size-1, stack.size());
    }
}
