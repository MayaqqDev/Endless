package dev.mayaqq.endless.worldgen.features.chorusRoot;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class ChorusPlantRootPlacedFeature extends Feature<ChorusPlantRootFeatureConfig> {
    public ChorusPlantRootPlacedFeature(Codec<ChorusPlantRootFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<ChorusPlantRootFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();
        Random random = context.getRandom();
        ChorusPlantRootFeatureConfig config = context.getConfig();

        int size = config.getSize();
        BlockStateProvider provider = config.getBlockStateProvider();

        size -= random.nextInt(3);

        pos = pos.up();

        for (int i = 0; i < size; i++) {
            BlockPos blockPos = pos.down();
            if (random.nextInt(3) == 1) {
                int randomNumber = random.nextInt(9);
                if (randomNumber < 4) {
                    switch (randomNumber) {
                        case 0 -> blockPos = blockPos.north();
                        case 1 -> blockPos = blockPos.south();
                        case 2 -> blockPos = blockPos.east();
                        case 3 -> blockPos = blockPos.west();
                    }
                }
            }
            if (world.isAir(blockPos) || world.getBlockState(blockPos).getBlock() == Blocks.END_STONE) {
                world.setBlockState(blockPos, provider.get(random, pos), 1);
            }
        }
        return true;
    }
}
