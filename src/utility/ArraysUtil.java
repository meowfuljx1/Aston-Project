package utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

// Utility-class
public final class ArraysUtil {
    private static final int RUN = 32; // Минимальный размер подмассива
    private static String path; // путь файла

    // Запрещаем создание экземпляра класса
    private ArraysUtil() {
        throw new UnsupportedOperationException("Нельзя создавать экземпляр TimSort, используйте статические методы...");
    }

    // бинарный поиск (Comparable)
    public static <T extends Comparable<T>> int binarySearch(T[] array, T target) {
        return binarySearch(array, target, null);
    }

    // бинарный поиск (Comparator)
    public static <T> int binarySearch(T[] array, T target, Comparator<T> c) {
        int low = 0, mid, high = array.length - 1;
        T guess;
        int compareResult, res = -1;

        while (low <= high) {
            mid = low + (high - low) / 2;
            guess = array[mid];

            compareResult = (c == null) ?
                    ((Comparable<T>) guess).compareTo(target) :
                    c.compare(guess, target);

            if (compareResult == 0) {
                res = mid;
                break;
            } else if (compareResult > 0) high = mid - 1;
            else low = mid + 1;
        }
        writeToFile(target, res);
        return res;
    }

    // TimSort, Comparator, основной метод сортировки
    public static <T> void sort(T[] array, Comparator<T> c, Scanner scanner) {
        int n = array.length;

        // Сортируем отдельные RUN-блоки вставками
        for (int i = 0; i < n; i += RUN) {
            insertionSort(array, c, i, Math.min(i + RUN - 1, n - 1));
        }

        // Сортируем слиянием увеличивающимися блоками
        for (int size = RUN; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);

                if (mid < right) {
                    merge(array, c, left, mid, right);
                }
            }
        }
        writeToFile(array, scanner);
    }

    // TimSort, Comparator, сортировка вставками (для небольших RUN-блоков)
    private static <T> void insertionSort(T[] array, Comparator<T> comparator, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            T key = array[i];
            int j = i - 1;

            while (j >= left && comparator.compare(array[j], key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    // TimSort, Comparator, слияние двух отсортированных подмассивов
    private static <T> void merge(T[] array, Comparator<T> comparator, int left, int mid, int right) {
        int len1 = mid - left + 1;
        int len2 = right - mid;

        T[] leftArray = Arrays.copyOfRange(array, left, mid + 1);
        T[] rightArray = Arrays.copyOfRange(array, mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        while (i < len1 && j < len2) {
            if (comparator.compare(leftArray[i], rightArray[j]) <= 0) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }

        while (i < len1) {
            array[k++] = leftArray[i++];
        }

        while (j < len2) {
            array[k++] = rightArray[j++];
        }
    }


    // TimSort, Comparable, основной метод сортировки
    public static <T extends Comparable<T>> void sort(T[] array, Scanner scanner) {
        int n = array.length;

        // Сортируем отдельные RUN-блоки вставками
        for (int i = 0; i < n; i += RUN) {
            insertionSort(array, i, Math.min(i + RUN - 1, n - 1));
        }

        // Сортируем слиянием увеличивающимися блоками
        for (int size = RUN; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);

                if (mid < right) {
                    merge(array, left, mid, right);
                }
            }
        }

        writeToFile(array, scanner);
    }

    // TimSort, Comparable, сортировка вставками (для небольших RUN-блоков)
    private static <T extends Comparable<T>> void insertionSort(T[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            T key = array[i];
            int j = i - 1;

            while (j >= left && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    // TimSort, Comparable, слияние двух отсортированных подмассивов
    private static <T extends Comparable<T>> void merge(T[] array, int left, int mid, int right) {
        int len1 = mid - left + 1;
        int len2 = right - mid;

        T[] leftArray = Arrays.copyOfRange(array, left, mid + 1);
        T[] rightArray = Arrays.copyOfRange(array, mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        while (i < len1 && j < len2) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }

        while (i < len1) {
            array[k++] = leftArray[i++];
        }

        while (j < len2) {
            array[k++] = rightArray[j++];
        }
    }

    // запись в файл после сортировки
    private static <T> void writeToFile(T[] array, Scanner scanner) {
        if (path == null){
            scanner.nextLine();
            path = Validator.validateFile("Сохранить результат в файл:", scanner);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write("Отсортированный массив: \n");
            for (T object : array)
                writer.write(object + "\n");
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл!");
        }
    }

    // запись в файл после бинарного поиска
    private static <T> void writeToFile(T target, int index) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write("\nБинарный поиск объекта: " + target + "\n");
            if (index == -1)
                writer.write("Объект отсутствует в массиве");
            else
                writer.write("Индекс объекта в массиве " + index + "\n\n");
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл!");
        }
    }
}