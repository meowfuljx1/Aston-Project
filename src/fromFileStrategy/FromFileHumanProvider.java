package fromFileStrategy;

import entities.Human;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FromFileHumanProvider implements FromFileProvider<Human> {
    @Override
    public Human[] getFileArray(String fileName, int length) {
        validate(fileName);
        Human[] humans = new Human[length];
        int actualSize = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            Human.HumanBuilder builder = Human.builder();
            while ((line = br.readLine()) != null && actualSize < length) {
                if (line.trim().isEmpty()) {
                    if (validate(builder)) {
                        humans[actualSize++] = builder.build();
                    }
                    builder = Human.builder();
                } else {
                    String[] parts = line.split(":", 2);
                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim();

                        switch (key) {
                            case "Фамилия" -> builder.lastName(value);
                            case "Пол" -> builder.gender(value);
                            case "Возраст" -> builder.age(Integer.parseInt(value));
                        }
                    }
                }
            }
            if (actualSize < length && validate(builder)) {
                humans[actualSize++] = builder.build();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return new Human[0];
        }
        Human[] result = new Human[actualSize];
        System.arraycopy(humans, 0, result, 0, actualSize);
        return result;
    }

    @Override
    public boolean validate(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IllegalArgumentException("Файл не существует: " + fileName);
        }
        if (!file.canRead()) {
            throw new IllegalArgumentException("Файл недоступен для чтения: " + fileName);
        }
        return true;
    }

    @Override
    public boolean validate(Object builder) {
        return builder instanceof Human.HumanBuilder && ((Human.HumanBuilder) builder).isComplete();
    }
}