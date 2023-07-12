package dev.mayaqq.endless.registry.blocks;

import dev.mayaqq.endless.registry.EndlessBlockEntities;
import dev.mayaqq.endless.registry.blockEntities.EggBaseGeneratorBlockEntity;
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
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EggBaseGeneratorBlock extends BlockWithEntity {
    public static final IntProperty LEVEL = IntProperty.of("level", 0, 3);
    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");
    public EggBaseGeneratorBlock(Settings settings) {
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
        return new EggBaseGeneratorBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, EndlessBlockEntities.EGG_BASE_GENERATOR_ENTITY, EggBaseGeneratorBlockEntity::tick);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
}
