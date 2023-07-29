package dev.mayaqq.endless.registry.blockEntities;

import com.google.common.collect.ImmutableMap;
import dev.mayaqq.endless.registry.EndlessBlockEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@SuppressWarnings("unchecked")
public class CoreConnectorBlockEntity extends BlockEntity {

    public static final Map<String, Direction> directions;

    public CoreConnectorBlockEntity(BlockPos pos, BlockState state) {
        super(EndlessBlockEntities.CORE_CONNECTOR, pos, state);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
    }

    public static void tick(World world, BlockPos pos, BlockState state, CoreConnectorBlockEntity be) {
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound nbtCompound = new NbtCompound();
        this.writeNbt(nbtCompound);
        return nbtCompound;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @SuppressWarnings("DataFlowIssue")
    public void updateInClientWorld() {
        world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), Block.NO_REDRAW);
    }

    static {
        directions = (Map<String, Direction>) (Object) ImmutableMap.builder()
                .put("north", Direction.NORTH)
                .put("east", Direction.EAST)
                .put("south", Direction.SOUTH)
                .put("west", Direction.WEST)
                .put("up", Direction.UP)
                .put("down", Direction.DOWN)
                .build();
    }
}
