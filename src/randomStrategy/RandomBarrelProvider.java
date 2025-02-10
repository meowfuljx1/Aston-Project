package randomStrategy;

import entities.Barrel;

public class RandomBarrelProvider implements RandomProvider {
    private final String[] BarrelContentArray = {"water", "fuel", "alcohol", "gas", "sand"};
    private final String[] BarrelMaterialArray = {"plastic", "carton", "metal", "wood", "glass"};

    @Override
    public Barrel[] getObjectArray(int length) {
        Barrel[] barrels = new Barrel[length];
        for (int i = 0; i < barrels.length; i++) {
            barrels[i] = Barrel.builder()
                    .volume(random.nextDouble())
                    .content(BarrelContentArray[random.nextInt(5)])
                    .material(BarrelMaterialArray[random.nextInt(5)])
                    .build();
        }
        return barrels;
    }
}
