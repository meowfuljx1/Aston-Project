package randomStrategy;

import java.util.Random;

public interface RandomProvider<T> {
    Random random = new Random();

    T[] getObjectArray(int length);
}
