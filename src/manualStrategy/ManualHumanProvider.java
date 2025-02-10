package manualStrategy;

import entities.Human;

import java.util.Scanner;

public class ManualHumanProvider implements ManualProvider<Human> {
    @Override
    public Human[] getManualArray(Scanner scanner, int length) {
        return new Human[0];
    }
}
