package utility;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

public final class Validator {
    private Validator() {
        throw new UnsupportedOperationException("Нельзя создавать экземпляр Validator, используйте статические методы...");
    }

    public static String validateString(String prompt, Scanner scanner) {
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

    public static boolean validateBoolean(String prompt, Scanner scanner) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.next().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            }
            System.out.println("Ошибка: введите true или false.");
        }
    }

    public static double validateVolume(String prompt, Scanner scanner) {
        double num;
        while (true) {
            System.out.print(prompt);
            try {
                num = Double.parseDouble(scanner.next());
                if (num > 0) return num;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите положительный объем.");
                scanner.nextLine();
            }
        }
    }

    public static int validatePositiveInt(String prompt, Scanner scanner) {
        int num;
        while (true) {
            System.out.print(prompt);
            try {
                num = Integer.parseInt(scanner.next());
                if (num > 0) return num;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите положительное число.");
                scanner.nextLine();
            }
        }
    }

    public static int validateAge(String prompt, Scanner scanner) {
        while (true) {
            int age = validatePositiveInt(prompt, scanner);
            if (age <= 100) return age;
            System.out.println("Ошибка: введите возраст от 0 до 100.");
        }
    }

    public static String validateGender(String prompt, Scanner scanner) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.next().toLowerCase();
            if (input.equals("м") || input.equals("ж")) {
                return input;
            }
            System.out.println("Ошибка: введите 'м' или 'ж'.");
        }
    }

    public static String validateFile(String prompt, Scanner scanner) {
        Path path;
        while (true) {
            System.out.println(prompt);
            path = Path.of(scanner.nextLine());
            if (Files.isRegularFile(path))
                return path.toString();
            else
                System.out.println("Файл не существует по данному пути");
        }
    }
}
