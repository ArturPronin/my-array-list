public class Main {
    public static void main(String[] args) {

        MyCollection<String> stringList = new MyArrayList<>();
        stringList.add("one");
        stringList.add("two");
        stringList.add("tree");
        stringList.add("four");
        stringList.add("five");
        stringList.add("six");
        System.out.println(stringList);

        stringList.remove(1);
        System.out.println("Удалили элемент с index=1 в списке: " + stringList);

        stringList.update(3, "5");
        System.out.println("Заменили значение элемента с index=3 в списке на \"5\": " + stringList);

        System.out.println("Получили элемент с index=5" + stringList.get(4));

        MyCollection<String> anotherList = new MyArrayList<>();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        stringList.add("5");
        stringList.add("6");

        stringList.addAll(anotherList);
        System.out.println("Добавили список \"anotherList\" в список \"stringList\": " + stringList);

        MyCollection<Integer> numberList = new MyArrayList<>();
        numberList.add(11);
        numberList.add(8);
        numberList.add(9);
        numberList.add(3);
        numberList.add(2);
        numberList.add(4);
        numberList.add(10);
        numberList.add(5);
        numberList.add(1);
        numberList.add(7);
        numberList.add(6);


        System.out.println("Список чисел до сортировки: " + numberList);
        numberList.sort();
        System.out.println("Отсортированный список чисел: " + numberList);

        MyCollection.staticSort(numberList);
        System.out.println("Отсортированный список чисел при помощи static метода staticSort: " + numberList);

        MyCollection<String> stringList2 = new MyArrayList<>(stringList);
        System.out.println("Список \"stringList2\", созданный на основе \"stringList\": " + stringList2);


    }
}
