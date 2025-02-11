package fromFileStrategy;

import entities.Barrel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FromFileBarrelProvider implements FromFileProvider<Barrel> {
    @Override
    public Barrel[] getFileArray(String fileName, int length) {
        validate(fileName);
        Barrel[] barrels = new Barrel[length];
        int actualSize = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            Barrel.BarrelBuilder builder = Barrel.builder();
            while ((line = br.readLine()) != null && actualSize < length) {
                if (line.trim().isEmpty()) {
                    if (validate(builder)) {
                        barrels[actualSize++] = builder.build();
                    }
                    builder = Barrel.builder();
                } else {
                    String[] parts = line.split(":", 2);
                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim();
                        switch (key) {
                            case "Объем" -> builder.volume(Double.parseDouble(value));
                            case "Хранимый материал" -> builder.content(value);
                            case "Материал бочки" -> builder.material(value);
                        }
                    }
                }
            }
            if (actualSize < length && validate(builder)) {
                barrels[actualSize++] = builder.build();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return new Barrel[0];
        }
        Barrel[] result = new Barrel[actualSize];
        System.arraycopy(barrels, 0, result, 0, actualSize);
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
        return builder instanceof Barrel.BarrelBuilder && ((Barrel.BarrelBuilder) builder).isComplete();
    }
}