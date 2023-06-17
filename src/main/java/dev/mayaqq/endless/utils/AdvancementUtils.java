package dev.mayaqq.endless.utils;

import dev.mayaqq.endless.Endless;
import net.minecraft.advancement.Advancement;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/* This will get replaced with Revelationary once it supports 1.20.1 */
public class AdvancementUtils {
    public static boolean hasAdvancement(PlayerEntity playerEntity, Identifier advancementIdentifier) {
        if (playerEntity == null) {
            return false;
        } else if (advancementIdentifier == null) {
            return true;
        } else if (playerEntity instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)playerEntity;
            Advancement advancement = serverPlayerEntity.server.getAdvancementLoader().get(advancementIdentifier);
            if (advancement == null) {
                Text name = playerEntity.getName();
                Endless.LOGGER.warn("Player " + name + " was getting an advancement check for an advancement that does not exist: " + advancementIdentifier);
                return false;
            } else {
                return serverPlayerEntity.getAdvancementTracker().getProgress(advancement).isDone();
            }
        }
        return false;
    }

}
