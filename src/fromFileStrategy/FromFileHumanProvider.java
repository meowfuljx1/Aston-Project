package fromFileStrategy;
import entities.Human;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static manualStrategy.Validator.validateFile;

public class FromFileHumanProvider implements FromFileProvider<Human> {
    public Human[] getFileArray(Scanner scanner, int length) {
        String path = validateFile(scanner);
        ArrayList<Human> list = new ArrayList<>(length);
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.replaceAll(" ", "").split(",", 3);
                boolean isCorrectValues;
                try {
                    isCorrectValues = validate(parts);
                } catch (Exception e) {
                    continue;
                }
                if (isCorrectValues) {
                    Human human = Human.builder()
                            .lastName(parts[0])
                            .age(Integer.valueOf(parts[1]))
                            .gender(parts[2])
                            .build();
                    list.add(human);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        list.trimToSize();
        return list.toArray(new Human[0]);
    }

    public boolean validate(String[] parts) {
        return  parts[0].matches("[a-zA-Zа-яА-Я]+") &&
                Integer.parseInt(parts[1])>0 &&
                parts[2].matches("[a-zA-Zа-яА-Я]+");
    }
}