package manualStrategy;

import entities.Barrel;
import utility.Validator;

import java.util.Scanner;

public final class ManualBarrelProvider implements ManualProvider<Barrel> {
    @Override
    public Barrel[] getManualArray(Scanner scanner, int length) {
        Barrel[] barrels = new Barrel[length];

        for (int i = 0; i < length; i++) {
            System.out.println("Введите данные для бочки #" + (i + 1));

            double volume = Validator.validateVolume("Объем: ", scanner);
            String content = Validator.validateString("Хранимый материал: ", scanner);
            String material = Validator.validateString("Материал бочки: ", scanner);

            barrels[i] = new Barrel.BarrelBuilder()
                    .volume(volume)
                    .content(content)
                    .material(material)
                    .build();
        }

        return barrels;
    }
}
