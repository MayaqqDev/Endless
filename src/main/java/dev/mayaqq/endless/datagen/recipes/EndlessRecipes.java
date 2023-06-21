package dev.mayaqq.endless.datagen.recipes;

import dev.mayaqq.endless.registry.EndlessBlocks;
import dev.mayaqq.endless.registry.EndlessItems;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

public class EndlessRecipes {
    public static void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, EndlessBlocks.CHORUS_PLANKS)
                .input(EndlessBlocks.CHORUS_PLANT_ROOT)
                .criterion(FabricRecipeProvider.hasItem(EndlessBlocks.CHORUS_PLANT_ROOT), FabricRecipeProvider.conditionsFromItem(EndlessBlocks.CHORUS_PLANT_ROOT))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, EndlessItems.CHORUS_STRING)
                .input(Items.CHORUS_FRUIT)
                .criterion(FabricRecipeProvider.hasItem(Items.CHORUS_FRUIT), FabricRecipeProvider.conditionsFromItem(Items.CHORUS_FRUIT))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, EndlessItems.VOID_CODEX)
                .input(Items.BOOK)
                .input(Items.CHORUS_FRUIT)
                .criterion(FabricRecipeProvider.hasItem(Items.BOOK), FabricRecipeProvider.conditionsFromItem(Items.BOOK))
                .criterion(FabricRecipeProvider.hasItem(Items.CHORUS_FRUIT), FabricRecipeProvider.conditionsFromItem(Items.CHORUS_FRUIT))
                .offerTo(exporter);
    }
}
