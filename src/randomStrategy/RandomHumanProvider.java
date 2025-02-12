package randomStrategy;

import entities.Human;

public class RandomHumanProvider implements RandomProvider<Human> {
    private final String[] LAST_NAME_ARRAY = {"Smith", "Johnson", "Brown", "Miller", "Anderson"};

    @Override
    public Human[] getRandomArray(int length) {
        Human[] humans = new Human[length];
        for (int i = 0; i < humans.length; i++) {
            humans[i] = Human.builder()
                    .lastName(LAST_NAME_ARRAY[random.nextInt(5)])
                    .gender(random.nextInt(0, 2) == 0 ? 'ж' : 'м')
                    .age(random.nextInt(100))
                    .build();
        }
        return humans;
    }
}
