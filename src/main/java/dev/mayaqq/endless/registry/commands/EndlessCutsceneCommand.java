package dev.mayaqq.endless.registry.commands;

import com.mojang.brigadier.context.CommandContext;
import dev.mayaqq.endless.Endless;
import dev.mayaqq.endless.client.api.cutscenes.Cutscene;
import dev.mayaqq.endless.registry.endless.EndlessRegistries;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class EndlessCutsceneCommand {
    public static int run(CommandContext<ServerCommandSource> context) {
        ServerPlayerEntity player = context.getSource().getPlayer();
        Cutscene cutscene = EndlessRegistries.CUTSCENE.get(IdentifierArgumentType.getIdentifier(context, "cutscene"));
        cutscene.play(player);
        return 0;
    }
}
