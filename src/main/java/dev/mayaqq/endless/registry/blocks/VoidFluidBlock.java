package dev.mayaqq.endless.registry.blocks;

import dev.mayaqq.endless.interfaces.EntityExtension;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VoidFluidBlock extends FluidBlock {
    public VoidFluidBlock(FlowableFluid fluid, Settings settings) {
        super(fluid, settings);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        ((EntityExtension) entity).tickEntityVoid();
    }
}