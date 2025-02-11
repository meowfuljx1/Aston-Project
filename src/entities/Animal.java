package entities;

import java.util.Comparator;

public record Animal(String type, String eyeColor, boolean wool) implements Comparable<Animal> {

    public static AnimalBuilder builder() {
        return new AnimalBuilder();
    }

    @Override
    public int compareTo(Animal otherAnimal) {
        return Comparator.comparing(Animal::type)
                .thenComparing(Animal::eyeColor)
                .thenComparing(Animal::wool)
                .compare(this, otherAnimal);
    }

    public static class AnimalBuilder {
        private String type;
        private String eyeColor;
        private boolean wool;

        public AnimalBuilder type(String type) {
            this.type = type;
            return this;
        }

        public AnimalBuilder eyeColor(String eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        public AnimalBuilder wool(boolean wool) {
            this.wool = wool;
            return this;
        }

        public Animal build() {
            return new Animal(type, eyeColor, wool);
        }

        public boolean isComplete() {
            return type != null && eyeColor != null;
        }
    }
}
