//  1.  Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив, метод должен вернуть новый массив,
//      который получен путем вытаскивания элементов из исходного массива, идущих после последней четверки. Входной массив должен содержать
//      хотя бы одну четверку, в противном случае в методе необходимо выбросить RuntimeException.
//      Написать набор тестов для этого метода (варианта 3-4 входных данных)
//      вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ]
//  2.  Написать метод, который проверяет что массив состоит только из чисел 1 и 4. Если в массиве нет хоть одной 4 или 1, то метод вернет false;
//      Написать набор тестов для этого метода (варианта 3-4 входных данных)

package lesson6;

public class Main {
    int[] task1(int[] input) {
        int[] output;
        int index = -1;
        int length = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == 4) {
                index = i;
                length = 0;
            } else
                length++;
        }
        if (index == -1)
            throw new RuntimeException();
        output = new int[length];
        System.arraycopy(input, ++index, output, 0, length);
        return output;
    }

    boolean task2(int[] input) {
        boolean isThere1 = false;
        boolean isThere4 = false;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == 1) {
                isThere1 = true;
                continue;
            }
            if (input[i] == 4) {
                isThere4 = true;
                continue;
            }
            return false;
        }
        return isThere1 && isThere4;
    }
}
