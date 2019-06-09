public class LinkList<T> {

    private Node<T> head;
    private int length;

    // Initializes empty list
    public LinkList() {
        head = null;
        length = 0;
    }

    // Contains data of generic type T, and pointer to next node
    protected class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }

        public T getData() {
            return(this.data);
        }
    }

    // Returns last node based on head node
    public Node<T> findLast() {
        Node<T> last = this.head;
        while(last.next != null) {
            last = last.next;
        }
        return last;
    }

    public int getLength() {
        return(this.length);
    }

    // Returns data at last node based on head node
    public T getLast() {
        return(this.findLast().data);
    }

    // Returns node at given index
    public Node<T> get(int index) {
        Node<T> target = this.head;
        // First, ensure that index is within the bounds of the list
        if(index > -1 && this.length >= index) {
            // Traverse down the list 'index' times, from node to node
            for(int i=0;i < index;i++){
                target = target.next;
            }
            return(target);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    // Appends 'data' to end of list
    public void append(T data) {
        // Special case if list is empty
        if(this.head == null) {
            this.head = new Node<T>(data);
        // Otherwise, find the last, and link the nodes
        } else {
            this.findLast().next = new Node<T>(data);
        }
        this.length++;
    }

    // Removes a node at given index
    public void delete(int index) {
        // Special case for the deletion of the head
        if(index == 0) {
            this.head = this.head.next;
            length--;
        } else {
            // LinkList.get throws IndexOutOfBoundsException when checking
            // the List.next value of the last node so catch and set to null
            try {
                this.get(index - 1).next = this.get(index + 1);
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                this.get(index - 1).next = null;
            }
        }
    }

    // Prints and formats the contents of the list
    public void printList() {
        if (this.length > 0) {
            System.out.print("[" + this.head.getData());
            int i = 1;
            while (this.get(i) != null) {
                System.out.print(", " + this.get(i).getData());
                i++;
            }
            System.out.print("]\n");
        } else {
            System.out.println("[]\n");
        }
    }
}
