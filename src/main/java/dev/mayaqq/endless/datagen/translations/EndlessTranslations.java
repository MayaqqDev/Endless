package dev.mayaqq.endless.datagen.translations;

import dev.mayaqq.endless.client.KeybindRegistry;
import dev.mayaqq.endless.registry.EndlessBlocks;
import dev.mayaqq.endless.registry.EndlessFluids;
import dev.mayaqq.endless.registry.EndlessItems;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class EndlessTranslations {
    public static void generateEnUsTranslations(FabricLanguageProvider.TranslationBuilder tb) {
        // keybinds
        tb.add("category.endless", "Endless");
        tb.add(KeybindRegistry.cutscene.getTranslationKey(), "Continue Cutscene");

        // cutscenes
            // when the player kills the dragon
        tb.add("cutscene.endless.egg_reminder", "The Dragon egg seems to be emitting a <shake>strange</shake> energy...");
        tb.add("cutscene.endless.egg_reminder2", "You should probably check it out...");
            // first click on egg
        tb.add("cutscene.endless.click_egg", "You need to find a different way to get it...");
            // first opens codex
        tb.add("cutscene.endless.open_codex", "This is the Void Codex");
        tb.add("cutscene.endless.open_codex2", "You will write all your (endless) findings here.");

        // advancements
        tb.add("advancements.endless.root.title", "Endless");
            tb.add("advancements.endless.root.description", "The beginning of the end.");
        tb.add("advancements.endless.click_egg.title", "Not this way...");
            tb.add("advancements.endless.click_egg.description", "Try to click the Dragon Egg and learn that you can't.");
        tb.add("advancements.endless.the_next_generation.title", "The Next Generation");
            tb.add("advancements.endless.the_next_generation.description", "Hold the Dragon Egg.");
        tb.add("advancements.endless.opened_codex.title", "Staring into the Void (Codex)");
            tb.add("advancements.endless.opened_codex.description", "Open the Void Codex for the first time.");

        // config
        tb.add("text.config.endless-config.title", "Endless Config");
            tb.add("text.config.endless-config.section.Client.title", "Client");
                tb.add("text.config.endless-config.option.cutSceneTextCentered", "Move Cutscene Text to Center");
            tb.add("text.config.endless-config.section.Server", "Server");

        // creative tab groups
        tb.add("itemGroup.endless.main", "Endless");

        // items
        tb.add(EndlessItems.CHORUS_BOAT_ITEM, "Chorus Boat");
        tb.add(EndlessItems.CHEST_CHORUS_BOAT_ITEM, "Chest Chorus Boat");
        tb.add(EndlessItems.VOID_CODEX, "Void Codex");
        tb.add(EndlessItems.CHORUS_STRING, "Chorus String");
            tb.add("item.endless.chorus_string.desc", "Like normal string, but more purple.");
        tb.add(EndlessFluids.VOID_FLUID_BUCKET, "Void Fluid Bucket");

        // blocks
        tb.add(EndlessBlocks.MAKESHIFT_VOID_EXTRACTOR_BLOCK, "Makeshift Void Extractor");
            // wood
        tb.add(EndlessBlocks.CHORUS_DOOR, "Chorus Door");
        tb.add(EndlessBlocks.CHORUS_TRAPDOOR, "Chorus Trapdoor");
        tb.add(EndlessBlocks.CHORUS_FENCE, "Chorus Fence");
        tb.add(EndlessBlocks.CHORUS_FENCE_GATE, "Chorus Fence Gate");
        tb.add(EndlessBlocks.CHORUS_PRESSURE_PLATE, "Chorus Pressure Plate");
        tb.add(EndlessBlocks.CHORUS_BUTTON, "Chorus Button");
        tb.add(EndlessBlocks.CHORUS_STAIRS, "Chorus Stairs");
        tb.add(EndlessBlocks.CHORUS_SLAB, "Chorus Slab");
        tb.add(EndlessBlocks.CHORUS_PLANKS, "Chorus Planks");
        tb.add(EndlessBlocks.CHORUS_PLANT_ROOT, "Chorus Plant Root");
        tb.add(EndlessBlocks.STRIPPED_CHORUS_PLANT_ROOT, "Stripped Chorus Plant Root");
        tb.add(EndlessBlocks.CHORUS_WOOD, "Chorus Wood");
        tb.add(EndlessBlocks.STRIPPED_CHORUS_WOOD, "Stripped Chorus Wood");
        tb.add(EndlessBlocks.CHORUS_SIGN, "Chorus Sign");
        tb.add(EndlessBlocks.CHORUS_WALL_SIGN, "Chorus Wall Sign");
        tb.add(EndlessBlocks.CHORUS_HANGING_SIGN, "Chorus Hanging Sign");
        tb.add(EndlessBlocks.CHORUS_HANGING_WALL_SIGN, "Chorus Hanging Wall Sign");

        tb.add(EndlessBlocks.CORE_CONNECTOR, "Core Connector");
        tb.add(EndlessBlocks.CORRUPTION, "Corruption");
        tb.add(EndlessBlocks.THE_CORE, "The Core");

        // fluids
        tb.add(EndlessFluids.VOID_FLUID, "Void Fluid");
    }
}
