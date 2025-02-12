package manualStrategy;

import entities.Animal;


import java.util.Scanner;

public class ManualAnimalProvider implements ManualProvider<Animal> {
    @Override
    public Animal[] getManualArray(Scanner scanner, int length) {
        Animal[] animals = new Animal[length];

        for (int i = 0; i < length; i++) {
            System.out.println("Введите данные для животного: " + (i + 1));

            String type = Validator.validateString("Вид: ");
            String eyeColor = Validator.validateString("Цвет глаз: ");
            boolean wool = Validator.validateBoolean("Есть шерсть? (true/false): ");

            animals[i] = new Animal.AnimalBuilder()
                    .type(type)
                    .eyeColor(eyeColor)
                    .wool(wool)
                    .build();
        }

        return animals;
    }
}
