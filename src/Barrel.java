public class Barrel {

    /*Класы:
    Животное (Вид, Цвет глаз, Шерсть (булка)),
    Бочка (Объем, Хранимый материал, Материал из которого изготовлена)
     rrel (Volume, Stored material, The material from which it is made),
    Человек (Пол, Возраст, Фамилия)*/

    private Double volume;
    private String content;
    private String material;

    public Barrel(Double volume, String content, String material) {
        this.volume = volume;
        this.content = content;
        this.material = material;
    }

    public static BarrelBuilder barrelBuilder() {
        return new BarrelBuilder();
    }

    public static class BarrelBuilder {

        private Double volume;
        private String content;
        private String material;

        BarrelBuilder() {
        }

        public void setVolume(Double volume) {
            this.volume = volume;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setMaterial(String material) {
            this.material = material;
        }

        public BarrelBuilder(Double volume, String content, String material) {
            this.volume = volume;
            this.content = content;
            this.material = material;
        }

        public String toString() {
            return "BarrelBuilder{" +
                    "volume=" + volume +
                    ", content='" + content + '\'' +
                    ", material='" + material + '\'' +
                    '}';
        }
    }
}
