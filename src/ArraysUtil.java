import java.util.Arrays;

// Utility-class
public final class ArraysUtil {

    private static final int RUN = 32; // Минимальный размер подмассива

    // бинарный поиск
    public static <T extends Comparable<T>> int binarySearch(T[] array, T target) {
        int low = 0, mid, high = array.length - 1;
        T guess;
        int compareResult;

        while (low <= high) {
            mid = low + (high - low) / 2;
            guess = array[mid];
            compareResult = guess.compareTo(target);

            if (compareResult == 0) return mid;
            else if (compareResult  > 0) high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }

    // Запрещаем создание экземпляра класса
    private ArraysUtil() {
        throw new UnsupportedOperationException("Нельзя создавать экземпляр TimSort, используйте статические методы...");
    }

    // Основной метод сортировки
    public static <T extends Comparable<T>> void sort(T[] array) {
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
    }

    // Сортировка вставками (для небольших RUN-блоков)
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

    // Слияние двух отсортированных подмассивов
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
}