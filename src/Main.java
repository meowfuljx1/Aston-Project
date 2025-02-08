import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private final Map<Integer, Runnable> actionMap;

    public Main() {
        actionMap = new HashMap<>();
//       actionMap.put(1, new cat());
//        actionMap.put(2, new barrel());
//       actionMap.put(3, new human());
    }

    public void runMenu() {
        final int EXIT = 4;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String text = String.format("Введите с чем вы хотите работать%s%s%s%s", "\n1.кошка", "\n2.бочка", "\n3.человек", "\n4.выход");
            System.out.println(text);
            int choice = scanner.nextInt();
            if (choice == EXIT) {
                System.out.println("Выход из программы.");
                scanner.close();
                break;
            }
            Runnable action = actionMap.get(choice);
            if (action != null) {
                action.run();
            } else {
                System.out.println("Неверный ввод. Попробуйте снова.");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main menu = new Main();
        menu.runMenu();


    }
}