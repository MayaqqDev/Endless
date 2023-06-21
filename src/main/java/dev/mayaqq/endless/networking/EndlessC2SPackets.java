package dev.mayaqq.endless.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

import static dev.mayaqq.endless.Endless.id;

public class EndlessC2SPackets {
    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(id("cutscene_done"), (server, player, handler, buf, responseSender) -> {
            Identifier id = buf.readIdentifier();
            server.execute(() -> {

            });
        });
    }
}
