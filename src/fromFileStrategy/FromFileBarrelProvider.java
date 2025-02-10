package fromFileStrategy;

import entities.Barrel;

public class FromFileBarrelProvider implements FromFileProvider<Barrel> {
    @Override
    public Barrel[] getFileArray(String path, int length) {
        return new Barrel[0];
    }

    @Override
    public boolean validate(String s) {
        return false;
    }
}
