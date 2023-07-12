package dev.mayaqq.endless.registry.blocks;

import com.google.common.collect.ImmutableMap;
import dev.mayaqq.endless.registry.EndlessBlockEntities;
import dev.mayaqq.endless.registry.blockEntities.CoreConnectorBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@SuppressWarnings("unchecked")
public class CoreConnectorBlock extends BlockWithEntity {
    public static final Map<Direction, BooleanProperty> CONNECTIONS;
    public static final BooleanProperty CONNECTED_TO_HEART = BooleanProperty.of("connected_to_heart");
    public static final IntProperty CONNECTED_MACHINES_COUNT = IntProperty.of("connected_machines_count", 0, 6);
    public static final BooleanProperty CHECKING_CONNECTIONS = BooleanProperty.of("checking_connections");
    public static final VoxelShape CORE = Block.createCuboidShape(5, 5, 5, 11, 11 ,11);
    public static final Map<Direction, VoxelShape> SHAPES;


    public CoreConnectorBlock(Settings settings) {
        super(settings);
        BlockState defaultState = this.getDefaultState();
        for (Direction direction : Direction.values()) {
            defaultState = defaultState.with(CONNECTIONS.get(direction), false);
        }
        defaultState = defaultState
                .with(CONNECTED_TO_HEART, false)
                .with(CONNECTED_MACHINES_COUNT, 0)
                .with(CHECKING_CONNECTIONS, false);
        this.setDefaultState(defaultState);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        var shape = CORE;

        for (Direction direction : Direction.values()) {
            if(state.get(CONNECTIONS.get(direction)))
                shape = VoxelShapes.union(shape, SHAPES.get(direction));
        }
        return shape;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        for (Direction direction : Direction.values()) {
            builder.add(CONNECTIONS.get(direction));
        }
        builder.add(CONNECTED_TO_HEART);
        builder.add(CONNECTED_MACHINES_COUNT);
        builder.add(CHECKING_CONNECTIONS);
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
                if (blockState.getBlock() instanceof CoreConnectorBlock) {
                    world.setBlockState(pos.offset(direction), blockState.with(CONNECTIONS.get(direction.getOpposite()), true));
                    state = state.with(CONNECTIONS.get(direction), true);
                }
            }
        }
        return state;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        for (Direction direction : Direction.values()) {
            BlockState blockState = world.getBlockState(pos.offset(direction));
            if (world.getBlockEntity(pos.offset(direction)) instanceof CoreConnectorBlockEntity) {
                world.setBlockState(pos.offset(direction), blockState.with(CONNECTIONS.get(direction.getOpposite()), false));
            }
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
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
