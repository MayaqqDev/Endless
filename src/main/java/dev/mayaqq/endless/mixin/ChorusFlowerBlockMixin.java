package dev.mayaqq.endless.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.mayaqq.endless.datagen.tags.EndlessTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChorusFlowerBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChorusFlowerBlock.class)
public class ChorusFlowerBlockMixin {
    @Unique
    private BlockState state;


    @ModifyExpressionValue(
            method = "randomTick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 0)
    )
    private boolean endless$makeChorusWorkWithTag1(boolean original) {
        return state.isIn(EndlessTags.BlockTags.ENDER_GROUND);
    }

    @ModifyExpressionValue(
            method = "randomTick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 3)
    )
    private boolean endless$makeChorusWorkWithTag2(boolean original) {
        return state.isIn(EndlessTags.BlockTags.ENDER_GROUND);
    }

    @ModifyExpressionValue(
            method = "canPlaceAt",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 1)
    )
    private boolean endless$makeChorusWorkWithTag3(boolean original) {
        return state.isIn(EndlessTags.BlockTags.ENDER_GROUND);
    }

    @Inject(method = "canPlaceAt", at = @At("HEAD"))
    private void endless$canPlaceAt(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        this.state = world.getBlockState(pos.down());
    }

    @Inject( method = "randomTick", at = @At(value = "HEAD"))
    private void endless$randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        this.state = world.getBlockState(pos.down());
    }
}
