package com.enricojr.stdlib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.enricojr.stdlib.sequences.Stack;

public class TestStack {

    @Test
    public void testStackPush() {
        Stack<Integer> stack = new Stack<>(Integer.class);
        stack.push(5);
        stack.push(6);
        stack.push(7);

        assertEquals(3, stack.len());
    }

    @Test
    public void testStackPop() {
        Stack<Integer> stack = new Stack<>(Integer.class);
        stack.push(5);
        stack.push(6);
        stack.push(7);

        Integer i = stack.pop();
        Integer j = stack.pop();
        Integer k = stack.pop();
        assertEquals(7, i);
        assertEquals(6, j);
        assertEquals(5, k);
        assertEquals(0, stack.len());
   }

   @Test
   public void testStackPeek() {
        Stack<Integer> stack = new Stack<>(Integer.class);
        stack.push(5);
        stack.push(6);
        stack.push(7);

        Integer i = stack.peek();
        assertEquals(7, i);
        assertEquals(3, stack.len());
   }
}
