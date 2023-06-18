package dev.mayaqq.endless.worldgen.features.chorusRoot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record ChorusPlantRootFeatureConfig(int size, BlockStateProvider stateProvider) implements FeatureConfig {
    public ChorusPlantRootFeatureConfig(int size, BlockStateProvider stateProvider) {
        this.size = size;
        this.stateProvider = stateProvider;
    }

    public static Codec<ChorusPlantRootFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("size").forGetter(config -> config.size),
            BlockStateProvider.TYPE_CODEC.fieldOf("stateProvider").forGetter(config -> config.stateProvider)
    ).apply(instance, ChorusPlantRootFeatureConfig::new));

    public int getSize() {
        return size;
    }

    public BlockStateProvider getBlockStateProvider() {
        return stateProvider;
    }
}
