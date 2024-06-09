import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class MyArrayList<T> implements MyCollection<T> {
    private T[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity) {
        checkException(initialCapacity);
        this.elements = (T[]) new Object[initialCapacity];
    }

    /**
     * Здесь применяем ключевое слово "extends"
     * Ограничение MyCollection<? extends T> - означает, что можно передать любую коллекцию, содержащую элементы типа "T" или подтипов "T"
     * То есть гарантируется, что элементы коллекции будут типа "T" или подтипами "T" и могут быть присвоены массиву T[]
     */
    public MyArrayList(MyCollection<? extends T> collection) {
        this.size = collection.size();
        this.elements = (T[]) new Object[size];
        int index = 0;
        for (T element : collection) {
            elements[index++] = element;
        }
    }


    public void add(T element) {
        T[] tempArray = elements;
        int minCapacity = size + 1;
        if (minCapacity > elements.length) {
            int newCapacity = elements.length + (elements.length + 1);
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;

            }
            elements = Arrays.copyOf(tempArray, newCapacity);
        }
        elements[size++] = element;
    }

    @Override
    public T get(int index) {
        checkException(index);
        return elements[index];
    }

    @Override
    public T remove(int index) {
        checkException(index);
        T[] tempArray = elements;
        elements = (T[]) new Object[size];
        System.arraycopy(tempArray, 0, elements, 0, index);
        int amountElementsAfterIndex = size - index - 1;
        if (amountElementsAfterIndex > 0) {
            System.arraycopy(tempArray, index + 1, elements, index, amountElementsAfterIndex);
        }
        elements[--size] = null;
        return elements[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void update(int index, T element) {
        checkException(index);
        elements[index] = element;
    }

    @Override
    public void addAll(MyCollection<? extends T> collection) {
        for (T element : collection) {
            add(element);
        }
    }

    @Override
    public void sort() {
        boolean sorted;
        for (int i = 0; i < size - 1; i++) {
            sorted = true;
            for (int j = 0; j < size - 1 - i; j++) {
                Comparable<? super T> comp = (Comparable<? super T>) elements[j];
                if (comp.compareTo(elements[j + 1]) > 0) {
                    T tempElement = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = tempElement;
                    sorted = false;
                }
            }
            if (sorted) break;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                return elements[currentIndex++];
            }
        };
    }

    private void checkException(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер массива: " + size);
        }
    }

    public String toString() {
        Iterator<T> it = iterator();
        if (!it.hasNext()) return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (; ; ) {
            T t = it.next();
            sb.append(t == this ? "(this Collection)" : t);
            if (!it.hasNext()) return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }
}
