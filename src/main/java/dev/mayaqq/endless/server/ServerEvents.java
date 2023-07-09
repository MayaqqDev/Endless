package dev.mayaqq.endless.server;

import dev.mayaqq.endless.Endless;
import dev.mayaqq.endless.data.ServerState;
import dev.mayaqq.endless.data.VoidItemsStorage;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.util.WorldSavePath;

import java.nio.file.Path;

import static dev.mayaqq.endless.Endless.MOD_ID;

public class ServerEvents {
    public static void init() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            Endless.SERVER_DATA_PATH = Path.of(server.getSavePath(WorldSavePath.ROOT) + "/" + MOD_ID);
            VoidItemsStorage.init();
            ServerState.getServerState(server);
            Endless.SERVER = server;
        });
    }
}
