package manualStrategy;

import entities.Animal;
import java.util.Scanner;

public class ManualAnimalProvider implements ManualProvider<Animal> {
    @Override
    public Animal[] getManualArray(Scanner scanner, int length) {
        Animal[] animals = new Animal[length];

        for (int i = 0; i < length; i++) {
            System.out.println("Введите данные для животного: " + (i + 1));

            System.out.print("Вид: ");
            String species = scanner.next();

            System.out.print("Цвет глаз: ");
            String eyeColor = scanner.next();

            System.out.print("Есть шерсть? (true/false): ");
            boolean hasFur = scanner.nextBoolean();

            animals[i] = new Animal.AnimalBuilder()
                    .type(species)
                    .eyeColor(eyeColor)
                    .wool(hasFur)
                    .build();
        }

        return animals;
    }
}
