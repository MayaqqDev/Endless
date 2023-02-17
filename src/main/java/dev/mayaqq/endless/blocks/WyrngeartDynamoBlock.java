package dev.mayaqq.endless.blocks;

import dev.mayaqq.endless.blockEntities.WyrngeartDynamoEntity;
import dev.mayaqq.endless.registry.BlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WyrngeartDynamoBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final BooleanProperty GENERATING = BooleanProperty.of("generating");

    public WyrngeartDynamoBlock(Settings settings) {
        super(settings.luminance((state) -> state.get(GENERATING) ? 10 : 0));
        setDefaultState(getDefaultState().with(GENERATING, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(GENERATING);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WyrngeartDynamoEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, BlockRegistry.WYRNGEART_DYNAMO_ENTITY, WyrngeartDynamoEntity::tick);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
