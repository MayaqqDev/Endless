package dev.mayaqq.endless.network;

import com.kneelawk.graphlib.api.graph.GraphEntityContext;
import com.kneelawk.graphlib.api.graph.user.GraphEntity;
import com.kneelawk.graphlib.api.graph.user.GraphEntityType;
import dev.mayaqq.endless.network.node.EndergyNetworkBlockNode;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class UpdateHandler implements GraphEntity<UpdateHandler> {
    private GraphEntityContext context;
    @Nullable
    private ChangeType queuedUpdate;

    public static void scheduleUpdate(ServerWorld world, BlockPos pos, ChangeType type) {
        NetworkRegistry.UNIVERSE.getServerGraphWorld(world)
                .getLoadedGraphsAt(pos)
                .map(graph -> graph.getGraphEntity(NetworkRegistry.UPDATE_HANDLER_TYPE))
                .forEach(updateHandler -> updateHandler.scheduleUpdate(type));
    }

    public void scheduleUpdate(ChangeType type) {
        queuedUpdate = ObjectUtils.max(queuedUpdate, type);
    }

    @Override
    public void onTick() {
        if (queuedUpdate == null) return;
        if (!(context.getBlockWorld() instanceof ServerWorld world)) return;

        context.getGraph()
                .getNodes()
                .forEach(node -> {
                    if (node.getNode() instanceof EndergyNetworkBlockNode drawerNode) {
                        drawerNode.update(world, node);
                    }
                });
        queuedUpdate = null;
    }

    @Override
    public void onInit(@NotNull GraphEntityContext context) {
        this.context = context;
    }

    @Override
    public @NotNull GraphEntityContext getContext() {
        return context;
    }

    @Override
    public @NotNull GraphEntityType<?> getType() {
        return NetworkRegistry.UPDATE_HANDLER_TYPE;
    }

    @Override
    public @Nullable NbtElement toTag() {
        return null;
    }

    @Override
    public void onUpdate() {
        scheduleUpdate(ChangeType.STRUCTURE);
    }

    @Override
    public void merge(@NotNull UpdateHandler other) {
    }

    public enum ChangeType {
        COUNT, // Need to send packets to client
        CONTENT, // Need to sort slots (includes lock changes)
        STRUCTURE // Need to rebuild storage list
    }
}
