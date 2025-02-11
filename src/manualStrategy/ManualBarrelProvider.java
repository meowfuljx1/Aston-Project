package manualStrategy;

import entities.Barrel;

import java.util.Scanner;

public class ManualBarrelProvider implements ManualProvider<Barrel> {
    @Override
    public Barrel[] getManualArray(Scanner scanner, int length) {
        return new Barrel[0];
    }
}
