import java.util.ArrayDeque;
import java.util.Deque;

class MyQueue {
    private final Deque<Integer> inStack;
    private final Deque<Integer> outStack;

    public MyQueue() {
        inStack = new ArrayDeque<>();
        outStack = new ArrayDeque<>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        shiftElements();
        return outStack.pop();
    }

    public int peek() {
        shiftElements();
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void shiftElements() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }
}