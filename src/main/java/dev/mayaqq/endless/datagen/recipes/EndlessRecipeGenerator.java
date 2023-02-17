package dev.mayaqq.endless.datagen.recipes;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.advancement.criterion.ItemCriterion;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

import static dev.mayaqq.endless.Endless.id;
import static dev.mayaqq.endless.registry.ItemRegistry.CHORUS_STRING;

public class EndlessRecipeGenerator extends FabricRecipeProvider {
    public EndlessRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

    }
}
