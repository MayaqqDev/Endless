package dev.mayaqq.endless.registry;

import dev.mayaqq.endless.Endless;
import dev.mayaqq.endless.registry.blockEntities.EggBaseGeneratorBlockEntity;
import dev.mayaqq.endless.registry.blockEntities.CoreConnectorBlockEntity;
import dev.mayaqq.endless.registry.blockEntities.MakeshiftVoidExtractorBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class EndlessBlockEntities {
    public static final BlockEntityType<MakeshiftVoidExtractorBlockEntity> MAKESHIFT_VOID_EXTRACTOR_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Endless.id("makeshift_void_extractor_entity"),
            FabricBlockEntityTypeBuilder.create(MakeshiftVoidExtractorBlockEntity::new, EndlessBlocks.MAKESHIFT_VOID_EXTRACTOR_BLOCK).build()
    );
    public static final BlockEntityType<EggBaseGeneratorBlockEntity> EGG_BASE_GENERATOR_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Endless.id("egg_base_generator_entity"),
            FabricBlockEntityTypeBuilder.create(EggBaseGeneratorBlockEntity::new, EndlessBlocks.EGG_BASE_GENERATOR).build()
    );
    public static final BlockEntityType<CoreConnectorBlockEntity> CORE_CONNECTOR = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Endless.id("core_connector"),
            FabricBlockEntityTypeBuilder.create(CoreConnectorBlockEntity::new, EndlessBlocks.CORE_CONNECTOR).build()
    );
    public static void register() {
        FluidStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.fluidStorage, MAKESHIFT_VOID_EXTRACTOR_ENTITY);

    }
}
