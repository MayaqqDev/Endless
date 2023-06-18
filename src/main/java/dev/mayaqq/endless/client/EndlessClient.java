package dev.mayaqq.endless.client;

import dev.mayaqq.endless.client.rendering.FluidRenderer;
import dev.mayaqq.endless.networking.EndlessS2CPackets;
import net.fabricmc.api.ClientModInitializer;

public class EndlessClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientEvents.init();
        KeybindRegistry.register();
        EndlessS2CPackets.register();
        FluidRenderer.register();
    }
}