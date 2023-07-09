package dev.mayaqq.endless.mixin;

import de.dafuqs.revelationary.api.advancements.AdvancementHelper;
import dev.mayaqq.endless.client.api.cutscenes.Cutscene;
import dev.mayaqq.endless.registry.EndlessCutscenes;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.block.Blocks;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.EndPortalFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicBoolean;

import static dev.mayaqq.endless.Endless.id;

@Mixin(EnderDragonFight.class)
public class EnderDragonFightMixin {
    @Shadow @Final private ServerWorld world;

    @Shadow private boolean previouslyKilled;
    @Final
    @Shadow private BlockPos origin;

    @Inject(method = "dragonKilled", at = @At("HEAD"))
    private void injectDragonKilled(EnderDragonEntity dragon, CallbackInfo ci) {
        AtomicBoolean hasAdvancement = new AtomicBoolean(true);
        if (!this.world.isClient) {
            this.world.getServer().getPlayerManager().getPlayerList().forEach(player -> {
                if (player.getWorld().getRegistryKey() == World.END && !AdvancementHelper.hasAdvancement(player, id("root"))) {
                    hasAdvancement.set(false);
                    EndlessCutscenes.EGG_REMINDER.play(player);
                    PlayerAdvancementTracker advancementTracker = player.getAdvancementTracker();
                    Advancement advancement = this.world.getServer().getAdvancementLoader().get(id("root"));
                    advancementTracker.grantCriterion(advancement, "got_first_cutscene");
                }
            });
        }
        if (previouslyKilled && !hasAdvancement.get()) {
            this.world.setBlockState(this.world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, EndPortalFeature.offsetOrigin(this.origin)), Blocks.DRAGON_EGG.getDefaultState());
        }
    }
}