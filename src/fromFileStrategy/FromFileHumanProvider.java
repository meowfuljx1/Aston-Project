package fromFileStrategy;

import entities.Human;

public class FromFileHumanProvider implements FromFileProvider<Human> {
    @Override
    public Human[] getFileArray(String path, int length) {
        return new Human[0];
    }

    @Override
    public boolean validate(String s) {
        return false;
    }
}
