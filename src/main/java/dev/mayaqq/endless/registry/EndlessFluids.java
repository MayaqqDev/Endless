package dev.mayaqq.endless.registry;

import dev.mayaqq.endless.registry.fluids.VoidFluid;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static dev.mayaqq.endless.Endless.id;

public class EndlessFluids {
    public static FlowableFluid STILL_VOID_FLUID;
    public static FlowableFluid FLOWING_VOID_FLUID;
    public static Item VOID_FLUID_BUCKET;
    public static Block VOID_FLUID;
    public static void register() {
        STILL_VOID_FLUID = Registry.register(Registries.FLUID, id("void_fluid"), new VoidFluid.Still());
        FLOWING_VOID_FLUID = Registry.register(Registries.FLUID, id("flowing_void_fluid"), new VoidFluid.Flowing());
        VOID_FLUID_BUCKET = Registry.register(Registries.ITEM, id("void_fluid_bucket"), new BucketItem(STILL_VOID_FLUID, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
        VOID_FLUID = Registry.register(Registries.BLOCK, id("void_fluid"), new FluidBlock(STILL_VOID_FLUID, Block.Settings.copy(Blocks.WATER)));

    }
}
