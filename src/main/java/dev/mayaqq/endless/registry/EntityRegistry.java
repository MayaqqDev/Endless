package dev.mayaqq.endless.registry;

import dev.mayaqq.endless.Endless;
import dev.mayaqq.endless.entities.ShockwaveEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;

public class EntityRegistry {

    public static final LinkedHashMap<EntityType<?>, Identifier> ENTITIES = new LinkedHashMap<>();

    public static final EntityType<ShockwaveEntity> SHOCKWAVE = create("shockwave",
            FabricEntityTypeBuilder.<ShockwaveEntity>create(SpawnGroup.MISC, ShockwaveEntity::new)
                    .dimensions(EntityDimensions.fixed(0F, 0F))
                    .trackRangeChunks(640)
                    .build());
    public static void register() {
        ENTITIES.keySet().forEach(entityType -> Registry.register(Registries.ENTITY_TYPE, ENTITIES.get(entityType), entityType));
    }
    private static <T extends Entity> EntityType<T> create(String name, EntityType<T> type) {
        ENTITIES.put(type, Endless.id(name));
        return type;
    }
}
