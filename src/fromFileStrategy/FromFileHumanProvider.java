package fromFileStrategy;
import entities.Human;
import utility.MyArrayList;
import utility.Validator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class FromFileHumanProvider implements FromFileProvider<Human> {
    public Human[] getFileArray(Scanner scanner, int length) {
        scanner.nextLine();
        String path = Validator.validateFile("Введите путь до файла:", scanner);
        MyArrayList<Human> list = new MyArrayList<>(length);
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
                    Human human = Human.builder()
                            .lastName(parts[0])
                            .gender(parts[1].toLowerCase().charAt(0))
                            .age(Integer.parseInt(parts[2]))
                            .build();
                    list.add(human);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list.toArray(new Human[0]);
    }

    public boolean validate(String[] parts) {
        return  parts[0].matches("[a-zA-Zа-яА-Я]+") &&
                parts[1].matches("^[мМжЖ]$") &&
                Integer.parseInt(parts[2])>0;
    }
}