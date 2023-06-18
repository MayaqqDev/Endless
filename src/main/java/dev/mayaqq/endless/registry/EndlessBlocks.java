package dev.mayaqq.endless.registry;

import dev.mayaqq.endless.registry.blocks.MakeshiftVoidExtractorBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;

import static dev.mayaqq.endless.Endless.id;

public class EndlessBlocks {
    public static final MakeshiftVoidExtractorBlock MAKESHIFT_VOID_EXTRACTOR_BLOCK = new MakeshiftVoidExtractorBlock(FabricBlockSettings.copy(Blocks.NETHERITE_BLOCK));
    public static final Block CHORUS_PLANT_ROOT = new Block(FabricBlockSettings.create().instrument(Instrument.BASS).strength(2.0F).sounds(BlockSoundGroup.NETHER_STEM).mapColor(DyeColor.BROWN));

    public static void register() {
        Registry.register(Registries.BLOCK, id("makeshift_void_extractor"), MAKESHIFT_VOID_EXTRACTOR_BLOCK);
        Registry.register(Registries.ITEM, id("makeshift_void_extractor"), new BlockItem(MAKESHIFT_VOID_EXTRACTOR_BLOCK, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, id("chorus_plant_root"), CHORUS_PLANT_ROOT);
        Registry.register(Registries.ITEM, id("chorus_plant_root"), new BlockItem(CHORUS_PLANT_ROOT, new FabricItemSettings()));
    }
}
