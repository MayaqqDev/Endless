package dev.mayaqq.endless.networking;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Identifier;

import static dev.mayaqq.endless.Endless.id;

public class PacketMethods {
    public static void showCutscene(ServerPlayerEntity player, Text message, Identifier id) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(message.getString());
        buf.writeIdentifier(id);
        ServerPlayNetworking.send(player, id("cutscene"), buf);
    }
}
