package dev.mayaqq.endless;

import dev.mayaqq.endless.networking.C2S;
import dev.mayaqq.endless.registry.BlockRegistry;
import dev.mayaqq.endless.registry.ItemRegistry;
import dev.mayaqq.endless.server.ServerEvents;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class Endless implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Endless");
    public static final String MOD_ID = "endless";
    public static Path SERVER_DATA_PATH;
    @Override
    public void onInitialize() {
        LOGGER.info("The Void is endless.");
        ServerEvents.init();
        BlockRegistry.register();
        ItemRegistry.register();
        C2S.register();
    }

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
}