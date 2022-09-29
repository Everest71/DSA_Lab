public class Node {
    String data;
    Node next;

    /**
     * Creates a new Node with the data provided.
     * @param newData a valid String that represents the data this Node holds.
     * @param newNext a valid Node reference that represents the next Node in a Linked List of Nodes.
     */
    public Node(String newData, Node newNext) {
        data = newData;
        next = newNext;
    }

    @Override
    public String toString() {
        return data;
    }
}