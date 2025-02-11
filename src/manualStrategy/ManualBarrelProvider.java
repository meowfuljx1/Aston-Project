package manualStrategy;

import entities.Barrel;
import java.util.Scanner;

public class ManualBarrelProvider implements ManualProvider<Barrel> {
    @Override
    public Barrel[] getManualArray(Scanner scanner, int length) {
        Barrel[] barrels = new Barrel[length];

        for (int i = 0; i < length; i++) {
            System.out.println("Введите данные для бочки #" + (i + 1));

            System.out.print("Объем: ");
            int volume = scanner.nextInt();

            System.out.print("Хранимый материал: ");
            String storedMaterial = scanner.next();

            System.out.print("Материал бочки: ");
            String material = scanner.next();

            barrels[i] = new Barrel.BarrelBuilder()
                    .volume(volume)
                    .content(storedMaterial)
                    .material(material)
                    .build();
        }

        return barrels;
    }
}
