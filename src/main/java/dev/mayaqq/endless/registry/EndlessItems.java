package dev.mayaqq.endless.registry;

import dev.mayaqq.endless.registry.items.EnderWrenchItem;
import dev.mayaqq.endless.registry.items.VoidCodexItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static dev.mayaqq.endless.Endless.id;

public class EndlessItems {
    public static final VoidCodexItem VOID_CODEX = Registry.register(Registries.ITEM, id("void_codex"), new VoidCodexItem(new VoidCodexItem.Settings().maxCount(1)));
    public static final EnderWrenchItem ENDER_WRENCH = Registry.register(Registries.ITEM, id("ender_wrench"), new EnderWrenchItem(new Item.Settings().maxCount(1)));
    public static void register() {}
}