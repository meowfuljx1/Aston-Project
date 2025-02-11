package manualStrategy;

import entities.Human;

import java.util.Scanner;

public class ManualHumanProvider implements ManualProvider<Human> {
    @Override
    public Human[] getManualArray(Scanner scanner, int length) {
        Human[] humans = new Human[length];

        for (int i = 0; i < length; i++) {
            System.out.println("Введите данные для человека: " + (i + 1));

            String gender = Validator.validateGender("Пол (м/ж): ");
            int age = Validator.validateAge("Возраст: ");
            String lastName = Validator.validateString("Фамилия: ");

            humans[i] = new Human.HumanBuilder()
                    .gender(gender)
                    .age(age)
                    .lastName(lastName)
                    .build();
        }

        return humans;
    }
}
