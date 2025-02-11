package fromFileStrategy;

public interface FromFileProvider<T> {
    T[] getFileArray(String path, int length);
    boolean validate(String fileName);
    boolean validate(Object builder);
}
