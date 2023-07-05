package dev.mayaqq.endless.registry;

import dev.mayaqq.endless.registry.items.DescriptedItem;
import dev.mayaqq.endless.registry.items.VoidCodexItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

import static dev.mayaqq.endless.Endless.id;

public class EndlessItems {
    public static final VoidCodexItem VOID_CODEX = Registry.register(Registries.ITEM, id("void_codex"), new VoidCodexItem(new VoidCodexItem.Settings().maxCount(1)));
    public static final DescriptedItem CHORUS_STRING = Registry.register(Registries.ITEM, id("chorus_string"), new DescriptedItem(new Item.Settings(), Text.translatable("item.endless.chorus_string.desc")));

    public static final ItemGroup ENDLESS_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(VOID_CODEX))
            .displayName(Text.translatable("itemGroup.endless.main"))
            .build();
    public static final RegistryKey<ItemGroup> ENDLESS_GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, id("endless"));

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, id("endless"), ENDLESS_GROUP);
        ItemGroupEvents.modifyEntriesEvent(ENDLESS_GROUP_KEY).register(content -> {
            for (Item item : Registries.ITEM) {
                if (item.getTranslationKey().split("\\.")[1].equals("endless")) {
                    content.add(new ItemStack(item));
                }
            }
        });
    }
}