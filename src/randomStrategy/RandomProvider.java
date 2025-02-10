package randomStrategy;

import java.util.Random;

public interface RandomProvider<T> {
    Random random = new Random();

    T[] getRandomArray(int length);
}
