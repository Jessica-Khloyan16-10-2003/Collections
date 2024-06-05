package LinkedList;

public class Main {
    public static void main(String[] args) {
       LinkedListCollection<String> linkedListCollection = new LinkedListCollection<String>();
       linkedListCollection.add("1");
       linkedListCollection.add("2");
       linkedListCollection.add("3");
       linkedListCollection.set(0,"6");
       linkedListCollection.show();
       LinkedListCollection.sort(linkedListCollection);
       linkedListCollection.show();

    }
}
