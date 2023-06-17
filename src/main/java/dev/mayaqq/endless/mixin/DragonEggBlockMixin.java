package dev.mayaqq.endless.mixin;

import dev.mayaqq.endless.networking.PacketMethods;
import dev.mayaqq.endless.registry.ItemRegistry;
import dev.mayaqq.endless.utils.AdvancementUtils;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DragonEggBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static dev.mayaqq.endless.Endless.id;

@Mixin(DragonEggBlock.class)
public class DragonEggBlockMixin {
    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    private void endless$onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (!world.isClient && player.getMainHandStack().getItem() == ItemRegistry.ENDER_WRENCH) {
            cir.setReturnValue(ActionResult.success(false));
        }
        sendCutscene(player, world);
    }
    @Inject(method = "onBlockBreakStart", at = @At("HEAD"))
    private void endless$onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player, CallbackInfo ci) {
        sendCutscene(player, world);
    }
    private void sendCutscene(PlayerEntity undefinedPlayer, World world) {
        if (!world.isClient && !AdvancementUtils.hasAdvancement(undefinedPlayer, id("click_egg"))) {
            ServerPlayerEntity player = (ServerPlayerEntity) undefinedPlayer;
            PacketMethods.showCutscene(player, "You need to find a different way to get it...");
            PlayerAdvancementTracker advancementTracker = player.getAdvancementTracker();
            Advancement advancement = world.getServer().getAdvancementLoader().get(id("click_egg"));
            advancementTracker.grantCriterion(advancement, "clicked_egg");
        }
    }
}