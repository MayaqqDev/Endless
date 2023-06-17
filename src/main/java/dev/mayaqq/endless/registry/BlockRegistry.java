package dev.mayaqq.endless.registry;

import dev.mayaqq.endless.blocks.MakeshiftVoidExtractorBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static dev.mayaqq.endless.Endless.id;

public class BlockRegistry {
    public static final MakeshiftVoidExtractorBlock MAKESHIFT_VOID_EXTRACTOR_BLOCK = new MakeshiftVoidExtractorBlock(FabricBlockSettings.copy(Blocks.NETHERITE_BLOCK));

    public static void register() {
        Registry.register(Registries.BLOCK, id("makeshift_void_extractor"), MAKESHIFT_VOID_EXTRACTOR_BLOCK);
        Registry.register(Registries.ITEM, id("makeshift_void_extractor"), new BlockItem(MAKESHIFT_VOID_EXTRACTOR_BLOCK, new FabricItemSettings()));
    }
}
