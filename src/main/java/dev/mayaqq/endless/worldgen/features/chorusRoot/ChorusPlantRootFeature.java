package dev.mayaqq.endless.worldgen.features.chorusRoot;

import com.mojang.serialization.Codec;
import dev.mayaqq.endless.Endless;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.ChorusPlantFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class ChorusPlantRootFeature extends Feature<ChorusPlantRootFeatureConfig> {
    public ChorusPlantRootFeature(Codec<ChorusPlantRootFeatureConfig> configCodec) {
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

        if (world.getBlockState(pos.up()) == Blocks.END_STONE.getDefaultState()) {
            pos = pos.up();
            for (int i = 0; i < size; i++) {
                pos = pos.down();
                if (random.nextInt(5) % 5 == 0) {
                    switch (random.nextInt(3)) {
                        case 0 -> pos = pos.offset(Direction.NORTH);
                        case 1 -> pos = pos.offset(Direction.EAST);
                        case 2 -> pos = pos.offset(Direction.SOUTH);
                        case 3 -> pos = pos.offset(Direction.WEST);
                    }
                }
                BlockPos blockPos = pos;
                if (world.isAir(blockPos) || world.getBlockState(blockPos).getBlock() == Blocks.END_STONE) {
                    world.setBlockState(blockPos, provider.get(random, pos), 1);
                }
            }
        }
        return true;
    }
}
