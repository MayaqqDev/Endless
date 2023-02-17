package dev.mayaqq.endless.registry;

import dev.mayaqq.endless.items.DraconicAtlasItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static dev.mayaqq.endless.Endless.id;

public class ItemRegistry {
    public static final DraconicAtlasItem DRACONIC_ATLAS = new  DraconicAtlasItem(new Item.Settings().maxCount(1));
    public static final Item CHORUS_STRING = new Item(new Item.Settings());
    public static void register() {
        Registry.register(Registries.ITEM, id("draconic_atlas"), DRACONIC_ATLAS);
        Registry.register(Registries.ITEM, id("chorus_string"), CHORUS_STRING);
    }
}