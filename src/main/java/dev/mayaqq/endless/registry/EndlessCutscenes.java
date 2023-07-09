package dev.mayaqq.endless.registry;

import dev.mayaqq.endless.client.api.cutscenes.Cutscene;
import dev.mayaqq.endless.registry.endless.EndlessRegistries;
import net.minecraft.registry.*;
import net.minecraft.text.Text;

import static dev.mayaqq.endless.Endless.id;

public class EndlessCutscenes {
    public static final Cutscene EGG_REMINDER = Registry.register(EndlessRegistries.CUTSCENE, id("egg_reminder"), new Cutscene(
            id("egg_reminder"),
            new Text[]{Text.translatable("cutscene.endless.egg_reminder"), Text.translatable("cutscene.endless.egg_reminder2")}));

    public static final Cutscene CLICK_EGG = Registry.register(EndlessRegistries.CUTSCENE, id("click_egg"), new Cutscene(
            id("click_egg"),
            new Text[]{Text.translatable("cutscene.endless.click_egg")}));

    public static final Cutscene OPEN_CODEX = Registry.register(EndlessRegistries.CUTSCENE, id("open_codex"), new Cutscene(
            id("open_codex"),
            new Text[]{Text.translatable("cutscene.endless.open_codex"), Text.translatable("cutscene.endless.open_codex2")}));

    public static void register() {}
}
