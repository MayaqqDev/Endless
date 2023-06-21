package dev.mayaqq.endless.registry;

import dev.mayaqq.endless.registry.items.DescriptedItem;
import dev.mayaqq.endless.registry.items.VoidCodexItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import static dev.mayaqq.endless.Endless.id;

public class EndlessItems {
    public static final VoidCodexItem VOID_CODEX = Registry.register(Registries.ITEM, id("void_codex"), new VoidCodexItem(new VoidCodexItem.Settings().maxCount(1)));
    public static final DescriptedItem CHORUS_STRING = Registry.register(Registries.ITEM, id("chorus_string"), new DescriptedItem(new Item.Settings(), Text.translatable("item.endless.chorus_string.desc")));
    public static void register() {}
}