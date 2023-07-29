package dev.mayaqq.endless.registry.blockEntities;

import dev.mayaqq.endless.registry.EndlessBlockEntities;
import dev.mayaqq.endless.registry.blocks.TheCoreBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class TheCoreBlockEntity extends BlockEntity implements GeoBlockEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation EGG_BASE_GENERATOR_ANIM = RawAnimation.begin();

    public TheCoreBlockEntity(BlockPos pos, BlockState state) {
        super(EndlessBlockEntities.EGG_BASE_GENERATOR_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, TheCoreBlockEntity be) {
        if (world.getBlockState(pos.up()) == Blocks.DRAGON_EGG.getDefaultState()) {
            world.setBlockState(pos, state.with(TheCoreBlock.ACTIVE, true));
        } else {
            world.setBlockState(pos, state.with(TheCoreBlock.ACTIVE, false));
            return;
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
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

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, state -> {
            return state.setAndContinue(EGG_BASE_GENERATOR_ANIM);
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
