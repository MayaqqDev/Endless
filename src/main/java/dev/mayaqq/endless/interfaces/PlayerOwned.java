package dev.mayaqq.endless.interfaces;

import dev.mayaqq.endless.Endless;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface PlayerOwned {
    static PlayerEntity getPlayerEntityIfOnline(UUID ownerUUID) {
        if (ownerUUID != null && Endless.SERVER != null) {
            return Endless.SERVER.getPlayerManager().getPlayer(ownerUUID);
        }
        return null;
    }

    UUID getOwnerUUID();

    void setOwner(PlayerEntity playerEntity);

    default boolean hasOwner() {
        return getOwnerUUID() != null;
    }

    default boolean isOwner(PlayerEntity playerEntity) {
        return playerEntity.getUuid().equals(getOwnerUUID());
    }

    @Nullable
    default PlayerEntity getOwnerIfOnline() {
        UUID ownerUUID = this.getOwnerUUID();
        if (ownerUUID != null) {
            return Endless.SERVER.getPlayerManager().getPlayer(ownerUUID);
        }
        return null;
    }
}
