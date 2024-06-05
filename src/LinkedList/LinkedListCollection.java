package LinkedList;


public class LinkedListCollection<T> {
    private MyNode<T> head;
    private MyNode<T> tail;
    private int size = 0;

    public LinkedListCollection() {
        this.head = null;
        this.tail = null;
    }
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Отрицательный индекс");
        }
        if (index == 0) {
            MyNode<T> newNode = new MyNode<T>(element);
            newNode.nextElement = head;
            head = newNode;
            size++;
        } else if (index == size) {
            add(element); // добавляем элемент в конец списка
        } else {
            MyNode<T> current = getNode(index - 1);
            MyNode<T> newNode = new MyNode<>(element);
            MyNode<T> lastCurrentNext = current.nextElement;
            newNode.nextElement = current.nextElement;
            current.nextElement = newNode;
            newNode.previousElement = current;
            lastCurrentNext.previousElement = newNode;
            size++;
        }
    }

    public void remove(Object element) {
        MyNode<T> current = head;
        MyNode<T> previous = null;

        while (current != null) {
            if (current.element.equals(element)) {
                if (previous == null) {
                    head = current.nextElement;
                } else {
                    previous.nextElement = current.nextElement;
                }
                size--;
                return;
            }
            previous = current;
            current = current.nextElement;
        }
    }
    public void remove(int index) {
        MyNode<T> current = head;
        MyNode<T> previous = null;

        while (current != null) {
            if (current.element.equals(getElement(index))) {
                if (previous == null) {
                    head = current.nextElement;
                } else {
                    previous.nextElement = current.nextElement;
                }
                size--;
                return;
            }
            previous = current;
            current = current.nextElement;
        }
    }
    private MyNode<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Данного индекса не существует");
        }

        MyNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.nextElement;
        }
        return current;
    }
    public T getElement(int index) {
        return getNode(index).element;
    }
    public T set(int index, T element) {
        MyNode<T> oldValue = getNode(index);
        getNode(index).element = element;
        MyNode<T> cur = getNode(index);
        return oldValue.element;
    }
    public void addAll(LinkedListCollection<T> otherList) {
        if (otherList.head==null) {
            return;
        }
        tail.nextElement = otherList.head;
        otherList.head.previousElement = tail;
        tail = otherList.tail;
        size += otherList.size;
    }

    public boolean add(T element) {
        final MyNode<T> last = tail;
        final MyNode<T> newNode = new MyNode<T>(element, null,last);
        tail = newNode;
        if (head == null) {
            head = newNode;
        }
        else {
            last.nextElement = newNode;
        }
        size++;
        return true;
    }
    private static class MyNode<T> {
        T element;
        MyNode<T> nextElement;
        MyNode<T> previousElement;

        MyNode(T element, MyNode<T> nextElement,MyNode<T> previousElement) {
            this.element = element;
            this.nextElement = nextElement;
            this.previousElement = previousElement;
        }

        public MyNode(T element) {
            this.element = element;
        }
    }
    public void show() {
        for (int i = 0; i < size; i++) {
            System.out.print(getNode(i).element + " ");
        }
    }
    public static <T extends Comparable<? super T>> void sort(LinkedListCollection<T> list) {
        for (int i = 0; i < list.size; i++) {
            for (int j = i + 1; j < list.size; j++) {
                if (list.getNode(i).element.compareTo(list.getNode(j).element) > 0) {
                    T temp = list.getNode(i).element;
                    list.set(i, list.getNode(j).element);
                    list.set(j, temp);
                }
            }
        }
    }
}
