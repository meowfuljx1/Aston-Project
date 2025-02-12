import entities.Animal;
import entities.Barrel;
import entities.Human;
import fromFileStrategy.*;
import manualStrategy.*;
import randomStrategy.*;

import java.util.*;

public class Main {
    private final Scanner scanner = new Scanner(System.in);

    private final Map<Integer, String> objectTypes = Map.of(
            1, "Animal",
            2, "Barrel",
            3, "Human"
    );
    private final Map<Integer, String> initializationTypes = Map.of(
            1, "From file",
            2, "Manual",
            3, "Random"
    );

    private final Map<String, FromFileProvider<?>> fromFileStrategy = Map.of(
            "Animal", new FromFileAnimalProvider(),
            "Barrel", new FromFileBarrelProvider(),
            "Human", new FromFileHumanProvider()
    );
    private final Map<String, ManualProvider<?>> manualStrategy = Map.of(
            "Animal", new ManualAnimalProvider(),
            "Barrel", new ManualBarrelProvider(),
            "Human", new ManualHumanProvider()
    );
    private final Map<String, RandomProvider<?>> randomStrategy = Map.of(
            "Animal", new RandomAnimalProvider(),
            "Barrel", new RandomBarrelProvider(),
            "Human", new RandomHumanProvider()
    );

    public void runMenu() {
        mainLoop:
        while (true) {
            // получаем Класс объекта
            String objectType = chooseObjectType();
            if (objectType.equals("exit")) break;

            // получаем длину массива
            int length = chooseArrayLength();
            if (length == -1) break;

            // получаем способ инициализации
            String initializationType = chooseInitializationType();
            if (initializationType.equals("exit")) break;

            // создаем массив
            Object[] array = getArray(initializationType, objectType, length);
            printArray(array, "\nМассив:");

            // что дальше?
            String op = whatsNext();
            switch (op) {
                case "sort":
                    sort(array);
                    printArray(array, "\nSorted:");
                    break;
                case "reset":
                    continue;
                case "exit":
                    break mainLoop;
            }

            // endpoint
            while(true){
                String op2 = whatsNext2();
                if (op2.equals("binarySearch")){
                    int index = binarySearch(array);
                    System.out.println(index != -1 ? "Индекс в массиве: " + index : "Объект отсутствует");
                } else if (op2.equals("reset"))
                    continue mainLoop;
                else
                    break;
            }
        }
        System.out.println("Завершение программы");
        scanner.close();
    }

    private String whatsNext2() {
        System.out.print("""
                \nЧтобы еще поделать...
                1. Бинарный поиск
                2. Создать новый массив
                -1. exit
                """);

        String input;
        while (true) {
            try {
                input = scanner.next("1|2|-1");
                switch (input) {
                    case "1" -> input = "binarySearch";
                    case "2" -> input = "reset";
                    case "-1" -> input = "exit";
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод");
                scanner.nextLine();
            }
        }
        return input;
    }

    private String whatsNext() {
        System.out.print("""
                \nЧто хотим дальше?
                1. Сортировка (будет доступен бинарный поиск)
                2. Создать новый массив
                -1. exit
                """);

        String input;
        while (true) {
            try {
                input = scanner.next("1|2|-1");
                switch (input) {
                    case "1" -> input = "sort";
                    case "2" -> input = "reset";
                    case "-1" -> input = "exit";
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод");
                scanner.nextLine();
            }
        }
        return input;
    }

    private void printArray(Object[] array, String s) {
        System.out.println(s);
        for (Object o : array)
            System.out.println(o);
    }

    private void sort(Object[] array) {
        if (array instanceof Animal[])
            ArraysUtil2.sort(array, Comparator.comparing(o -> ((Animal) o)));
        else if (array instanceof Barrel[])
            ArraysUtil2.sort(array, Comparator.comparing(o -> (Barrel) o));
        else
            ArraysUtil2.sort(array, Comparator.comparing(o -> (Human) o));
    }

    private int binarySearch(Object[] array) {
        System.out.println("\nБинарный поиск!");
        System.out.println("Какой объект ищем?");
        int index;

        if (array instanceof Animal[]) {
            System.out.print("Тип: ");
            String type = scanner.next();

            System.out.print("Цвет глаз: ");
            String eyeColor = scanner.next();

            System.out.print("Шерсть (true/false): ");
            boolean wool = scanner.nextBoolean();

            Animal target = Animal.builder()
                    .type(type)
                    .eyeColor(eyeColor)
                    .wool(wool)
                    .build();

            index = ArraysUtil2.binarySearch(array, target, Comparator.comparing(o -> ((Animal) o)));
        } else if (array instanceof Barrel[]) {
            System.out.print("Объем: ");
            double volume = scanner.nextDouble();

            System.out.print("Хранимый материал: ");
            String content = scanner.next();

            System.out.print("Из чего изготовлена: ");
            String material = scanner.next();

            Barrel barrel = Barrel.builder()
                    .volume(volume)
                    .content(content)
                    .material(material)
                    .build();

            index = ArraysUtil2.binarySearch(array, barrel, Comparator.comparing(o -> ((Barrel) o)));
        } else {
            System.out.print("\nФамилия: ");
            String lastName = scanner.next();

            System.out.print("Пол: ");
            String gender = scanner.next();

            System.out.print("Возраст: ");
            int age = scanner.nextInt();

            Human human = Human.builder()
                    .lastName(lastName)
                    .gender(gender)
                    .age(age)
                    .build();

            index = ArraysUtil2.binarySearch(array, human, Comparator.comparing(o -> ((Human) o)));
        }
        return index;
    }

    private Object[] getArray(String initializationType, String objectType, int length) {
        return switch (initializationType) {
            case "From file" -> {
                System.out.println("Введите путь до файла:");
                String path = scanner.next();
                yield fromFileStrategy.get(objectType).getFileArray(path, length);
            }
            case "Manual" -> manualStrategy.get(objectType).getManualArray(scanner, length);
            default -> randomStrategy.get(objectType).getRandomArray(length); // Random
        };
    }

    private String chooseObjectType() {
        System.out.print("""
                С каким типом вы хотите работать?
                1. Animal
                2. Barrel
                3. Human
                -1. exit
                """
        );
        int input = validateInput(objectTypes);
        return input == -1 ? "exit" : objectTypes.get(input);
    }

    private int chooseArrayLength() {
        System.out.println("Сколько объектов поместить в массив?");
        return validateInput();
    }

    private String chooseInitializationType() {
        System.out.print("""
                Как вы хотите инициализировать массив?
                1. From file
                2. Manual
                3. Random
                -1. exit
                """
        );
        int input = validateInput(initializationTypes);
        return input == -1 ? "exit" : initializationTypes.get(input);
    }

    private int validateInput() {
        int input;
        while (true) {
            try {
                input = scanner.nextInt();
                if (input > 0 || input == -1)
                    return input;
                else
                    System.out.println("Минимум объектов в массиве - 1");
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод, введите число:");
                scanner.nextLine();
            }
        }
    }

    private int validateInput(Map<Integer, String> validValues) {
        int input;
        while (true) {
            try {
                input = scanner.nextInt();
                if (validValues.containsKey(input) || input == -1)
                    break;
                else
                    System.out.println("Такой команды нет");
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод");
                scanner.nextLine();
            }
        }
        return input;
    }

    public static void main(String[] args) {
        Main menu = new Main();
        menu.runMenu();
    }
}