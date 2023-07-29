package dev.mayaqq.endless.registry;

import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import dev.mayaqq.endless.registry.blocks.CoreConnectorBlock;
import dev.mayaqq.endless.registry.blocks.CorruptionBlock;
import dev.mayaqq.endless.registry.blocks.TheCoreBlock;
import dev.mayaqq.endless.registry.blocks.MakeshiftVoidExtractorBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

import static dev.mayaqq.endless.Endless.id;

public class EndlessBlocks {
    public static final Block MAKESHIFT_VOID_EXTRACTOR_BLOCK = registerBlock("makeshift_void_extractor", new MakeshiftVoidExtractorBlock(FabricBlockSettings.copy(Blocks.NETHERITE_BLOCK)));
    // Chorus wood
    public static final Block CHORUS_PLANT_ROOT = registerBlock("chorus_plant_root", new PillarBlock(FabricBlockSettings.create().instrument(Instrument.BASS).strength(2.0F).sounds(BlockSoundGroup.NETHER_STEM).mapColor(MapColor.PALE_PURPLE)));
    public static final Block CHORUS_PLANKS = registerBlock("chorus_planks", new Block(FabricBlockSettings.copy(Blocks.CRIMSON_PLANKS).mapColor(MapColor.PURPLE)));
    public static final Block CHORUS_BUTTON = registerBlock("chorus_button", new ButtonBlock(FabricBlockSettings.copy(Blocks.CRIMSON_BUTTON).mapColor(MapColor.PURPLE), BlockSetType.CRIMSON, 10, true));
    public static final Block CHORUS_DOOR = registerBlock("chorus_door", new DoorBlock(FabricBlockSettings.copy(Blocks.CRIMSON_DOOR).mapColor(MapColor.PURPLE), BlockSetType.CRIMSON));
    public static final Block CHORUS_FENCE = registerBlock("chorus_fence", new FenceBlock(FabricBlockSettings.copy(Blocks.CRIMSON_FENCE).mapColor(MapColor.PURPLE)));
    public static final Block CHORUS_FENCE_GATE = registerBlock("chorus_fence_gate", new FenceGateBlock(FabricBlockSettings.copy(Blocks.CRIMSON_FENCE_GATE).mapColor(MapColor.PURPLE), WoodType.CRIMSON));
    public static final Block CHORUS_PRESSURE_PLATE = registerBlock("chorus_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(Blocks.CRIMSON_PRESSURE_PLATE).mapColor(MapColor.PURPLE), BlockSetType.CRIMSON));
    public static final Block CHORUS_SLAB = registerBlock("chorus_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.CRIMSON_SLAB).mapColor(MapColor.PURPLE)));
    public static final Block CHORUS_STAIRS = registerBlock("chorus_stairs", new StairsBlock(CHORUS_PLANKS.getDefaultState(), FabricBlockSettings.copy(Blocks.CRIMSON_STAIRS).mapColor(MapColor.PURPLE)));
    public static final Block CHORUS_TRAPDOOR = registerBlock("chorus_trapdoor", new TrapdoorBlock(FabricBlockSettings.copy(Blocks.CRIMSON_TRAPDOOR).mapColor(MapColor.PURPLE), BlockSetType.CRIMSON));
    public static final Block CHORUS_WOOD = registerBlock("chorus_wood", new Block(FabricBlockSettings.copy(Blocks.CRIMSON_HYPHAE).mapColor(MapColor.PURPLE)));
    public static final Block STRIPPED_CHORUS_PLANT_ROOT = registerBlock("stripped_chorus_plant_root", new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_CRIMSON_STEM).mapColor(MapColor.PURPLE)));
    public static final Block STRIPPED_CHORUS_WOOD = registerBlock("stripped_chorus_wood", new Block(FabricBlockSettings.copy(Blocks.STRIPPED_CRIMSON_HYPHAE).mapColor(MapColor.PURPLE)));

    // Sign stuff
    public static final Block CHORUS_SIGN = registerBlock("chorus_sign", new TerraformSignBlock(id("entity/signs/chorus"), FabricBlockSettings.copyOf(Blocks.CRIMSON_SIGN).mapColor(MapColor.PURPLE)), false);
    public static final Block CHORUS_WALL_SIGN = registerBlock("chorus_wall_sign", new TerraformSignBlock(id("entity/signs/chorus"), FabricBlockSettings.copyOf(Blocks.CRIMSON_WALL_SIGN).mapColor(MapColor.PURPLE)), false);
    public static final Block CHORUS_HANGING_SIGN = registerBlock("chorus_hanging_sign", new TerraformSignBlock(id("entity/signs/hanging/chorus"), FabricBlockSettings.copyOf(Blocks.CRIMSON_WALL_SIGN).mapColor(MapColor.PURPLE)), false);
    public static final Block CHORUS_HANGING_WALL_SIGN = registerBlock("chorus_hanging_wall_sign", new TerraformSignBlock(id("entity/signs/hanging/chorus"), FabricBlockSettings.copyOf(Blocks.CRIMSON_WALL_SIGN).mapColor(MapColor.PURPLE)), false);

    // Not wood anymore
    public static final Block CORRUPTION = registerBlock("corruption", new CorruptionBlock(FabricBlockSettings.create().strength(0F, 0F).mapColor(MapColor.BLACK).sounds(BlockSoundGroup.SLIME)));
    public static final Block THE_CORE = registerBlock("the_core", new TheCoreBlock(FabricBlockSettings.copy(Blocks.NETHERITE_BLOCK)));
    public static final Block CORE_CONNECTOR = registerBlock("core_connector", new CoreConnectorBlock(FabricBlockSettings.create().strength(0.5F, 0.5F)));

    public static void register() {
        // extra care items
        registerItem("chorus_sign", new SignItem(new Item.Settings().maxCount(16), CHORUS_SIGN, CHORUS_WALL_SIGN));
        registerItem("chorus_hanging_sign", new HangingSignItem(CHORUS_HANGING_SIGN, CHORUS_HANGING_WALL_SIGN, new Item.Settings().maxCount(16)));
    }

    protected static Block registerBlock(String id, Block block) {
        registerItem(id, new BlockItem(block, new FabricItemSettings()));
        return Registry.register(Registries.BLOCK, id(id), block);
    }

    protected static Block registerBlock(String id, Block block, boolean item) {
        if (item) registerItem(id, new BlockItem(block, new FabricItemSettings()));
        return Registry.register(Registries.BLOCK, id(id), block);
    }

    private static void registerItem(String id, Item item) {
        EndlessItems.registerItem(id, item);
    }
}
