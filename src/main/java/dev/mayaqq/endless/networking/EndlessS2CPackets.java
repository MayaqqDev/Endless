package dev.mayaqq.endless.networking;

import dev.mayaqq.endless.Endless;
import dev.mayaqq.endless.client.ClientEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class EndlessS2CPackets {
    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(Endless.id("cutscene"), (client, handler, buf, responseSender) -> {
            String message = buf.readString();
            client.execute(() -> {
                ClientEvents.renderTextCutScene(message);
            });
        });
    }
}
