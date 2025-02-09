public class Animal {

    /*Класы:
    Животное (Вид, Цвет глаз, Шерсть (булка)),
    Бочка (Объем, Хранимый материал, Материал из которого изготовлена) rrel (Volume, Stored material, The material from which it is made),
    Человек (Пол, Возраст, Фамилия) Person (Gender, Age, Last Name)*/

    private String type;
    private String eyeColor;
    private Boolean wool;

    public Animal(String type, String eyeColor, Boolean wool) {
        this.type = type;
        this.eyeColor = eyeColor;
        this.wool = wool;
    }

    private static AnimalBuilder animalBuilder() {
        return new AnimalBuilder();
    }

    public static class AnimalBuilder {

        private String type;
        private String eyeColor;
        private Boolean wool;

        AnimalBuilder() {
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setEyeColor(String eyeColor) {
            this.eyeColor = eyeColor;
        }

        public void setWool(Boolean wool) {
            this.wool = wool;
        }

        public AnimalBuilder(String type, String eyeColor, Boolean wool) {
            this.type = type;
            this.eyeColor = eyeColor;
            this.wool = wool;
        }

        public String toString() {
            return "AnimalBuilder{" +
                    "type='" + type + '\'' +
                    ", eyeColor='" + eyeColor + '\'' +
                    ", wool=" + wool +
                    '}';
        }
    }
}
