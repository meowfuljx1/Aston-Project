package manualStrategy;

import entities.Human;
import utility.Validator;

import java.util.Scanner;

public class ManualHumanProvider implements ManualProvider<Human> {
    @Override
    public Human[] getManualArray(Scanner scanner, int length) {
        Human[] humans = new Human[length];

        for (int i = 0; i < length; i++) {
            System.out.println("Введите данные для человека: " + (i + 1));

            String gender = Validator.validateGender("Пол (м/ж): ", scanner);
            int age = Validator.validateAge("Возраст: ", scanner);
            String lastName = Validator.validateString("Фамилия: ", scanner);

            humans[i] = new Human.HumanBuilder()
                    .gender(gender.charAt(0))
                    .age(age)
                    .lastName(lastName)
                    .build();
        }

        return humans;
    }
}
