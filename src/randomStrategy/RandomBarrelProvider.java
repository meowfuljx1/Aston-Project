package randomStrategy;

import entities.Barrel;

public class RandomBarrelProvider implements RandomProvider<Barrel> {
    private final String[] BARREL_CONTENT_ARRAY = {"water", "fuel", "alcohol", "gas", "sand"};
    private final String[] BARREL_MATERIAL_ARRAY = {"plastic", "carton", "metal", "wood", "glass"};

    @Override
    public Barrel[] getRandomArray(int length) {
        Barrel[] barrels = new Barrel[length];
        for (int i = 0; i < barrels.length; i++) {
            barrels[i] = Barrel.builder()
                    .volume(Math.round(random.nextDouble(1, 100) * 100.0) / 100.0)
                    .content(BARREL_CONTENT_ARRAY[random.nextInt(5)])
                    .material(BARREL_MATERIAL_ARRAY[random.nextInt(5)])
                    .build();
        }
        return barrels;
    }
}
