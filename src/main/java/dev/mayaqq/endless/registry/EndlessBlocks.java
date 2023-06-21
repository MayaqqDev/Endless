package dev.mayaqq.endless.registry;

import dev.mayaqq.endless.registry.blocks.CorruptionBlock;
import dev.mayaqq.endless.registry.blocks.EggBaseGeneratorBlock;
import dev.mayaqq.endless.registry.blocks.MakeshiftVoidExtractorBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

import static dev.mayaqq.endless.Endless.id;

public class EndlessBlocks {
    public static final MakeshiftVoidExtractorBlock MAKESHIFT_VOID_EXTRACTOR_BLOCK = new MakeshiftVoidExtractorBlock(FabricBlockSettings.copy(Blocks.NETHERITE_BLOCK));
    public static final PillarBlock CHORUS_PLANT_ROOT = new PillarBlock(FabricBlockSettings.create().instrument(Instrument.BASS).strength(2.0F).sounds(BlockSoundGroup.NETHER_STEM).mapColor(MapColor.PALE_PURPLE));
    public static final Block CHORUS_PLANKS = new Block(FabricBlockSettings.create().strength(2.0F, 3.0F).mapColor(MapColor.PURPLE).sounds(BlockSoundGroup.NETHER_WOOD));
    public static final CorruptionBlock CORRUPTION = new CorruptionBlock(FabricBlockSettings.create().strength(0F, 0F).mapColor(MapColor.BLACK).sounds(BlockSoundGroup.SLIME));
    public static final EggBaseGeneratorBlock EGG_BASE_GENERATOR = new EggBaseGeneratorBlock(FabricBlockSettings.copy(Blocks.NETHERITE_BLOCK));

    public static void register() {
        Registry.register(Registries.BLOCK, id("makeshift_void_extractor"), MAKESHIFT_VOID_EXTRACTOR_BLOCK);
        Registry.register(Registries.ITEM, id("makeshift_void_extractor"), new BlockItem(MAKESHIFT_VOID_EXTRACTOR_BLOCK, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, id("chorus_plant_root"), CHORUS_PLANT_ROOT);
        Registry.register(Registries.ITEM, id("chorus_plant_root"), new BlockItem(CHORUS_PLANT_ROOT, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, id("chorus_planks"), CHORUS_PLANKS);
        Registry.register(Registries.ITEM, id("chorus_planks"), new BlockItem(CHORUS_PLANKS, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, id("corruption"), CORRUPTION);
        Registry.register(Registries.ITEM, id("corruption"), new BlockItem(CORRUPTION, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, id("egg_base_generator"), EGG_BASE_GENERATOR);
        Registry.register(Registries.ITEM, id("egg_base_generator"), new BlockItem(EGG_BASE_GENERATOR, new FabricItemSettings()));
    }
}
