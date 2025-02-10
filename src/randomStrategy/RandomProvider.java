package randomStrategy;

import java.util.Random;

public interface RandomProvider {
    Random random = new Random();
    Object[] getObjectArray(int length);
}
