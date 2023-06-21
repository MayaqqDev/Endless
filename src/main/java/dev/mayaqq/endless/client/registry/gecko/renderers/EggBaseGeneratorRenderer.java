package dev.mayaqq.endless.client.registry.gecko.renderers;

import dev.mayaqq.endless.client.registry.gecko.models.EggBaseGeneratorModel;
import dev.mayaqq.endless.registry.blockEntities.EggBaseGeneratorBlockEntity;
import software.bernie.example.client.model.block.FertilizerModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class EggBaseGeneratorRenderer extends GeoBlockRenderer<EggBaseGeneratorBlockEntity> {
    public EggBaseGeneratorRenderer() {
        super(new EggBaseGeneratorModel());
    }
}
