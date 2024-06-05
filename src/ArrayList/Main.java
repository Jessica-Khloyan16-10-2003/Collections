package ArrayList;


public class Main {
    public static void main(String[] args) {
        ArrayCollection<String> anotherArray2 = new ArrayCollection<String>();
        anotherArray2.add("а");
        anotherArray2.add("А");
        ArrayCollection.sort(anotherArray2);
        System.out.println(anotherArray2);
    }
}
