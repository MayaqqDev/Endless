package dev.mayaqq.endless.registry.blocks;

import dev.mayaqq.endless.interfaces.EntityExtension;
import dev.mayaqq.endless.registry.EndlessBlocks;
import dev.mayaqq.endless.server.ServerEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class CorruptionBlock extends Block {
    public CorruptionBlock(Settings settings) {
        super(settings.noCollision().dropsNothing().solid());
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            entity.damage(livingEntity.getDamageSources().outOfWorld(), 2.0F);
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        Vec3d v = entity.getVelocity();
        ((EntityExtension) entity).tickEntityVoid();
        entity.setVelocity(v.x, Math.max(-0.1F, v.y), v.z);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        BlockPos offset = getRandomOffset(pos, world.getRandom());
        while (true) {
            if (world.getBlockState(offset).getBlock() == EndlessBlocks.CORRUPTION) {
                offset = getRandomOffset(pos, world.getRandom());
            } else {
                if (world.getRandom().nextInt(64) != 0) {
                    ServerEvents.corruptionAwaiting.add(offset);
                    ServerEvents.corruption.put(offset, world);
                }
                break;
            }
        }
        super.onBlockAdded(state, world, pos, oldState, notify);
    }

    public static BlockPos getRandomOffset(BlockPos pos, Random random) {
        switch (random.nextInt(7)) {
            case 0, 5, 6 -> pos = pos.down();
            case 1 -> pos = pos.north();
            case 2 -> pos = pos.south();
            case 3 -> pos = pos.east();
            case 4 -> pos = pos.west();
        }
        return pos;
    }
}
