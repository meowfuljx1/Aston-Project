package entities;

import java.util.Comparator;

public record Barrel(double volume, String content, String material) implements Comparable<Barrel> {

    public static BarrelBuilder builder() {
        return new BarrelBuilder();
    }

    @Override
    public int compareTo(Barrel otherBarrel) {
        return Comparator.comparing(Barrel::volume)
                .thenComparing(Barrel::content)
                .thenComparing(Barrel::material)
                .compare(this, otherBarrel);
    }

    public static class BarrelBuilder {
        private double volume;
        private String content;
        private String material;

        public BarrelBuilder volume(double volume) {
            this.volume = volume;
            return this;
        }

        public BarrelBuilder content(String content) {
            this.content = content;
            return this;
        }

        public BarrelBuilder material(String material) {
            this.material = material;
            return this;
        }

        public Barrel build() {
            return new Barrel(volume, content, material);
        }
    }
}
