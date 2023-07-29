package dev.mayaqq.endless.datagen;

import dev.mayaqq.endless.datagen.recipes.EndlessRecipeProvider;
import dev.mayaqq.endless.datagen.tags.EndlessTags;
import dev.mayaqq.endless.datagen.translations.EndlessEnUsLangProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import dev.mayaqq.endless.datagen.advancements.EndlessAdvancementsProvider;

public class EndlessDataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(EndlessAdvancementsProvider::new);
        pack.addProvider(EndlessRecipeProvider::new);
        pack.addProvider(EndlessEnUsLangProvider::new);
        pack.addProvider(EndlessTags.BiomeTags::new);
        pack.addProvider(EndlessTags.BlockTags::new);
        pack.addProvider(EndlessTags.ItemTags::new);
        pack.addProvider(EndlessTags.FluidTags::new);
    }
}
