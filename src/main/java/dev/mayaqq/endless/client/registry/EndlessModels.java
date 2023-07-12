package dev.mayaqq.endless.client.registry;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import dev.mayaqq.endless.client.registry.gecko.renderers.EggBaseGeneratorRenderer;
import dev.mayaqq.endless.registry.EndlessBlockEntities;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;

import static dev.mayaqq.endless.Endless.id;

public class EndlessModels {
    public static void register() {
        BlockEntityRendererRegistry.register(EndlessBlockEntities.EGG_BASE_GENERATOR_ENTITY,
                context -> new EggBaseGeneratorRenderer());

        TerraformBoatClientHelper.registerModelLayers(id("chorus_boat"), false);

        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, id("entity/signs/chorus")));
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, id("entity/signs/chorus")));
    }
}
