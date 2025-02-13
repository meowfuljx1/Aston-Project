package fromFileStrategy;
import java.util.Scanner;

public interface FromFileProvider<T> {
    T[] getFileArray(Scanner scanner, int length);
    boolean validate(String[] parts);
}
