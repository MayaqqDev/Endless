package dev.mayaqq.endless.registry;

import dev.mayaqq.endless.registry.blocks.VoidFluidBlock;
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
    public static FlowableFluid STILL_VOID_FLUID = registerFluid("void_fluid", new VoidFluid.Still());
    public static FlowableFluid FLOWING_VOID_FLUID = registerFluid("flowing_void_fluid", new VoidFluid.Flowing());
    public static Item VOID_FLUID_BUCKET = EndlessItems.registerItem("void_fluid_bucket", new BucketItem(STILL_VOID_FLUID, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static Block VOID_FLUID = EndlessBlocks.registerBlock("void_fluid", new VoidFluidBlock(STILL_VOID_FLUID, Block.Settings.copy(Blocks.WATER)));

    public static void register() {}

    private static FlowableFluid registerFluid(String id, FlowableFluid fluid) {
        return Registry.register(Registries.FLUID, id(id), fluid);
    }
}
