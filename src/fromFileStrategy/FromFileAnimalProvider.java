package fromFileStrategy;

import entities.Animal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FromFileAnimalProvider implements FromFileProvider<Animal> {
    @Override
    public Animal[] getFileArray(String fileName, int length) {
        validate(fileName);
        Animal[] animals = new Animal[length];
        int actualSize = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            Animal.AnimalBuilder builder = Animal.builder();
            while ((line = br.readLine()) != null && actualSize < length) {
                if (line.trim().isEmpty()) {
                    if (validate(builder)) {
                        animals[actualSize++] = builder.build();
                    }
                    builder = Animal.builder();
                } else {
                    String[] parts = line.split(":", 2);
                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim();
                        switch (key) {
                            case "Вид" -> builder.type(value);
                            case "Цвет глаз" -> builder.eyeColor(value);
                            case "Шерсть" -> {
                                boolean hasWool = value.equalsIgnoreCase("есть");
                                builder.wool(hasWool);
                            }
                        }
                    }
                }
            }
            if (actualSize < length && validate(builder)) {
                animals[actualSize++] = builder.build();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return new Animal[0];
        }
        Animal[] result = new Animal[actualSize];
        System.arraycopy(animals, 0, result, 0, actualSize);
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
        return builder instanceof Animal.AnimalBuilder && ((Animal.AnimalBuilder) builder).isComplete();
    }
}

