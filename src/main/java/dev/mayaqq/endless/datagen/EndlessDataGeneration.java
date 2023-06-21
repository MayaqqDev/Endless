package dev.mayaqq.endless.datagen;

import dev.mayaqq.endless.datagen.recipes.EndlessRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import dev.mayaqq.endless.datagen.advancements.EndlessAdvancementsProvider;

public class EndlessDataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(EndlessAdvancementsProvider::new);
        pack.addProvider(EndlessRecipeProvider::new);
    }
}