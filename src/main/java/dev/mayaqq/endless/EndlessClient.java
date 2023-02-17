package dev.mayaqq.endless;

import dev.mayaqq.endless.client.renderer.entity.ShockwaveEntityRenderer;
import dev.mayaqq.endless.registry.EntityRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;


public class EndlessClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EntityRegistry.SHOCKWAVE, ShockwaveEntityRenderer::new);

    }
}
