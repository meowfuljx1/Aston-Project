package manualStrategy;

import entities.Animal;

import java.util.Scanner;

public class ManualAnimalProvider implements ManualProvider<Animal> {
    @Override
    public Animal[] getManualArray(Scanner scanner, int length) {
        return new Animal[0];
    }
}
