package dev.mayaqq.endless.client.api.cutscenes;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.mayaqq.endless.Endless;
import dev.mayaqq.endless.client.KeybindRegistry;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

public class CutsceneRenderer {

    static boolean cutSceneInProgress = false;
    static int cutScenePhase = 0;

    static int renderTick = 0;
    static int duration = 0;
    static ArrayList<String> cutSceneBuffer = new ArrayList<>();
    static String text = "";
    static int textPhase = 0;
    static boolean shouldContinue = true;
    static String fullText = "";

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (GLFW.glfwGetKey(client.getWindow().getHandle(), GLFW.GLFW_KEY_ESCAPE) == GLFW.GLFW_PRESS && cutSceneInProgress) {
                cutSceneBuffer.clear();
                cutSceneInProgress = false;
                cutScenePhase = 0;
                text = "";
                textPhase = 0;
                shouldContinue = true;
            }
            if (client.options.hudHidden && cutSceneInProgress) {
                client.options.hudHidden = false;
            }
            if (KeybindRegistry.cutscene.wasPressed() && cutSceneInProgress) {
                cutScenePhase++;
            }
        });
        HudRenderCallback.EVENT.register((matrices, tickDelta) -> {
            if (!cutSceneBuffer.isEmpty()) {
                fullText = cutSceneBuffer.get(0);
                cutSceneInProgress = true;
            }
            if (cutSceneInProgress) {
                duration++;
                renderTick++;
                if (renderTick == 5) {
                    renderTick = 0;
                }
                MinecraftClient client = MinecraftClient.getInstance();
                client.options.hudHidden = false;

                int windowWidth = client.getWindow().getScaledWidth();
                int windowHeight = client.getWindow().getScaledHeight();

                int white = 0xFFFFFFFF;
                int black = 0x80000000;

                client.getFramebuffer().beginWrite(false);
                RenderSystem.enableDepthTest();

                TextRenderer textRenderer = client.textRenderer;
                MatrixStack matrixStack = matrices.getMatrices();
                matrixStack.push();
                matrixStack.translate(0, 0, 1000);

                matrices.fill(0, 0, windowWidth, windowHeight, black);
                if (renderTick == 0 && shouldContinue) {
                    if (textPhase == fullText.length()) {
                        shouldContinue = false;
                    } else {
                        text += fullText.charAt(textPhase);
                        textPhase++;
                    }
                }
                if (Endless.CONFIG.cutSceneTextCentered()) {
                    matrices.drawCenteredTextWithShadow(textRenderer, text, windowWidth / 2, windowHeight / 2 - 20, white);
                } else {
                    int center = windowWidth / 2 - textRenderer.getWidth(fullText) / 2;
                    matrices.drawText(textRenderer, text, center, windowHeight / 2 - 20, white, true);
                }
                String pressText = "Press " + KeybindRegistry.cutscene.getBoundKeyLocalizedText().getString() + " to continue";
                matrices.drawText(
                        textRenderer,
                        pressText,
                        windowWidth - textRenderer.getWidth(pressText) - 5,
                        windowHeight - textRenderer.fontHeight - 5,
                        white,
                        true
                );

                matrixStack.pop();
                RenderSystem.disableDepthTest();

                client.getFramebuffer().endWrite();
                if (cutScenePhase == 1 || duration > 1000) {
                    duration = 0;
                    cutSceneBuffer.remove(0);
                    cutSceneInProgress = false;
                    cutScenePhase = 0;
                    text = "";
                    textPhase = 0;
                    shouldContinue = true;
                }
            }
        });
    }

    public static void renderTextCutScene(String text) {
        if (Endless.CONFIG.showCutScenes()) {
            cutSceneBuffer.add(text);
        }
    }

    public static void renderTextCutScene(Cutscene cutsceneInstance) {
        if (Endless.CONFIG.showCutScenes()) {
            for (Text line : cutsceneInstance.lines()) {
                cutSceneBuffer.add(line.getString());
            }
        }
    }
}
