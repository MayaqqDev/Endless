package dev.mayaqq.endless.registry;

import dev.mayaqq.endless.blockEntities.WyrngeartDynamoEntity;
import dev.mayaqq.endless.blocks.WyrngeartDynamoBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static dev.mayaqq.endless.Endless.id;

public class BlockRegistry {
    public static final Block NIGHTSHADE_TUFT = new Block(FabricBlockSettings.of(Material.GOURD));

    public static final WyrngeartDynamoBlock WYRNGEART_DYNAMO = new WyrngeartDynamoBlock(FabricBlockSettings.of(Material.METAL));
    public static final BlockEntityType<WyrngeartDynamoEntity> WYRNGEART_DYNAMO_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, id("wyrngeart_dynamo"), FabricBlockEntityTypeBuilder.create(WyrngeartDynamoEntity::new, WYRNGEART_DYNAMO).build());

    public static void register() {
        Registry.register(Registries.BLOCK, id("nightshade_tuft"), NIGHTSHADE_TUFT);
        Registry.register(Registries.ITEM, id("nightshade_tuft"), new BlockItem(NIGHTSHADE_TUFT, new FabricItemSettings()));

        Registry.register(Registries.BLOCK, id("wyrngeart_dynamo"), WYRNGEART_DYNAMO);
        Registry.register(Registries.ITEM, id("wyrngeart_dynamo"), new BlockItem(WYRNGEART_DYNAMO, new FabricItemSettings()));
    }
}
