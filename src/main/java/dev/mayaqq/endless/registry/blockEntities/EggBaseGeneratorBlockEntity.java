package dev.mayaqq.endless.registry.blockEntities;

import dev.mayaqq.endless.energy.storage.EndergyUser;
import dev.mayaqq.endless.registry.EndlessBlockEntities;
import dev.mayaqq.endless.registry.blocks.EggBaseGeneratorBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class EggBaseGeneratorBlockEntity extends BlockEntity implements EndergyUser, GeoBlockEntity {
    protected boolean endergyDirty;

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation EGG_BASE_GENERATOR_ANIM = RawAnimation.begin();

    public EggBaseGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(EndlessBlockEntities.EGG_BASE_GENERATOR_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, EggBaseGeneratorBlockEntity be) {
        if (world.getBlockState(pos.up()) == Blocks.DRAGON_EGG.getDefaultState()) {
            world.setBlockState(pos, state.with(EggBaseGeneratorBlock.ACTIVE, true));
        } else {
            world.setBlockState(pos, state.with(EggBaseGeneratorBlock.ACTIVE, false));
            return;
        }
        if (world.getBlockState(pos).get(EggBaseGeneratorBlock.ACTIVE)) {
            switch (state.get(EggBaseGeneratorBlock.LEVEL)) {
            }
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
