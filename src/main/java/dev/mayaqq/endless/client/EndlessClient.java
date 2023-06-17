package dev.mayaqq.endless.client;

import dev.mayaqq.endless.networking.S2C;
import net.fabricmc.api.ClientModInitializer;

public class EndlessClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientEvents.init();
        KeybindRegistry.register();
        S2C.register();
    }
}