package dev.mayaqq.endless.networking;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

import static dev.mayaqq.endless.Endless.id;

public class PacketMethods {
    public static void showCutscene(ServerPlayerEntity player, String message) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(message);
        ServerPlayNetworking.send(player, id("cutscene"), buf);
    }
}
