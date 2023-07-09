package dev.mayaqq.endless.registry.commands.suggestionProviders;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import dev.mayaqq.endless.client.api.cutscenes.Cutscene;
import dev.mayaqq.endless.registry.endless.EndlessRegistries;
import net.minecraft.server.command.ServerCommandSource;

import java.util.concurrent.CompletableFuture;

public class CutsceneSuggestionProvider implements SuggestionProvider<ServerCommandSource> {

    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> context, SuggestionsBuilder builder) throws CommandSyntaxException {
        for (Cutscene cutscene : EndlessRegistries.CUTSCENE) {
            builder.suggest(cutscene.id().toString());
        }
        return builder.buildFuture();
    }
}
