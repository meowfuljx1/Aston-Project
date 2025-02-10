import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}
import initialization.Initializable;
import initialization.InitializeArrayFromFile;
import initialization.InitializeArrayManually;
import initialization.InitializeArrayRandomly;

import java.util.*;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, Initializable> map = new HashMap<>();

    Map<Integer, String> objectTypes = Map.of(
            1, "Animal",
            2, "Human",
            3, "Barrel"
    );

    Map<Integer, String> initializationTypes = Map.of(
            1, "Manually",
            2, "Randomly",
            3, "FromFile"
    );

    public Main() {
        {
            map.put("Manually", new InitializeArrayManually());
            map.put("Randomly", new InitializeArrayRandomly());
            map.put("FromFile", new InitializeArrayFromFile());
        }
    }

    public void runMenu() {
        while (true) {
            String objectType = chooseObjectType();
            if (objectType.equals("exit")) break;

            int length = chooseArrayLength();
            if (length == -1) break;

            String initializationType = chooseInitializationType();
            if (initializationType.equals("exit")) break;

            var array = map.get(initializationType).initArray(objectType, length);

            // ПЕРЕДЕЛАТЬ ФОРМАТИРОВАНИЕ
            int emojiLength = Arrays.toString(array).length()/2;
            System.out.println("Ваш массивчик:");
            for (int i = 0; i < emojiLength; i++)
                System.out.print("✨");
            System.out.println();
            System.out.println(Arrays.toString(array));
            for (int i = 0; i < emojiLength; i++)
                System.out.print("✨");
            System.out.println();

            System.out.print("""
                    Что хотим дальше?
                    1. Бинарный поиск
                    2. Сортировка
                    3. Создать новый массив
                    -1. exit
                    """);

            int choice = scanner.nextInt();

            if(choice == 1){
                System.out.println("Какой объект ищем?");
            }

         /*   switch (){
                case 1 -> System.out.println();
                case 2 -> System.out.println();
                case -1 ->
            }*/
        }
        System.out.println("Завершение программы");
        scanner.close();

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

    private int chooseArrayLength(){
        int input;

        System.out.println("Сколько объектов поместить в массив?");
        while (true){
            try{
                input = scanner.nextInt();
                if (input > 0 || input == -1)
                    return input;
                else
                    System.out.println("Минимум объектов в массиве - 1");
            } catch (InputMismatchException e){
                System.out.println("Неверный ввод, введите число:");
                scanner.nextLine();
            }
        }
    }

    private String chooseInitializationType() {
        System.out.print("""
                Как вы хотите инициализировать массив?
                1. Вручную
                2. Рандомно
                3. Из файла
                -1. exit
                """
        );
        int input = validateInput(initializationTypes);
        return input == -1 ? "exit" : initializationTypes.get(input);
    }

    private int validateInput(Map<Integer, String> validValues) {
        int input;
        while (true) {
            try{
                input = scanner.nextInt();
                if (validValues.containsKey(input) || input == -1)
                    break;
                else
                    System.out.println("Такой команды нет");
            } catch (InputMismatchException e){
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