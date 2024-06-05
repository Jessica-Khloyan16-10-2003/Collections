package ArrayList;

import java.util.*;

//реализация ArrayList
public class ArrayCollection<T> extends ArrayList<T> implements ArrayMethods<T>{
    private static final Object[] EMPTY = {};
    private Object[] innerArray; //внутренний массив
    private int currentIndex = -1; //индекс текущего элемента
    private final static int DEFAULT_CAPACITY = 10; //емкость по умолчанию
    public ArrayCollection(int initialCapacity) { //конструктор, принимающий емкость
        if (initialCapacity>=0) {
            this.innerArray = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Указана неверная емкость");
        }
    }
    public ArrayCollection() { //конструктор по умолчанию, который за емкость принимает 10 (значение по дефолту)
        this.innerArray = new Object[DEFAULT_CAPACITY];
    }

        public ArrayCollection(Collection<? extends T> otherList) {
            this.innerArray = new Object[DEFAULT_CAPACITY];
            Object[] array;
            if (otherList.getClass()==ArrayCollection.class) {
                array = ((ArrayCollection<? extends T>) otherList).innerArray;
            } else {
                array = otherList.toArray();
            }
            if (array.length != 0) {
                if (otherList.size()>getSize()) {
                    resize();
                    System.arraycopy(array,0,innerArray,0,array.length);
                    currentIndex = array.length-1;
                } else {
                    System.arraycopy(array,0,innerArray,0,array.length);
                    currentIndex = array.length-1;
                }
            } else {
                innerArray = EMPTY;
            }

    }

    @Override
    public boolean add(T element) { //метод добавления элемента
        currentIndex++;
        if (currentIndex>=innerArray.length) {
            resize();
        }
        innerArray[currentIndex] = element;
        return true;
    }
    private void resize() { //метод, который увеличивает емкость коллекции, если прошлой емкости не хватает для добавляемого элемента
        int newCapacity = (innerArray.length * 3)/2 + 1;
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(this.innerArray,0,newArray,0,this.innerArray.length);
        innerArray = newArray;
    }

    private void resize(int capacity) { //метод, который увеличивает емкость коллекции, если прошлой емкости не хватает для добавляемого элемента (если нужная емкость известна)
        Object[] newArray = new Object[capacity];
        System.arraycopy(this.innerArray,0,newArray,0,this.innerArray.length);
        innerArray = newArray;
    }
    @Override
    public T get(int index) { //метод, который "берет" элемент, лежащий по указанному индексу
        return (T) innerArray[index];
    }
    @Override
    public T remove(int index) { //метод, удаляющий элемент
        Object cur;
        if (index>=this.innerArray.length || index<0) {
            throw new IllegalArgumentException("Такого индекса нет");
        } else {
            cur = this.innerArray[index];
            Object[] arrayPart = new Object[(this.innerArray.length-1)-index];
            System.arraycopy(this.innerArray,index+1,arrayPart,0,arrayPart.length);
            System.arraycopy(arrayPart,0,this.innerArray,index,arrayPart.length);
        }
        return (T) cur;
    }
    @Override
    public int getSize() { //метод, показывающий размер массива (именно размер, т. е. кол-во элементов, реально лежащих в коллекции на данный момент
        return ++currentIndex;
    }
    @Override
    public T set(int index, T element) { //изменение значение элемента
        T oldValue = getElement(index);
        innerArray[index] = element;
        return oldValue;
    }
    private T getElement(int index) { //достать значение элемента по индексу
        return (T) innerArray[index];
    }
    @Override
    public boolean addAll(ArrayCollection<T> anotherArrayCollection) { //метод, копирующий коллекцию, переданную в качестве параметра, в текущую коллекцию
        resize(anotherArrayCollection.innerArray.length+this.innerArray.length);
        System.arraycopy(Arrays.stream(anotherArrayCollection.innerArray).filter(Objects::nonNull).toArray(),0,this.innerArray,++currentIndex,++anotherArrayCollection.currentIndex);
        currentIndex = (anotherArrayCollection.currentIndex+currentIndex)-2;
        return true;
    }

    @Override
    public String toString() { //представление коллекции в виде строкового литерала
        return Arrays.toString(Arrays.stream(innerArray).filter(Objects::nonNull).toArray());
    }
    public static <T extends Comparable<? super T>> void sort(ArrayCollection<T> list) {
        for (int i = 0; i <= list.currentIndex; i++) {
            for (int j = i + 1; j <= list.currentIndex; j++) {
                if (list.get(i).compareTo(list.get(j)) > 0) {
                    T temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
}

