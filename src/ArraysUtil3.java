import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

// Utility-class
public final class ArraysUtil3 {
    // Запрещаем создание экземпляра класса
    private ArraysUtil3() {
        throw new UnsupportedOperationException("Нельзя создавать экземпляр TimSort, используйте статические методы...");
    }

    private static final int RUN = 32; // Минимальный размер подмассива

    // Основной метод сортировки
    public static <T> void sort(T[] array, Comparator<? super T> comparator, Function<T, Integer> f) {

        int age = f.apply(array[1]);
        if (age % 2 == 0){
            
        } else {

        }


        int n = array.length;

        // Сортируем отдельные RUN-блоки вставками
        for (int i = 0; i < n; i += RUN) {
            insertionSort(array, comparator, i, Math.min(i + RUN - 1, n - 1));
        }

        // Сортируем слиянием увеличивающимися блоками
        for (int size = RUN; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);

                if (mid < right) {
                    merge(array, comparator, left, mid, right);
                }
            }
        }
    }

    // Сортировка вставками (для небольших RUN-блоков)
    private static <T> void insertionSort(T[] array, Comparator<? super T> comparator, int left, int right) {
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

    // Слияние двух отсортированных подмассивов
    private static <T> void merge(T[] array, Comparator<? super T> comparator, int left, int mid, int right) {
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

    // бинарный поиск
    public static <T> int binarySearch(T[] array, T target, Comparator<? super T> comparator) {
        int low = 0, mid, high = array.length - 1;
        T guess;
        int compareResult;

        while (low <= high) {
            mid = low + (high - low) / 2;
            guess = array[mid];
            compareResult = comparator.compare(guess, target);

            if (compareResult == 0) return mid;
            else if (compareResult  > 0) high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }
}