package dev.mayaqq.endless.client.registry.gecko.models;

import dev.mayaqq.endless.registry.blockEntities.TheCoreBlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;

import static dev.mayaqq.endless.Endless.id;

public class EggBaseGeneratorModel extends DefaultedBlockGeoModel<TheCoreBlockEntity> {
    private final Identifier EGG_BASE_GENERATOR_MODEL = buildFormattedModelPath(id("egg_base_generator"));
    private final Identifier EGG_BASE_GENERATOR_TEXTURE = buildFormattedTexturePath(id("egg_base_generator"));
    private final Identifier EGG_BASE_GENERATOR_ANIMATIONS = buildFormattedAnimationPath(id("egg_base_generator"));
    public EggBaseGeneratorModel() {
        super(id("egg_base_generator"));
    }

    @Override
    public Identifier getAnimationResource(TheCoreBlockEntity animatable) {
        return EGG_BASE_GENERATOR_ANIMATIONS;
    }
    @Override
    public Identifier getModelResource(TheCoreBlockEntity animatable) {
        return EGG_BASE_GENERATOR_MODEL;
    }

    @Override
    public Identifier getTextureResource(TheCoreBlockEntity animatable) {
        return EGG_BASE_GENERATOR_TEXTURE;
    }

    @Override
    public RenderLayer getRenderType(TheCoreBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
