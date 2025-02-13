package fromFileStrategy;

import entities.Animal;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static manualStrategy.Validator.validateFile;

public class FromFileAnimalProvider implements FromFileProvider<Animal> {
    public Animal[] getFileArray(Scanner scanner, int length) {
        String path = validateFile(scanner);
        ArrayList<Animal> list = new ArrayList<>(length);
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.replaceAll(" ", "").split(",", 3);
                boolean isCorrectValues;
                try {
                    isCorrectValues = validate(parts);
                } catch (ArrayIndexOutOfBoundsException e){
                    continue;
                }
                if (isCorrectValues) {
                    Animal animal = Animal.builder()
                            .type(parts[0])
                            .eyeColor(parts[1])
                            .wool(Boolean.parseBoolean(parts[2]))
                            .build();
                    list.add(animal);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        list.trimToSize();
        return list.toArray(new Animal[0]);
    }

    public boolean validate(String[] parts) {
        return parts[0].matches("[a-zA-Zа-яА-Я]+") &&
                parts[1].matches("[a-zA-Zа-яА-Я]+") &&
                (parts[2].equalsIgnoreCase("true") || parts[2].equalsIgnoreCase("false"));
    }
}


