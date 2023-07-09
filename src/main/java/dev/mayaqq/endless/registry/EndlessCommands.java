package dev.mayaqq.endless.registry;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.mayaqq.endless.registry.commands.EndlessCutsceneCommand;
import dev.mayaqq.endless.registry.commands.suggestionProviders.CutsceneSuggestionProvider;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;

public class EndlessCommands {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            LiteralCommandNode<ServerCommandSource> endless = CommandManager.literal("endless").build();

            LiteralCommandNode<ServerCommandSource> cutscene = CommandManager.literal("cutscene").build();

            ArgumentCommandNode<ServerCommandSource, Identifier> cutsceneId = CommandManager.argument("cutscene", IdentifierArgumentType.identifier()).suggests(new CutsceneSuggestionProvider()).executes(EndlessCutsceneCommand::run).build();

            dispatcher.getRoot().addChild(endless);
            endless.addChild(cutscene);
            cutscene.addChild(cutsceneId);
        });
    }
}
