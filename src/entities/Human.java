package entities;

import java.util.Comparator;

public record Human(String lastName, String gender, Integer age) implements Comparable<Human> {

    public static HumanBuilder builder() {
        return new HumanBuilder();
    }

    @Override
    public int compareTo(Human otherHuman) {
        return Comparator.comparing(Human::lastName)
                .thenComparing(Human::gender)
                .thenComparing(Human::age)
                .compare(this, otherHuman);
    }

    public static class HumanBuilder {
        private String lastName;
        private String gender;
        private Integer age;

        public HumanBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public HumanBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public HumanBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Human build() {
            return new Human(lastName, gender, age);
        }
    }
}
