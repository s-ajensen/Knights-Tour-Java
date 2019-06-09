import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {

    @Test
    public void pushTest() {
        Stack stack = new Stack(64);

        stack.push(new Board());
        stack.push(new Board());
        stack.push(new Board());
        stack.push(new Board());
        stack.push(new Board());
        stack.push(new Board());

        System.out.println(stack.peek());
        stack.pop();
        System.out.println(stack.peek());
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack.peek());
    }

    @Test
    public void pushTestAgain() {
        Stack stack = new Stack(64);

        stack.push(new Board());
        stack.push(new Board());

        System.out.println(stack.peek());

        stack.pop();
        System.out.println(stack.peek());
    }
}