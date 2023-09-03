package dev.mayaqq.endless.datagen.tags;

import dev.mayaqq.endless.registry.EndlessBlocks;
import dev.mayaqq.endless.registry.EndlessFluids;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.tag.vanilla.VanillaBiomeTagProvider;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.concurrent.CompletableFuture;

import static dev.mayaqq.endless.Endless.id;

public class EndlessTags {
    public static class ItemTags extends FabricTagProvider.ItemTagProvider {
        public ItemTags(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
            super(output, registryLookupFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup lookup) {

        }
    }

    public static class BlockTags extends FabricTagProvider.BlockTagProvider {
        // vanilla tags
        public static final TagKey<Block> LOGS = TagKey.of(RegistryKeys.BLOCK, mcId("logs"));
        public static final TagKey<Block> PLANKS = TagKey.of(RegistryKeys.BLOCK, mcId("planks"));
        public static final TagKey<Block> MNEABLE_AXE = TagKey.of(RegistryKeys.BLOCK, mcId("mineable/axe"));
        // endless tags
        public static final TagKey<Block> VOID_REPLACEABLE = TagKey.of(RegistryKeys.BLOCK, id("void_replaceable"));
        public static final TagKey<Block> ENDER_GROUND = TagKey.of(RegistryKeys.BLOCK, id("ender_ground"));

        public BlockTags(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
            super(output, registryLookupFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup lookup) {
            getOrCreateTagBuilder(VOID_REPLACEABLE)
                    .add(Blocks.END_STONE);
            getOrCreateTagBuilder(LOGS)
                    .add(EndlessBlocks.CHORUS_PLANT_ROOT);
            getOrCreateTagBuilder(PLANKS)
                    .add(EndlessBlocks.CHORUS_PLANKS);
            getOrCreateTagBuilder(MNEABLE_AXE)
                    .add(EndlessBlocks.CHORUS_PLANT_ROOT)
                    .add(EndlessBlocks.CHORUS_PLANKS);
            getOrCreateTagBuilder(ENDER_GROUND)
                    .add(Blocks.END_STONE)
                    .add(EndlessBlocks.ENDER_GRASS);
        }
    }

    public static class BiomeTags extends VanillaBiomeTagProvider {

        // endless tags
        public static final TagKey<Biome> HAS_CHORUS_ROOTS = TagKey.of(RegistryKeys.BIOME, id("has_chorus_roots"));

        public BiomeTags(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
            super(output, registryLookupFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup lookup) {
            getOrCreateTagBuilder(HAS_CHORUS_ROOTS)
                    .add(getBiomeKey(mcId("end_highlands")))
                    .add(getBiomeKey(mcId("end_midlands")));

        }

        private static RegistryKey<Biome> getBiomeKey(Identifier id) {
            return RegistryKey.of(RegistryKeys.BIOME, id);
        }
    }

    public static Identifier mcId(String path) {
        return new Identifier("minecraft", path);
    }

    public static class FluidTags extends FabricTagProvider.FluidTagProvider {

        // vanilla tags
        public static final TagKey<Fluid> WATER = TagKey.of(RegistryKeys.FLUID, mcId("water"));

        public FluidTags(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
            super(output, registryLookupFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup lookup) {
            getOrCreateTagBuilder(WATER)
                    .add(EndlessFluids.STILL_VOID_FLUID)
                    .add(EndlessFluids.FLOWING_VOID_FLUID);
        }
    }
}
