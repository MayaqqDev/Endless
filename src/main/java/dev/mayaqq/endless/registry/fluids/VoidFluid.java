package dev.mayaqq.endless.registry.fluids;

import dev.mayaqq.endless.registry.EndlessFluids;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.dimension.DimensionType;

public abstract class VoidFluid extends FlowableFluid {
    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == getStill() || fluid == getFlowing();
    }

    @Override
    protected boolean isInfinite(World world) {
        return false;
    }

    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        final BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
        Block.dropStacks(state, world, pos, blockEntity);
    }

    @Override
    protected boolean canBeReplacedWith(FluidState fluidState, BlockView blockView, BlockPos blockPos, Fluid fluid, Direction direction) {
        return false;
    }

    @Override
    protected int getFlowSpeed(WorldView worldView) {
        DimensionType dimensionType = worldView.getDimension();
        if (!dimensionType.hasCeiling() && !dimensionType.bedWorks()) {
            return 4;
        }
        return 1;
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView worldView) {
        DimensionType dimensionType = worldView.getDimension();
        if (!dimensionType.hasCeiling() && !dimensionType.bedWorks()) {
            return 1;
        }
        return 2;
    }

    @Override
    public int getTickRate(WorldView worldView) {
        DimensionType dimensionType = worldView.getDimension();
        if (!dimensionType.hasCeiling() && !dimensionType.bedWorks()) {
            return 7;
        }
        return 30;
    }

    @Override
    protected float getBlastResistance() {
        return 100.0F;
    }

    @Override
    public Fluid getStill() {
        return EndlessFluids.STILL_VOID_FLUID;
    }

    @Override
    public Fluid getFlowing() {
        return EndlessFluids.FLOWING_VOID_FLUID;
    }

    @Override
    public Item getBucketItem() {
        return EndlessFluids.VOID_FLUID_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        return EndlessFluids.VOID_FLUID.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
    }

    public static class Flowing extends VoidFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return false;
        }
    }

    public static class Still extends VoidFluid {
        @Override
        public int getLevel(FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return true;
        }
    }


}
