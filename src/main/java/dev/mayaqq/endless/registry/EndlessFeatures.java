package dev.mayaqq.endless.registry;

import dev.mayaqq.endless.worldgen.features.chorusRoot.ChorusPlantRootFeatureConfig;
import dev.mayaqq.endless.worldgen.features.chorusRoot.ChorusPlantRootFeature;
import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.*;

import static dev.mayaqq.endless.Endless.id;

public class EndlessFeatures {
    public static final Identifier CHORUS_PLANT_ROOT = id("chorus_plant_root");
    public static Feature<ChorusPlantRootFeatureConfig> CHORUS_PLANT_ROOT_FEATURE = new ChorusPlantRootFeature(ChorusPlantRootFeatureConfig.CODEC);
    public static RegistryKey<PlacedFeature> CHORUS_PLANT_ROOT_PLACED_FEATURE_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, CHORUS_PLANT_ROOT);
    public static void register() {
        Registry.register(Registries.FEATURE, CHORUS_PLANT_ROOT, CHORUS_PLANT_ROOT_FEATURE);
    
        // example code to make the chorus roots placed feature generate in all end biomes
        BiomeModifications.addFeature(BiomeSelectors.tag(EndlessTags.HAS_CHORUS_ROOTS), GenerationStep.Feature.UNDERGROUND_DECORATION, CHORUS_PLANT_ROOT_PLACED_FEATURE_KEY);
    }
}
