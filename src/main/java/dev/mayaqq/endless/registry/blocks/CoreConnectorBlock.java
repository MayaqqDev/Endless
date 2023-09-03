package dev.mayaqq.endless.registry.blocks;

import com.google.common.collect.ImmutableMap;
import dev.mayaqq.endless.Endless;
import dev.mayaqq.endless.network.node.ConnectorBlockNode;
import dev.mayaqq.endless.network.node.EndergyNetworkBlockNode;
import dev.mayaqq.endless.registry.blocks.base.NetworkBlock;
import net.minecraft.block.*;
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

import java.util.Map;

@SuppressWarnings({"unchecked", "deprecation"})
public class CoreConnectorBlock extends NetworkBlock {
    public static final Map<Direction, BooleanProperty> CONNECTIONS;
    public static final BooleanProperty CONNECTED_TO_CORE = BooleanProperty.of("connected_to_core");

    public static final VoxelShape CORE = Block.createCuboidShape(5, 5, 5, 11, 11 ,11);
    public static final Map<Direction, VoxelShape> SHAPES;

    public CoreConnectorBlock(Settings settings) {
        super(settings);
        BlockState defaultState = this.getDefaultState();
        for (Direction direction : Direction.values()) {
            defaultState = defaultState.with(CONNECTIONS.get(direction), false);
        }
        defaultState = defaultState
                .with(CONNECTED_TO_CORE, false);
        this.setDefaultState(defaultState);
    }

    @Override
    public EndergyNetworkBlockNode getNode() {
        return ConnectorBlockNode.INSTANCE;
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
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = this.getDefaultState();
        if (ctx.canPlace()) {
            World world = ctx.getWorld();
            BlockPos pos = ctx.getBlockPos();
            for (Direction direction : Direction.values()) {
                BlockState blockState = world.getBlockState(pos.offset(direction));
                if (blockState.getBlock() instanceof NetworkBlock) {
                    state = state.with(CONNECTIONS.get(direction), true);
                }
            }
        }
        return state;
    }


    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!world.isClient()) {
            if (neighborState.getBlock() instanceof NetworkBlock) {
                state = state.with(CONNECTIONS.get(direction), true);
            } else {
                state = state.with(CONNECTIONS.get(direction), false);
            }
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
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
