package manualStrategy;

import java.util.Scanner;

public interface ManualProvider <T>{
    T[] getManualArray(Scanner scanner, int length);
}
