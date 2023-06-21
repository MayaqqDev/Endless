package dev.mayaqq.endless.config;

import dev.mayaqq.endless.Endless;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.SectionHeader;

@Modmenu(modId = Endless.MOD_ID)
@Config(name = Endless.MOD_ID + "-config", wrapperName = "EndlessConfig", saveOnModification = true)
public class EndlessConfigClass {
    @SectionHeader("Client")
    public boolean cutSceneTextCentered = true;
    public boolean showCutScenes = true;
}
