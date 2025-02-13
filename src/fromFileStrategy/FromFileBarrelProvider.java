package fromFileStrategy;

import entities.Barrel;
import utility.MyArrayList;
import utility.Validator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class FromFileBarrelProvider implements FromFileProvider<Barrel> {
    public Barrel[] getFileArray(Scanner scanner, int length) {
        scanner.nextLine();
        String path = Validator.validateFile("Введите путь до файла:", scanner);
        MyArrayList<Barrel> list = new MyArrayList<>(length);
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.replaceAll(" ", "").split(";", 3);
                boolean isCorrectValues;
                try {
                    isCorrectValues = validate(parts);
                } catch (Exception e) {
                    continue;
                }
                if (isCorrectValues) {
                    Barrel barrel = Barrel.builder()
                            .volume(Double.parseDouble(parts[0]))
                            .content(parts[1])
                            .material(parts[2])
                            .build();
                    list.add(barrel);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list.toArray(new Barrel[0]);
    }

    public boolean validate(String[] parts) {
        return Double.parseDouble(parts[0])>0 &&
                parts[1].matches("[a-zA-Zа-яА-Я]+") &&
                parts[2].matches("[a-zA-Zа-яА-Я]+");
    }
}