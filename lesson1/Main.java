package lesson1;

import java.util.ArrayList;
import java.util.Arrays;

// Задача 3
abstract class Fruit {}

class Apple extends Fruit {
    static float weight = 1.0f;
}

class Orange extends Fruit {
    static float weight = 1.5f;
}

class Box<T extends Fruit> {
    float oneFruitWeight;
    ArrayList<T> box = new ArrayList<>();

    Box(float weight) {
        oneFruitWeight = weight;
    }

    void add(T fruit) {
        box.add(fruit);
    }

    float getWeight() {
        return box.size() * oneFruitWeight;
    }

    boolean compare(Box<?> anotherBox) {
        return getWeight() == anotherBox.getWeight();
    }

    void pepper(Box<T> anotherBox) {
        for (int i = 0; i < box.size(); i++)
            anotherBox.add(box.get(i));
        box.removeAll(box);
    }
}

public class Main {
//  Задача 1
    static <T> void changeElements(T[] array, int index1, int index2) {
        T tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

//  Задача 2
    static <T> ArrayList<T> arrayToArrayList(T[] array) {
        ArrayList<T> list = new ArrayList<T>(Arrays.asList(array));
        return list;
    }
}