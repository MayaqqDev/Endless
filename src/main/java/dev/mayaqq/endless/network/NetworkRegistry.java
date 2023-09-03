package dev.mayaqq.endless.network;

import com.kneelawk.graphlib.api.graph.GraphUniverse;
import com.kneelawk.graphlib.api.graph.user.GraphEntityType;
import com.kneelawk.graphlib.api.world.SaveMode;
import dev.mayaqq.endless.network.node.ConnectorBlockNode;
import dev.mayaqq.endless.registry.blocks.base.NetworkComponent;

import java.util.List;

import static dev.mayaqq.endless.Endless.id;

public class NetworkRegistry {
    public static final GraphUniverse UNIVERSE = GraphUniverse.builder()
            .saveMode(SaveMode.INCREMENTAL)
            .build(id("endergy"));
    public static final GraphEntityType<UpdateHandler> UPDATE_HANDLER_TYPE = GraphEntityType.of(id("update_handler"), UpdateHandler::new);

    public static void register() {
        UNIVERSE.register();
        UNIVERSE.addDiscoverer((world, pos) -> {
            if (world.getBlockState(pos).getBlock() instanceof NetworkComponent component) {
                return List.of(component.getNode());
            }
            return List.of();
        });
        UNIVERSE.addGraphEntityTypes(UPDATE_HANDLER_TYPE);
        UNIVERSE.addNodeTypes(ConnectorBlockNode.TYPE);
    }
}
