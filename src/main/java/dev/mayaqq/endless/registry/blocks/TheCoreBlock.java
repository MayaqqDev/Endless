package dev.mayaqq.endless.registry.blocks;

import dev.mayaqq.endless.Endless;
import dev.mayaqq.endless.energy.storage.EndergyUser;
import dev.mayaqq.endless.registry.EndlessBlockEntities;
import dev.mayaqq.endless.registry.blockEntities.TheCoreBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class TheCoreBlock extends BlockWithEntity implements EndergyUser {
    public static final IntProperty LEVEL = IntProperty.of("level", 0, 3);
    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");
    public TheCoreBlock(Settings settings) {
        super(settings.luminance((state) -> state.get(ACTIVE) ? 10 : 0));
        setDefaultState(getDefaultState().with(LEVEL, 0));
        setDefaultState(getDefaultState().with(ACTIVE, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
        builder.add(ACTIVE);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TheCoreBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, EndlessBlockEntities.EGG_BASE_GENERATOR_ENTITY, TheCoreBlockEntity::tick);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public void recheck(World world, BlockPos pos, BlockState state, ArrayList<BlockPos> ignored) {
        if (world.isClient) return;
        for (Direction direction : Direction.values()) {
            BlockState neighborState = world.getBlockState(pos.offset(direction));
            if (neighborState.getBlock() instanceof CoreConnectorBlock) {
                world.setBlockState(pos.offset(direction), neighborState.with(CoreConnectorBlock.CONNECTED_TO_CORE, true));
            }
        }
    }
}
