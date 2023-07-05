package dev.mayaqq.endless.registry.blockEntities;

import com.google.common.collect.ImmutableMap;
import dev.mayaqq.endless.energy.storage.EndergyUser;
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

public class CoreConnectorBlockEntity extends BlockEntity implements EndergyUser {

    public static final Map<String, Direction> directions;

    public CoreConnectorBlockEntity(BlockPos pos, BlockState state) {
        super(EndlessBlockEntities.CORE_CONNECTOR, pos, state);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
    }

    @SuppressWarnings("rawtypes")
    public static void tick(World world, BlockPos pos, BlockState state, CoreConnectorBlockEntity be) {
        for (Property property : state.getProperties()) {
            if (property instanceof BooleanProperty booleanProperty) {
                if (state.get(booleanProperty) && directions.containsKey(booleanProperty.getName())) {
                    Direction direction = directions.get(booleanProperty.getName());
                    BlockPos offsetPos = pos.offset(direction);
                    BlockState offsetState = world.getBlockState(offsetPos);
                    BlockEntity offsetBe = world.getBlockEntity(offsetPos);
                }
            }
        }
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
