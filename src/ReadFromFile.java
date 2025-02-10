import entities.Animal;
import entities.Barrel;
import entities.Human;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromFile {
    public static Object[] readFromFile(String fileName) {
        Object[] objects = null;
        int objectCount = 0;

        // Первый проход: подсчет количества объектов
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Вид:") || line.startsWith("Объем:") || line.startsWith("Фамилия:")) {
                    objectCount++;
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return new Object[0]; // Возвращаем пустой массив в случае ошибки
        }

        // Создаем массив нужного размера
        objects = new Object[objectCount];

        // Второй проход: заполнение массива объектами
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int index = -1; // Индекс текущего объекта в массиве
            Animal.AnimalBuilder animalBuilder = null;
            Barrel.BarrelBuilder barrelBuilder = null;
            Human.HumanBuilder humanBuilder = null;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Вид:")) {
                    // Если начинается новый объект, сохраняем предыдущий (если он есть)
                    if (animalBuilder != null) {
                        objects[index] = animalBuilder.build();
                    } else if (barrelBuilder != null) {
                        objects[index] = barrelBuilder.build();
                    } else if (humanBuilder != null) {
                        objects[index] = humanBuilder.build();
                    }
                    index++;
                    animalBuilder = Animal.builder();
                    animalBuilder.type(line.substring("Вид:".length()).trim());
                } else if (line.startsWith("Цвет глаз:")) {
                    if (animalBuilder != null) {
                        animalBuilder.eyeColor(line.substring("Цвет глаз:".length()).trim());
                    }
                } else if (line.startsWith("Шерсть:")) {
                    if (animalBuilder != null) {
                        animalBuilder.wool(Boolean.parseBoolean(line.substring("Шерсть:".length()).trim()));
                    }
                } else if (line.startsWith("Объем:")) {
                    // Если начинается новый объект, сохраняем предыдущий (если он есть)
                    if (animalBuilder != null) {
                        objects[index] = animalBuilder.build();
                    } else if (barrelBuilder != null) {
                        objects[index] = barrelBuilder.build();
                    } else if (humanBuilder != null) {
                        objects[index] = humanBuilder.build();
                    }
                    index++;
                    barrelBuilder = Barrel.builder();
                    barrelBuilder.volume(Double.parseDouble(line.substring("Объем:".length()).trim()));
                } else if (line.startsWith("Хранимый материал:")) {
                    if (barrelBuilder != null) {
                        barrelBuilder.content(line.substring("Хранимый материал:".length()).trim());
                    }
                } else if (line.startsWith("Материал бочки:")) {
                    if (barrelBuilder != null) {
                        barrelBuilder.material(line.substring("Материал бочки:".length()).trim());
                    }
                } else if (line.startsWith("Фамилия:")) {
                    // Если начинается новый объект, сохраняем предыдущий (если он есть)
                    if (animalBuilder != null) {
                        objects[index] = animalBuilder.build();
                    } else if (barrelBuilder != null) {
                        objects[index] = barrelBuilder.build();
                    } else if (humanBuilder != null) {
                        objects[index] = humanBuilder.build();
                    }
                    index++;
                    humanBuilder = Human.builder();
                    humanBuilder.lastName(line.substring("Фамилия:".length()).trim());
                } else if (line.startsWith("Пол:")) {
                    if (humanBuilder != null) {
                        humanBuilder.gender(line.substring("Пол:".length()).trim());
                    }
                } else if (line.startsWith("Возраст:")) {
                    if (humanBuilder != null) {
                        humanBuilder.age(Integer.parseInt(line.substring("Возраст:".length()).trim()));
                    }
                }
            }
            // Добавляем последний объект, если он есть
            if (animalBuilder != null) {
                objects[index] = animalBuilder.build();
            } else if (barrelBuilder != null) {
                objects[index] = barrelBuilder.build();
            } else if (humanBuilder != null) {
                objects[index] = humanBuilder.build();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        return objects;
    }
}