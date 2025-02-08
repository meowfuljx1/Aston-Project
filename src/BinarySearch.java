import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BinarySearch {
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
}
