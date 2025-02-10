package randomStrategy;

import entities.Animal;

public class RandomAnimalProvider implements RandomProvider<Animal> {
    private final String[] ANIMAL_TYPE_ARRAY = {"cat", "dog", "hamster", "horse", "parrot"};
    private final String[] ANIMAL_EYE_COLOR_ARRAY = {"green", "brown", "blue", "gray", "yellow"};

    @Override
    public Animal[] getObjectArray(int length) {
        Animal[] animals = new Animal[length];
        for (int i = 0; i < animals.length; i++) {
            animals[i] = Animal.builder()
                    .type(ANIMAL_TYPE_ARRAY[random.nextInt(5)])
                    .eyeColor(ANIMAL_EYE_COLOR_ARRAY[random.nextInt(5)])
                    .wool(random.nextBoolean())
                    .build();
        }
        return animals;
    }
}
