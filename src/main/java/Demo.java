public class Demo {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst("Test1");
        list.addLast("Test2");
        list.addLast("Test3");
        list.addLast("Test4");
        list.addLast("Test5");


        list.remove(2);
        list.printList();
    }
}
