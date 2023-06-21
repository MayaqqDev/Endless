package dev.mayaqq.endless.client.registry;

import dev.mayaqq.endless.client.registry.gecko.renderers.EggBaseGeneratorRenderer;
import dev.mayaqq.endless.registry.EndlessBlockEntities;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;

public class EndlessModels {
    public static void register() {
        BlockEntityRendererRegistry.register(EndlessBlockEntities.EGG_BASE_GENERATOR_ENTITY,
                context -> new EggBaseGeneratorRenderer());
    }
}
