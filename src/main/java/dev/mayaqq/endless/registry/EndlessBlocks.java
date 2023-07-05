package dev.mayaqq.endless.registry;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import dev.mayaqq.endless.registry.blocks.CoreConnectorBlock;
import dev.mayaqq.endless.registry.blocks.CorruptionBlock;
import dev.mayaqq.endless.registry.blocks.EggBaseGeneratorBlock;
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
    public static final MakeshiftVoidExtractorBlock MAKESHIFT_VOID_EXTRACTOR_BLOCK = new MakeshiftVoidExtractorBlock(FabricBlockSettings.copy(Blocks.NETHERITE_BLOCK));
    // Chorus wood
    public static final PillarBlock CHORUS_PLANT_ROOT = new PillarBlock(FabricBlockSettings.create().instrument(Instrument.BASS).strength(2.0F).sounds(BlockSoundGroup.NETHER_STEM).mapColor(MapColor.PALE_PURPLE));
    public static final Block CHORUS_PLANKS = new Block(FabricBlockSettings.copy(Blocks.CRIMSON_PLANKS).mapColor(MapColor.PURPLE));
    public static final ButtonBlock CHORUS_BUTTON = new ButtonBlock(FabricBlockSettings.copy(Blocks.CRIMSON_BUTTON).mapColor(MapColor.PURPLE), BlockSetType.CRIMSON, 10, true);
    public static final DoorBlock CHORUS_DOOR = new DoorBlock(FabricBlockSettings.copy(Blocks.CRIMSON_DOOR).mapColor(MapColor.PURPLE), BlockSetType.CRIMSON);
    public static final FenceBlock CHORUS_FENCE = new FenceBlock(FabricBlockSettings.copy(Blocks.CRIMSON_FENCE).mapColor(MapColor.PURPLE));
    public static final FenceGateBlock CHORUS_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(Blocks.CRIMSON_FENCE_GATE).mapColor(MapColor.PURPLE), WoodType.CRIMSON);
    public static final PressurePlateBlock CHORUS_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(Blocks.CRIMSON_PRESSURE_PLATE).mapColor(MapColor.PURPLE), BlockSetType.CRIMSON);
    public static final SlabBlock CHORUS_SLAB = new SlabBlock(FabricBlockSettings.copy(Blocks.CRIMSON_SLAB).mapColor(MapColor.PURPLE));
    public static final StairsBlock CHORUS_STAIRS = new StairsBlock(CHORUS_PLANKS.getDefaultState(), FabricBlockSettings.copy(Blocks.CRIMSON_STAIRS).mapColor(MapColor.PURPLE));
    public static final TrapdoorBlock CHORUS_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings.copy(Blocks.CRIMSON_TRAPDOOR).mapColor(MapColor.PURPLE), BlockSetType.CRIMSON);
    public static final Block CHORUS_WOOD = new Block(FabricBlockSettings.copy(Blocks.CRIMSON_HYPHAE).mapColor(MapColor.PURPLE));
    public static final PillarBlock STRIPPED_CHORUS_PLANT_ROOT = new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_CRIMSON_STEM).mapColor(MapColor.PURPLE));
    public static final Block STRIPPED_CHORUS_WOOD = new Block(FabricBlockSettings.copy(Blocks.STRIPPED_CRIMSON_HYPHAE).mapColor(MapColor.PURPLE));

        // boats, not really blocks but whatever
    public static final Item chorus_boat_item = TerraformBoatItemHelper.registerBoatItem(id("chorus_boat"), TerraformBoatTypeRegistry.createKey(id("chorus_boat")), false);
    public static final Item chest_chorus_boat_item = TerraformBoatItemHelper.registerBoatItem(id("chest_chorus_boat"), TerraformBoatTypeRegistry.createKey(id("chest_chorus_boat")), true);
    public static final TerraformBoatType chorus_boat = new TerraformBoatType.Builder()
            .item(chorus_boat_item)
            .chestItem(chest_chorus_boat_item)
            .planks(CHORUS_PLANKS.asItem())
            .build();

    // Sign stuff
    public static final Block CHORUS_SIGN = new TerraformSignBlock(id("entity/signs/chorus"), FabricBlockSettings.copyOf(Blocks.CRIMSON_SIGN).mapColor(MapColor.PURPLE));
    public static final Block CHORUS_WALL_SIGN = new TerraformSignBlock(id("entity/signs/chorus"), FabricBlockSettings.copyOf(Blocks.CRIMSON_WALL_SIGN).mapColor(MapColor.PURPLE));
    public static final Block CHORUS_HANGING_SIGN = new TerraformSignBlock(id("entity/signs/hanging/chorus"), FabricBlockSettings.copyOf(Blocks.CRIMSON_WALL_SIGN).mapColor(MapColor.PURPLE));
    public static final Block CHORUS_HANGING_WALL_SIGN = new TerraformSignBlock(id("entity/signs/hanging/chorus"), FabricBlockSettings.copyOf(Blocks.CRIMSON_WALL_SIGN).mapColor(MapColor.PURPLE));
    public static final Item chorus_sign_item = new SignItem(new Item.Settings().maxCount(16), CHORUS_SIGN, CHORUS_WALL_SIGN);
    public static final Item chorus_hanging_sign_item = new HangingSignItem(CHORUS_HANGING_SIGN, CHORUS_HANGING_WALL_SIGN, new Item.Settings().maxCount(16));

    // Not wood anymore
    public static final CorruptionBlock CORRUPTION = new CorruptionBlock(FabricBlockSettings.create().strength(0F, 0F).mapColor(MapColor.BLACK).sounds(BlockSoundGroup.SLIME));
    public static final EggBaseGeneratorBlock EGG_BASE_GENERATOR = new EggBaseGeneratorBlock(FabricBlockSettings.copy(Blocks.NETHERITE_BLOCK));
    public static final CoreConnectorBlock CORE_CONNECTOR = new CoreConnectorBlock(FabricBlockSettings.create().strength(0.5F, 0.5F));

    public static void register() {
        // woooooooooooooooood
        Registry.register(Registries.BLOCK, id("chorus_plant_root"), CHORUS_PLANT_ROOT);
        Registry.register(Registries.ITEM, id("chorus_plant_root"), new BlockItem(CHORUS_PLANT_ROOT, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, id("chorus_planks"), CHORUS_PLANKS);
        Registry.register(Registries.ITEM, id("chorus_planks"), new BlockItem(CHORUS_PLANKS, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, id("chorus_door"), CHORUS_DOOR);
        Registry.register(Registries.ITEM, id("chorus_door"), new BlockItem(CHORUS_DOOR, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, id("chorus_fence"), CHORUS_FENCE);
        Registry.register(Registries.ITEM, id("chorus_fence"), new BlockItem(CHORUS_FENCE, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, id("chorus_fence_gate"), CHORUS_FENCE_GATE);
        Registry.register(Registries.ITEM, id("chorus_fence_gate"), new BlockItem(CHORUS_FENCE_GATE, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, id("chorus_slab"), CHORUS_SLAB);
        Registry.register(Registries.ITEM, id("chorus_slab"), new BlockItem(CHORUS_SLAB, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, id("chorus_stairs"), CHORUS_STAIRS);
        Registry.register(Registries.ITEM, id("chorus_stairs"), new BlockItem(CHORUS_STAIRS, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, id("chorus_trapdoor"), CHORUS_TRAPDOOR);
        Registry.register(Registries.ITEM, id("chorus_trapdoor"), new BlockItem(CHORUS_TRAPDOOR, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, id("chorus_button"), CHORUS_BUTTON);
        Registry.register(Registries.ITEM, id("chorus_button"), new BlockItem(CHORUS_BUTTON, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, id("chorus_pressure_plate"), CHORUS_PRESSURE_PLATE);
        Registry.register(Registries.ITEM, id("chorus_pressure_plate"), new BlockItem(CHORUS_PRESSURE_PLATE, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, id("chorus_wood"), CHORUS_WOOD);
        Registry.register(Registries.ITEM, id("chorus_wood"), new BlockItem(CHORUS_WOOD, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, id("stripped_chorus_plant_root"), STRIPPED_CHORUS_PLANT_ROOT);
        Registry.register(Registries.ITEM, id("stripped_chorus_plant_root"), new BlockItem(STRIPPED_CHORUS_PLANT_ROOT, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, id("stripped_chorus_wood"), STRIPPED_CHORUS_WOOD);
        Registry.register(Registries.ITEM, id("stripped_chorus_wood"), new BlockItem(STRIPPED_CHORUS_WOOD, new FabricItemSettings()));
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, id("chorus_boat"), chorus_boat);
        Registry.register(Registries.BLOCK, id("chorus_sign"), CHORUS_SIGN);
        Registry.register(Registries.ITEM, id("chorus_sign"), chorus_sign_item);
        Registry.register(Registries.BLOCK, id("chorus_wall_sign"), CHORUS_WALL_SIGN);
        Registry.register(Registries.BLOCK, id("chorus_hanging_sign"), CHORUS_HANGING_SIGN);
        Registry.register(Registries.ITEM, id("chorus_hanging_sign"), chorus_hanging_sign_item);
        Registry.register(Registries.BLOCK, id("chorus_hanging_wall_sign"), CHORUS_HANGING_WALL_SIGN);

        Registry.register(Registries.BLOCK, id("makeshift_void_extractor"), MAKESHIFT_VOID_EXTRACTOR_BLOCK);
        Registry.register(Registries.ITEM, id("makeshift_void_extractor"), new BlockItem(MAKESHIFT_VOID_EXTRACTOR_BLOCK, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, id("corruption"), CORRUPTION);
        Registry.register(Registries.ITEM, id("corruption"), new BlockItem(CORRUPTION, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, id("egg_base_generator"), EGG_BASE_GENERATOR);
        Registry.register(Registries.ITEM, id("egg_base_generator"), new BlockItem(EGG_BASE_GENERATOR, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, id("core_connector"), CORE_CONNECTOR);
        Registry.register(Registries.ITEM, id("core_connector"), new BlockItem(CORE_CONNECTOR, new FabricItemSettings()));
    }
}
