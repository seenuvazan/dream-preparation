class MyCircularDeque {
    private final int[] data;
    private final int capacity;
    private int front;
    private int rear;
    private int size;

    public MyCircularDeque(int k) {
        this.capacity = k;
        this.data = new int[k];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }
    
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        // Move front backwards cyclically and store value
        front = (front - 1 + capacity) % capacity;
        data[front] = value;
        size++;
        return true;
    }
    
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        // Store value at rear and move rear forward cyclically
        data[rear] = value;
        rear = (rear + 1) % capacity;
        size++;
        return true;
    }
    
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        // Move front forward cyclically
        front = (front + 1) % capacity;
        size--;
        return true;
    }
    
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        // Move rear backward cyclically
        rear = (rear - 1 + capacity) % capacity;
        size--;
        return true;
    }
    
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return data[front];
    }
    
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        // The last element is right behind rear
        int lastIndex = (rear - 1 + capacity) % capacity;
        return data[lastIndex];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == capacity;
    }
}