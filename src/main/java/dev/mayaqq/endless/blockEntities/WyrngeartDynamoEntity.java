package dev.mayaqq.endless.blockEntities;

import dev.mayaqq.endless.registry.MultiblockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vazkii.patchouli.api.IMultiblock;

import static dev.mayaqq.endless.Endless.id;
import static dev.mayaqq.endless.blocks.WyrngeartDynamoBlock.GENERATING;
import static dev.mayaqq.endless.registry.BlockRegistry.WYRNGEART_DYNAMO_ENTITY;


public class WyrngeartDynamoEntity extends BlockEntity {

    private int dragonEnergy = 0;

    public WyrngeartDynamoEntity(BlockPos pos, BlockState state) {
        super(WYRNGEART_DYNAMO_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, WyrngeartDynamoEntity be) {
        //only run every so milliseconds
        if (world.getBlockState(pos).get(GENERATING)) {
            //summon particles around the block
            world.addParticle(ParticleTypes.DRAGON_BREATH, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0, 0, 0);
        }
        if (world.getTime() % 20 != 0) return;
        IMultiblock multiblock = MultiblockRegistry.MULTIBLOCKS.get(id("wyrngeart_dynamo_structure"));
        boolean valid = multiblock.validate(world, pos, BlockRotation.NONE);
        if (valid) {
            world.setBlockState(pos, state.with(GENERATING, true));
            be.dragonEnergy++;
        } else {
            world.setBlockState(pos, state.with(GENERATING, false));
        }
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.putInt("de", dragonEnergy);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        dragonEnergy = nbt.getInt("de");
    }
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

}
