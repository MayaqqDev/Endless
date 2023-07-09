package dev.mayaqq.endless.mixin;

import de.dafuqs.revelationary.api.advancements.AdvancementHelper;
import dev.mayaqq.endless.client.api.cutscenes.Cutscene;
import dev.mayaqq.endless.registry.EndlessCutscenes;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.block.BlockState;
import net.minecraft.block.DragonEggBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static dev.mayaqq.endless.Endless.id;

@Mixin(DragonEggBlock.class)
public class DragonEggBlockMixin {
    @Inject(method = "onUse", at = @At("HEAD"))
    private void endless$onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        sendCutscene(player, world);
    }
    @Inject(method = "onBlockBreakStart", at = @At("HEAD"))
    private void endless$onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player, CallbackInfo ci) {
        sendCutscene(player, world);
    }

    @Unique
    private void sendCutscene(PlayerEntity undefinedPlayer, World world) {
        if (!world.isClient && !AdvancementHelper.hasAdvancement(undefinedPlayer, id("click_egg"))) {
            ServerPlayerEntity player = (ServerPlayerEntity) undefinedPlayer;
            EndlessCutscenes.CLICK_EGG.play(player);
            PlayerAdvancementTracker advancementTracker = player.getAdvancementTracker();
            MinecraftServer server = world.getServer();
            if (server != null) {
                Advancement advancement = world.getServer().getAdvancementLoader().get(id("click_egg"));
                advancementTracker.grantCriterion(advancement, "clicked_egg");
            }
        }
    }
}