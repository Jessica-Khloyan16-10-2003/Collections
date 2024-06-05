package ArrayList;

public interface ArrayMethods<T> {
    boolean add(T element);
    T get(int index);
    T remove(int index);
    T set(int index, T element);
    boolean addAll(ArrayCollection<T> anotherArrayCollection);
    int getSize();
}
