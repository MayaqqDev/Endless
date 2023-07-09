package dev.mayaqq.endless.client.api.cutscenes;

import dev.mayaqq.endless.Endless;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public record Cutscene(Identifier id, Text[] lines) {

    @Environment(EnvType.CLIENT)
    public void play() {
        CutsceneRenderer.renderTextCutScene(this);
    }

    public void play(ServerPlayerEntity player) {
        play(id, player);
    }

    /**
     * @deprecated While this method is still functional, it is recommended to use {@link dev.mayaqq.endless.registry.endless.EndlessRegistries} instead.
     */
    @Deprecated
    public static void playTextCutscene(ServerPlayerEntity player, String line) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(line);
        ServerPlayNetworking.send(player, Endless.id("cutscenewithtext"), buf);
    }

    public static void play(Identifier id, ServerPlayerEntity player) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeIdentifier(id);
        ServerPlayNetworking.send(player, Endless.id("cutscene"), buf);
    }
}
