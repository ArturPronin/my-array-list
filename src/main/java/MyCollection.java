import java.util.Comparator;

public interface MyCollection<T> extends Iterable<T> {
    void add(T element);

    T get(int index);

    T remove(int index);

    int size();

    void update(int index, T element);

    void addAll(MyCollection<? extends T> collection);

    void sort();

    /**
     * Здесь применяем ключевое слово "extends"
     * Ограничение T extends Comparable<? super T> - значит, что тип "T" должен реализовывать интерфейс Comparable для "T" или его суперклассов
     * Это гарантирует, что любые два элемента типа T могут быть корректно сравнены
     */
    static <T extends Comparable<T>> void staticSort(MyCollection<T> collection) {
        MyArrayList<T> list = (MyArrayList<T>) collection;
        list.sort();
    }

}
