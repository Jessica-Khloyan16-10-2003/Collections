import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainBubble {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(14);
        list.add(0);
        System.out.println(list);

        bubbleSort(list);

        System.out.println(list);
    }
    public static <T extends Comparable<T>> void bubbleSort(List<T> list) {
        boolean swapped;
        int n = list.size();

        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                    T temp = list.get(i - 1);
                    list.set(i - 1, list.get(i));
                    list.set(i, temp);
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
    }

}
