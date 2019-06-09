import java.util.Vector;

public class Stack {

    Board[] stack;
    int size;
    int top = -1;

    //Overloaded constructor accepts initial size param
    public Stack(int size) {
        this.stack = new Board[size];
        this.size = size;
    }

    public boolean isEmpty() {
        return(this.top == -1);
    }

    // Peek returns the top element of stack if not empty
    public Board peek() {
        if(!this.isEmpty()) {
            return(this.stack[(this.top)]);
        } else {
            throw new RuntimeException("Empty Stack");
        }
    }

    public void push(Board element) {
        if(this.top >= (size-1)){
            throw new RuntimeException("Full Stack");
        } else {
            stack[++top] = element;
        }
    }

    public void pop() {
        if(top < 0) {
            throw new RuntimeException("Empty stack");
        } else {
            this.stack[top--] = null;
        }
    }


}
