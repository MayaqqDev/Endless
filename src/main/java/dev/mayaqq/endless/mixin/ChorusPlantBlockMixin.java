package dev.mayaqq.endless.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.mayaqq.endless.datagen.tags.EndlessTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChorusPlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChorusPlantBlock.class)
public class ChorusPlantBlockMixin {

    @Unique private BlockState state;

    @ModifyExpressionValue(
            method = "withConnectionProperties",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 2)
    )
    private boolean endless$makeChorusWorkWithTag1(boolean original) {
        return state.isIn(EndlessTags.BlockTags.ENDER_GROUND);
    }

    @Inject(method = "withConnectionProperties", at = @At("HEAD"))
    private void endless$withConnectionProperties(BlockView world, BlockPos pos, CallbackInfoReturnable<BlockState> cir) {
        this.state = world.getBlockState(pos.down());
    }

    @ModifyExpressionValue(
            method = "getStateForNeighborUpdate",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 2)
    )
    private boolean endless$makeChorusWorkWithTag2(boolean original) {
        return state.isIn(EndlessTags.BlockTags.ENDER_GROUND);
    }

    @Inject(method = "getStateForNeighborUpdate", at = @At("HEAD"))
    private void endless$getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
        this.state = neighborState;
    }

    @ModifyExpressionValue(
            method = "canPlaceAt",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 2)
    )
    private boolean endless$makeChorusWorkWithTag3(boolean original) {
        return state.isIn(EndlessTags.BlockTags.ENDER_GROUND);
    }

    @ModifyExpressionValue(
            method = "canPlaceAt",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 4)
    )
    private boolean endless$makeChorusWorkWithTag4(boolean original) {
        return state.isIn(EndlessTags.BlockTags.ENDER_GROUND);
    }

    @Inject(method = "canPlaceAt", at = @At("HEAD"))
    private void endless$canPlaceAt(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        this.state = world.getBlockState(pos.down());
    }
}
