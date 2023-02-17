package dev.mayaqq.endless;

import dev.mayaqq.endless.registry.BlockRegistry;
import dev.mayaqq.endless.registry.EntityRegistry;
import dev.mayaqq.endless.registry.ItemRegistry;
import dev.mayaqq.endless.registry.MultiblockRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Endless implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("endless");

	@Override
	public void onInitialize() {
		ItemRegistry.register();
		BlockRegistry.register();
		MultiblockRegistry.register();
		EntityRegistry.register();
		LOGGER.info("The void is endless.");
	}

	public static Identifier id(String path) {
		return new Identifier("endless", path);
	}
}