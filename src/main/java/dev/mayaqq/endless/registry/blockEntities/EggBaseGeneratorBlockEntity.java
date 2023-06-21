package dev.mayaqq.endless.registry.blockEntities;

import dev.mayaqq.endless.energy.storage.EndergyStorageBlockEntity;
import dev.mayaqq.endless.energy.storage.TotalCappedEndergyStorage;
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

public class EggBaseGeneratorBlockEntity extends BlockEntity implements EndergyStorageBlockEntity<TotalCappedEndergyStorage>, GeoBlockEntity {
    protected TotalCappedEndergyStorage endergyStorage;
    protected boolean endergyDirty;

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation EGG_BASE_GENERATOR_ANIM = RawAnimation.begin();

    public EggBaseGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(EndlessBlockEntities.EGG_BASE_GENERATOR_ENTITY, pos, state);
        this.endergyStorage = new TotalCappedEndergyStorage(5000);
    }

    public static void tick(World world, BlockPos pos, BlockState state, EggBaseGeneratorBlockEntity be) {
        if (world.getBlockState(pos.up()) == Blocks.DRAGON_EGG.getDefaultState()) {
            world.setBlockState(pos, state.with(EggBaseGeneratorBlock.ACTIVE, true));
        } else {
            world.setBlockState(pos, state.with(EggBaseGeneratorBlock.ACTIVE, false));
            return;
        }
        if (world.getBlockState(pos).get(EggBaseGeneratorBlock.ACTIVE)) {
            int generatedEnergy = 0;
            switch (state.get(EggBaseGeneratorBlock.LEVEL)) {
                case 0 -> generatedEnergy = 1;
                case 1 -> generatedEnergy = 2;
                case 2 -> generatedEnergy = 4;
                case 3 -> generatedEnergy = 8;
            }
            be.endergyStorage.addEnergy(generatedEnergy);
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        if (nbt.contains("EndergyStorage", NbtElement.COMPOUND_TYPE)) {
            this.endergyStorage = TotalCappedEndergyStorage.fromNbt(nbt.getCompound("EndergyStorage"));
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.put("EndergyStorage", this.endergyStorage.toNbt());
    }

    @Override
    public TotalCappedEndergyStorage getEnergyStorage() {
        return this.endergyStorage;
    }

    @Override
    public void setEndergyDirty() {
        this.endergyDirty = true;
    }

    @Override
    public boolean getEndergyDirty() {
        return this.endergyDirty;
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
