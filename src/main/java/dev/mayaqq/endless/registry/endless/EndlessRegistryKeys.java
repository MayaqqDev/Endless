package dev.mayaqq.endless.registry.endless;

import dev.mayaqq.endless.client.api.cutscenes.Cutscene;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

import static dev.mayaqq.endless.Endless.id;

public class EndlessRegistryKeys {
    public static final RegistryKey<Registry<Cutscene>> CUTSCENE = RegistryKey.ofRegistry(id("cutscene"));
}
