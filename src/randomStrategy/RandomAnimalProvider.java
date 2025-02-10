package randomStrategy;

import entities.Animal;

public class RandomAnimalProvider implements RandomProvider {
    private final String[] AnimalTypeArray = {"cat", "dog", "hamster", "horse", "parrot"};
    private final String[] AnimalEyeColorArray = {"green", "brown", "blue", "gray", "yellow"};

    @Override
    public Animal[] getObjectArray(int length) {
        Animal[] animals = new Animal[length];
        for (int i = 0; i < animals.length; i++) {
            animals[i] = Animal.builder()
                    .type(AnimalTypeArray[random.nextInt(5)])
                    .eyeColor(AnimalEyeColorArray[random.nextInt(5)])
                    .wool(random.nextBoolean())
                    .build();
        }
        return animals;
    }
}
