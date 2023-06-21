package dev.mayaqq.endless.registry;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

import static dev.mayaqq.endless.Endless.id;

public class EndlessTags {
    public static final TagKey<Biome> HAS_CHORUS_ROOTS = TagKey.of(RegistryKeys.BIOME, id("has_chorus_roots"));
    public static final TagKey<Block> VOID_REPLACEABLE = TagKey.of(RegistryKeys.BLOCK, id("void_replaceable"));
    public static void register() {}
}
