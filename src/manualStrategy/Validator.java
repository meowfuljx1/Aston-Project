package manualStrategy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public final class Validator {
    private static final Scanner scanner = new Scanner(System.in);

    private Validator() {
        throw new UnsupportedOperationException("Нельзя создавать экземпляр Validator, используйте статические методы...");
    }
    public static String validateString(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.next();
            if (input.matches("[a-zA-Zа-яА-Я]+")) {
                return input;
            }
            System.out.println("Ошибка: вводите только буквы.");
        }
    }

    public static boolean validateBoolean(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.next().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            }
            System.out.println("Ошибка: введите true или false.");
        }
    }

    public static double validateVolume(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double num = scanner.nextDouble();
                if (num > 0) return num;
            }
            scanner.nextLine();
            System.out.println("Ошибка: введите положительный объем.");
        }
    }
    public static int validatePositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                if (num > 0) return num;
            }
            scanner.nextLine();
            System.out.println("Ошибка: введите положительное число.");
        }
    }

    public static int validateAge(String prompt) {
        while (true) {
            int age = validatePositiveInt(prompt);
            if (age <= 100) return age;
            System.out.println("Ошибка: введите возраст от 0 до 100.");
        }
    }

    public static String validateGender(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.next().toLowerCase();
            if (input.equals("м") || input.equals("ж")) {
                return input;
            }
            System.out.println("Ошибка: введите 'м' или 'ж'.");
        }
    }

    public  static String validateFile(Scanner scanner) {
        System.out.println("Введите путь до файла:");
        Path path;
        while (true) {
            path = Path.of(scanner.nextLine());
            if (Files.exists(path) && Files.isReadable(path))
                return path.toString();
            else
                System.out.println("Файл не существует по данному пути или к нему нет доступа\nВведите другой:");
        }
    }
}
