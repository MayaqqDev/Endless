package dev.mayaqq.endless.registry;

import dev.mayaqq.endless.datagen.tags.EndlessTags;
import dev.mayaqq.endless.worldgen.features.chorusRoot.ChorusPlantRootFeatureConfig;
import dev.mayaqq.endless.worldgen.features.chorusRoot.ChorusPlantRootFeature;
import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.source.BiomeSources;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.*;

import static dev.mayaqq.endless.Endless.id;

public class EndlessFeatures {
    public static final Identifier CHORUS_PLANT_ROOT = id("chorus_plant_root");

    public static Feature<ChorusPlantRootFeatureConfig> CHORUS_PLANT_ROOT_FEATURE = Registry.register(Registries.FEATURE, CHORUS_PLANT_ROOT, new ChorusPlantRootFeature(ChorusPlantRootFeatureConfig.CODEC));
    public static RegistryKey<PlacedFeature> CHORUS_PLANT_ROOT_PLACED_FEATURE_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, CHORUS_PLANT_ROOT);
    public static RegistryKey<PlacedFeature> ENDER_GRASS_PATCH_FEATURE_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, id("ender_grass_patch"));

    public static void register() {
        // add feature to end biomes
        BiomeModifications.addFeature(BiomeSelectors.tag(EndlessTags.BiomeTags.HAS_CHORUS_ROOTS), GenerationStep.Feature.UNDERGROUND_DECORATION, CHORUS_PLANT_ROOT_PLACED_FEATURE_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.TOP_LAYER_MODIFICATION, ENDER_GRASS_PATCH_FEATURE_KEY);
    }
}
