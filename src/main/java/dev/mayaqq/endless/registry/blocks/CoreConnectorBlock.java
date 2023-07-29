package dev.mayaqq.endless.registry.blocks;

import com.google.common.collect.ImmutableMap;
import dev.mayaqq.endless.energy.storage.EndergyUser;
import dev.mayaqq.endless.registry.EndlessBlockEntities;
import dev.mayaqq.endless.registry.blockEntities.CoreConnectorBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Map;

import static dev.mayaqq.endless.Endless.LOGGER;

@SuppressWarnings({"unchecked", "deprecation"})
public class CoreConnectorBlock extends BlockWithEntity implements EndergyUser {
    public static final Map<Direction, BooleanProperty> CONNECTIONS;
    public static final BooleanProperty CONNECTED_TO_CORE = BooleanProperty.of("connected_to_core");
    public static final BooleanProperty CONNECTED_TO_ACTIVE_CORE = BooleanProperty.of("connected_to_active_core");

    public static final VoxelShape CORE = Block.createCuboidShape(5, 5, 5, 11, 11 ,11);
    public static final Map<Direction, VoxelShape> SHAPES;

    public CoreConnectorBlock(Settings settings) {
        super(settings);
        BlockState defaultState = this.getDefaultState();
        for (Direction direction : Direction.values()) {
            defaultState = defaultState.with(CONNECTIONS.get(direction), false);
        }
        defaultState = defaultState
                .with(CONNECTED_TO_CORE, false)
                .with(CONNECTED_TO_ACTIVE_CORE, false);
        this.setDefaultState(defaultState);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        var shape = CORE;

        for (Direction direction : Direction.values()) {
            if (state.get(CONNECTIONS.get(direction)))
                shape = VoxelShapes.union(shape, SHAPES.get(direction));
        }
        return shape;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        for (Direction direction : Direction.values()) {
            builder.add(CONNECTIONS.get(direction));
        }
        builder.add(CONNECTED_TO_CORE);
        builder.add(CONNECTED_TO_ACTIVE_CORE);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CoreConnectorBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, EndlessBlockEntities.CORE_CONNECTOR, CoreConnectorBlockEntity::tick);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = this.getDefaultState();
        if (ctx.canPlace()) {
            World world = ctx.getWorld();
            BlockPos pos = ctx.getBlockPos();
            for (Direction direction : Direction.values()) {
                BlockState blockState = world.getBlockState(pos.offset(direction));
                if (blockState.getBlock() instanceof EndergyUser block) {
                    if (block instanceof TheCoreBlock) {
                        state = state.with(CONNECTED_TO_CORE, true);
                    }
                    if (block instanceof CoreConnectorBlock) {
                        if (blockState.get(CONNECTED_TO_CORE)) {
                            state = state.with(CONNECTED_TO_CORE, true);
                        }
                    }
                    state = state.with(CONNECTIONS.get(direction), true);
                }
            }
        }
        return state;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!world.isClient()) {
            if (neighborState.getBlock() instanceof EndergyUser block) {
                state = state.with(CONNECTED_TO_CORE, false);
                if (block instanceof TheCoreBlock) {
                    state = state.with(CONNECTED_TO_CORE, true);
                }
                state = state.with(CONNECTIONS.get(direction), true);
                if (block instanceof CoreConnectorBlock) {
                    if (neighborState.get(CONNECTED_TO_CORE)) {
                        state = state.with(CONNECTED_TO_CORE, true);
                    }
                }
            } else {
                state = state.with(CONNECTIONS.get(direction), false);
            }
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient()) {
            for (Direction dir : Direction.values()) {
                if (state.get(CONNECTIONS.get(dir))) {
                    BlockState blockState = world.getBlockState(pos.offset(dir));
                    if (blockState.getBlock() instanceof EndergyUser block) {
                        block.recheck(world, pos.offset(dir), blockState, new ArrayList<>());
                    }
                }
            }
        }

        super.onBreak(world, pos, state, player);
    }

    @Override
    public void recheck(World world, BlockPos pos, BlockState state, ArrayList<BlockPos> ignored) {
        ignored.add(pos);
        world.setBlockState(pos, state.with(CONNECTED_TO_CORE, false));
        for (Direction dir : Direction.values()) {
            BlockPos offset = pos.offset(dir);
            if (world.getBlockState(offset).getBlock() instanceof EndergyUser block) {
                if (!ignored.contains(offset)) {
                    BlockState blockState = world.getBlockState(offset);
                    block.recheck(world, offset, blockState, ignored);
                }
            }
        }
    }

    static {
        CONNECTIONS = (Map<Direction, BooleanProperty>) (Object) ImmutableMap.builder()
                .put(Direction.NORTH, BooleanProperty.of("north"))
                .put(Direction.EAST, BooleanProperty.of("east"))
                .put(Direction.SOUTH, BooleanProperty.of("south"))
                .put(Direction.WEST, BooleanProperty.of("west"))
                .put(Direction.UP, BooleanProperty.of("up"))
                .put(Direction.DOWN, BooleanProperty.of("down"))
                .build();
        SHAPES = (Map<Direction, VoxelShape>) (Object) ImmutableMap.builder()
                .put(Direction.WEST, Block.createCuboidShape(0, 5, 5, 5, 11 ,11))
                .put(Direction.EAST, Block.createCuboidShape(11, 5, 5, 16, 11 ,11))
                .put(Direction.NORTH, Block.createCuboidShape(5, 5, 0, 11, 11 ,5))
                .put(Direction.SOUTH, Block.createCuboidShape(5, 5, 11, 11, 11 ,16))
                .put(Direction.DOWN, Block.createCuboidShape(5, 0, 5, 11, 5 ,11))
                .put(Direction.UP, Block.createCuboidShape(5, 11, 5, 11, 16 ,11))
                .build();
    }
}
