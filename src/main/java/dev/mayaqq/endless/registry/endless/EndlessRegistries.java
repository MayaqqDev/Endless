package dev.mayaqq.endless.registry.endless;

import com.mojang.serialization.Lifecycle;
import dev.mayaqq.endless.client.api.cutscenes.Cutscene;
import net.minecraft.registry.Registry;
import net.minecraft.registry.SimpleRegistry;

public class EndlessRegistries {
    public static final Registry<Cutscene> CUTSCENE = new SimpleRegistry<>(EndlessRegistryKeys.CUTSCENE, Lifecycle.stable(), false);

}
