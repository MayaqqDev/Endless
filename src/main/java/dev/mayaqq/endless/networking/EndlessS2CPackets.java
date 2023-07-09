package dev.mayaqq.endless.networking;

import dev.mayaqq.endless.Endless;
import dev.mayaqq.endless.client.api.cutscenes.Cutscene;
import dev.mayaqq.endless.client.api.cutscenes.CutsceneRenderer;
import dev.mayaqq.endless.registry.EndlessCutscenes;
import dev.mayaqq.endless.registry.endless.EndlessRegistries;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

public class EndlessS2CPackets {
    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(Endless.id("cutscenewithtext"), (client, handler, buf, responseSender) -> {
            String message = buf.readString();
            client.execute(() -> {
                CutsceneRenderer.renderTextCutScene(message);
            });
        });
        ClientPlayNetworking.registerGlobalReceiver(Endless.id("cutscene"), (client, handler, buf, responseSender) -> {
            Identifier id = buf.readIdentifier();
            client.execute(() -> {
                EndlessRegistries.CUTSCENE.get(id).play();
            });
        });
    }
}
