package dev.mayaqq.endless.network.node;

import com.kneelawk.graphlib.api.graph.NodeHolder;
import com.kneelawk.graphlib.api.graph.user.BlockNode;
import com.kneelawk.graphlib.api.wire.FullWireBlockNode;
import dev.mayaqq.endless.network.NetworkRegistry;
import dev.mayaqq.endless.network.UpdateHandler;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EndergyNetworkBlockNode extends FullWireBlockNode {
    @Override
    default void onConnectionsChanged(@NotNull NodeHolder<BlockNode> self) {
        var graph = self.getGraphWorld().getGraph(self.getGraphId());
        if (graph != null) {
            graph.getGraphEntity(NetworkRegistry.UPDATE_HANDLER_TYPE).scheduleUpdate(UpdateHandler.ChangeType.CONTENT);
        }
    }

    @Override
    @Nullable
    default NbtElement toTag() {
        return null;
    }

    default void update(ServerWorld world, NodeHolder<BlockNode> node) {
    }
}
