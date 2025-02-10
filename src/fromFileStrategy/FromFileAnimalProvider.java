package fromFileStrategy;

import entities.Animal;

public class FromFileAnimalProvider implements FromFileProvider<Animal> {
    @Override
    public Animal[] getFileArray(String path, int length) {
        return new Animal[0];
    }

    @Override
    public boolean validate(String s) {
        return false;
    }
}
