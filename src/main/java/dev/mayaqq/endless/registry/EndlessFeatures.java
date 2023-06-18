package dev.mayaqq.endless.registry;

import dev.mayaqq.endless.worldgen.features.chorusRoot.ChorusPlantRootFeatureConfig;
import dev.mayaqq.endless.worldgen.features.chorusRoot.ChorusPlantRootPlacedFeature;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;

import static dev.mayaqq.endless.Endless.id;

public class EndlessFeatures {
    public static final Identifier CHORUS_PLANT_ROOT = id("chorus_plant_root");
    public static Feature<ChorusPlantRootFeatureConfig> CHORUS_PLANT_ROOT_FEATURE = new ChorusPlantRootPlacedFeature(ChorusPlantRootFeatureConfig.CODEC);
    public static void register() {
        Registry.register(Registries.FEATURE, CHORUS_PLANT_ROOT, CHORUS_PLANT_ROOT_FEATURE);
    }
}
