package dev.mayaqq.endless.registry;

import dev.mayaqq.endless.registry.blockEntities.TheCoreBlockEntity;
import dev.mayaqq.endless.registry.blockEntities.MakeshiftVoidExtractorBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static dev.mayaqq.endless.Endless.id;

public class EndlessBlockEntities {
    public static final BlockEntityType<MakeshiftVoidExtractorBlockEntity> MAKESHIFT_VOID_EXTRACTOR_ENTITY = registerBlockEntity("makeshift_void_extractor_entity", build(MakeshiftVoidExtractorBlockEntity::new, EndlessBlocks.MAKESHIFT_VOID_EXTRACTOR_BLOCK));
    public static final BlockEntityType<TheCoreBlockEntity> EGG_BASE_GENERATOR_ENTITY = registerBlockEntity("egg_base_generator_entity", build(TheCoreBlockEntity::new, EndlessBlocks.THE_CORE));

    public static void register() {
        FluidStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.fluidStorage, MAKESHIFT_VOID_EXTRACTOR_ENTITY);
    }

    private static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String id, BlockEntityType<T> entity) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id(id), entity);
    }

    private static <T extends BlockEntity> BlockEntityType<T> build(FabricBlockEntityTypeBuilder.Factory<T> factory, Block block) {
        return FabricBlockEntityTypeBuilder.create(factory, block).build();
    }
}
