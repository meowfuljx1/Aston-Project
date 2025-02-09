public class Human {

    /*Класы:
    Животное (Вид, Цвет глаз, Шерсть (булка)),
    Бочка (Объем, Хранимый материал, Материал из которого изготовлена),
    Человек (Пол, Возраст, Фамилия) Person (Gender, Age, Last Name)*/

    private String gender;
    private Double age;
    private String lastName;

    public Human(String gender, double age, String lastName) {
        this.gender = gender;
        this.age = age;
        this.lastName = lastName;
    }

    public static HumanBuilder humanBuilder() {
        return new HumanBuilder();
    }

    public static class HumanBuilder {

        private String gender;
        private Double age;
        private String lastName;

        HumanBuilder() {
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setAge(Double age) {
            this.age = age;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public HumanBuilder(String gender, Double age, String lastName) {
            this.gender = gender;
            this.age = age;
            this.lastName = lastName;
        }

        public String toString() {
            return "HumanBuilder{" +
                    "gender='" + gender + '\'' +
                    ", age=" + age +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }
    }
}
