package dev.mayaqq.endless.client;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeybindRegistry {
    public static KeyBinding cutscene;
    public static void register() {
        cutscene = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.endless.cutscene",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_ENTER,
                "category.endless"
        ));
    }
}
