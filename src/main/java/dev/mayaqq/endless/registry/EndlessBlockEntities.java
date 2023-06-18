package dev.mayaqq.endless.registry;

import dev.mayaqq.endless.Endless;
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
    public static void register() {
        FluidStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.fluidStorage, MAKESHIFT_VOID_EXTRACTOR_ENTITY);

    }
}
