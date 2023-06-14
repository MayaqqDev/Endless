package dev.mayaqq.endless;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Endless implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Endless");
    public static final String MOD_ID = "endless";
    @Override
    public void onInitialize() {
        LOGGER.info("The Void is endless.");
    }

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
}
