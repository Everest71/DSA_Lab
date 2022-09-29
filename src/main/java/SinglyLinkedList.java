import java.util.NoSuchElementException;
import java.util.zip.ZipFile;

public class SinglyLinkedList {
    Node head;
    Node tail;
    int size;

    /**
     * Creates an empty SinglyLinkedList.
     */
    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Prints the contents of this list.
     * @throws NoSuchElementException if this list is empty.
     */
    public void printList() {
        if (size == 0) {
            System.out.println("This list is empty!");
            return;
        }

        Node current = head; //This will always be non-null when the list is at least size 1

        //This loop will always run at least once, and stops when at the end of the list
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }

        System.out.println("end.");
    }

    /**
     * Clears the contents of this list.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Adds a new Node to the start of this list.
     * @param data a valid String that will be used to create the Node.
     */
    public void addFirst(String data) {
        if (size == 0) {
            //If empty, the new node is both the head and tail now.
            Node newNode = new Node(data, null);
            head = newNode;
            tail = newNode;
        } else {
            //If non-empty, the new node points at the existing head and becomes the new head after.
            Node newNode = new Node(data, head);
            head = newNode;
        }
        ++size; //Either way, we will successfully add a Node, so deferring this until here saves a line.
    }

    /**
     * Adds a new Node to the end of this list.
     * @param data a valid String that will be used to create the Node.
     */
    public void addLast(String data) {
        Node newNode = new Node(data, null);
        if (size == 0) {
            //If empty, the new node is both the head and tail now.
            head = newNode;
        } else {
            //If non-empty, the new node becomes the new tail after.
            tail.next = newNode;
        }
        tail = newNode;
        ++size; //Either way, we will successfully add a Node, so deferring this until here saves a line.
    }

    /**
     * Adds a new Node at the specified index.
     * @param index a valid Integer representing the index the new Node will be added at.
     * @param data a valid String that will be used to create the Node.
     * @throws IndexOutOfBoundsException if the provided index is out of the bounds of this list.
     */
    public void xadd(int index, String data) {
        if (index > (size - 1)) {
            throw new IndexOutOfBoundsException("This Index is Out Of Bounds!");
        }
        else if (index == 0){
            addFirst(data);
        }
        else if (index == (size-1)){
            addLast(data);
        }
        Node current = head;

        for (int i = 0; i < size; i++) {
            if (i==(index-1)){
                current.next =  new Node(data,current.next);
            }
            current = current.next;
        }
        size++;
    }

    /**
     * Returns (but doesn't remove) the first Node of this list.
     * @return The first Node of this list.
     * @throws NoSuchElementException if this list is empty.
     */
    public Node getFirst() {
        if(size == 0) {
            throw new NoSuchElementException("This list is empty!");
        } else {
            return head;
        }
    }

    /**
     * Returns (but doesn't remove) the last Node of this list.
     * @return The last Node of this list.
     * @throws NoSuchElementException if this list is empty.
     */
    public Node getLast() {
        if(size == 0) {
            throw new NoSuchElementException("This list is empty!");
        } else {
            return tail;
        }
    }

    /**
     * Returns (but doesn't remove) the Node at the given index of this list.
     * @param index a valid Integer representing the index of the element that will be returned.
     * @throws NoSuchElementException if this list is empty.
     * @throws IndexOutOfBoundsException if the provided index is out of the bounds of this list.
     */
    public Node get(int index) {
        if (size == 0) {
            throw new NoSuchElementException("This list is empty!");
        }else if (index>(size-1)){
            throw new IndexOutOfBoundsException("This Index is Out Of Bounds!");
        }else if (index==0){
            return(getFirst());
        }else if (index==(size-1)){
            return(getLast());
        }

        Node current = head;

        for (int i = 0; i < size; i++) {
            if (i==index){
                return(current);
            }

            current = current.next;
        }

        return null;
    }

    /**
     * Removes (but doesn't return) the first Node of this list.
     * @throws NoSuchElementException if this list is empty.
     */
    public void removeFirst() {
        if (size == 0) {
            //If empty, throw a new exception to ensure there's no illegal actions performed.
            throw new NoSuchElementException("This list is empty!");
        } else if (size == 1) {
            //Removing from a list of size 1 is the same as clearing it, so this saves a bit of repetition.
            clear();
        } else {
            //Otherwise, all we need to do is shift the head to be the next node in the list.
            //This will only result in a null error if the list is size 0-1, which we already handled above.
            head = head.next;
            --size;
        }
    }

    /**
     * Removes (but doesn't return) the last Node of this list.
     * @throws NoSuchElementException if this list is empty.
     */
    public void removeLast() throws NoSuchElementException {
        if (size == 0) {
            //If empty, throw a new exception to ensure there's no illegal actions performed.
            throw new NoSuchElementException("This list is empty!");
        } else if (size == 1) {
            //Removing from a list of size 1 is the same as clearing it, so this saves a bit of repetition.
            clear();
        } else {
            //Removing from the end of a singly linked list is a bit more involved, because we can't go backwards.
            //We need to traverse the list to find the second to last node and remove the tail from its perspective.
            //If this was a doubly linked list we could consistently do this faster by referring to tail.prev.
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }

            current.next = null;
            tail = current;
            --size;
        }
    }

    /**
     * Removes (but doesn't return) the Node at the specified index.
     */
    public void remove(int index) throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("This list is empty!");
        }else if (index>(size-1)){
            throw new IndexOutOfBoundsException("This Index is Out Of Bounds!");
        }else if (index==0){
            removeFirst();
        }else if (index==(size-1)){
            removeLast();
        }

        Node current = head;

        for (int i = 0; i < size; i++) {
            if (i==(index-1)){
                current.next = current.next.next;
                break;
            }

            current = current.next;
        }
        size--;
    }
}