package dev.mayaqq.endless.client.registry.gecko.renderers;

import dev.mayaqq.endless.client.registry.gecko.models.EggBaseGeneratorModel;
import dev.mayaqq.endless.registry.blockEntities.TheCoreBlockEntity;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class EggBaseGeneratorRenderer extends GeoBlockRenderer<TheCoreBlockEntity> {
    public EggBaseGeneratorRenderer() {
        super(new EggBaseGeneratorModel());
    }
}
