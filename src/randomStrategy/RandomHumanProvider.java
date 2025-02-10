package randomStrategy;

import entities.Human;

public class RandomHumanProvider implements RandomProvider{
    private final String[] GenderArray = {"Male", "Female"};
    private final String[] LastNameArray = {"Smith", "Johnson", "Brown", "Miller", "Anderson"};

    @Override
    public Human[] getObjectArray(int length) {
        Human[] humans = new Human[length];
        for (int i = 0; i < humans.length; i++) {
            humans[i] = Human.builder()
                    .lastName(LastNameArray[random.nextInt(5)])
                    .gender(GenderArray[random.nextInt(2)])
                    .age(random.nextInt(100))
                    .build();
        }
        return humans;
    }
}
