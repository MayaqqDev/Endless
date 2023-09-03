package dev.mayaqq.endless.network.node;

import com.kneelawk.graphlib.api.graph.user.BlockNodeType;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static dev.mayaqq.endless.Endless.id;

public class ConnectorBlockNode implements EndergyNetworkBlockNode {
    public static final Identifier ID = id("connector");
    public static final ConnectorBlockNode INSTANCE = new ConnectorBlockNode();
    public static final BlockNodeType TYPE = BlockNodeType.of(ID, tag -> INSTANCE);

    private ConnectorBlockNode() {
    }

    @Override
    public @NotNull BlockNodeType getType() {
        return TYPE;
    }
}
