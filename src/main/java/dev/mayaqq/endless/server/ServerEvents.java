package dev.mayaqq.endless.server;

import dev.mayaqq.endless.Endless;
import dev.mayaqq.endless.data.ServerState;
import dev.mayaqq.endless.data.VoidItemsStorage;
import dev.mayaqq.endless.registry.EndlessBlocks;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import static dev.mayaqq.endless.Endless.MOD_ID;

public class ServerEvents {
    public static ArrayList<BlockPos> corruptionAwaiting = new ArrayList<>();
    public static HashMap<BlockPos, World> corruption = new HashMap<>();
    public static void init() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            Endless.SERVER_DATA_PATH = Path.of(server.getSavePath(WorldSavePath.ROOT) + "/" + MOD_ID);
            VoidItemsStorage.init();
            ServerState.getServerState(server);
            Endless.SERVER = server;
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            if (!corruptionAwaiting.isEmpty()) {
                BlockPos pos = corruptionAwaiting.get(0);
                World world = corruption.get(pos);
                if (world != null && world.getBottomY() != pos.getY()) {
                    world.setBlockState(pos, EndlessBlocks.CORRUPTION.getDefaultState());
                }
                corruption.remove(pos);
                corruptionAwaiting.remove(0);
            }
        });
    }
}