package dev.mayaqq.endless.registry;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
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
import software.bernie.geckolib.event.GeoRenderEvent;

import static dev.mayaqq.endless.Endless.id;

public class EndlessItems {
    public static final Item VOID_CODEX = registerItem("void_codex", new VoidCodexItem(new VoidCodexItem.Settings().maxCount(1)));
    public static final Item CHORUS_STRING = registerItem("chorus_string", new DescriptedItem(new Item.Settings(), Text.translatable("item.endless.chorus_string.desc")));

    public static final Item CHORUS_BOAT_ITEM = TerraformBoatItemHelper.registerBoatItem(id("chorus_boat"), TerraformBoatTypeRegistry.createKey(id("chorus_boat")), false);
    public static final Item CHEST_CHORUS_BOAT_ITEM = TerraformBoatItemHelper.registerBoatItem(id("chest_chorus_boat"), TerraformBoatTypeRegistry.createKey(id("chest_chorus_boat")), true);
    public static final TerraformBoatType CHORUS_BOAT = new TerraformBoatType.Builder()
            .item(CHORUS_BOAT_ITEM)
            .chestItem(CHEST_CHORUS_BOAT_ITEM)
            .planks(EndlessBlocks.CHORUS_PLANKS.asItem())
            .build();

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
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, id("chorus_boat"), CHORUS_BOAT);
    }

    protected static Item registerItem(String id, Item item) {
        return Registry.register(Registries.ITEM, id(id), item);
    }
}