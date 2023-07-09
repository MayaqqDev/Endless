package dev.mayaqq.endless.client;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.mayaqq.endless.Endless;
import dev.mayaqq.endless.client.api.cutscenes.CutsceneRenderer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

import static dev.mayaqq.endless.Endless.id;

public class ClientEvents {

    public static void init() {
        CutsceneRenderer.init();
    }
}