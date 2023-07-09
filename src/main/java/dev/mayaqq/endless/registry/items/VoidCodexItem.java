package dev.mayaqq.endless.registry.items;

import de.dafuqs.revelationary.api.advancements.AdvancementHelper;
import dev.mayaqq.endless.client.api.cutscenes.Cutscene;
import dev.mayaqq.endless.registry.EndlessCutscenes;
import io.wispforest.lavender.book.BookItem;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import static dev.mayaqq.endless.Endless.id;

public class VoidCodexItem extends BookItem {
    public VoidCodexItem(Settings settings) {
        super(settings, id("void_codex"));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!AdvancementHelper.hasAdvancement(user, id("open_codex"))) {
            if (!world.isClient) {
                ServerPlayerEntity player = (ServerPlayerEntity) user;
                PlayerAdvancementTracker advancementTracker = player.getAdvancementTracker();
                Advancement advancement = world.getServer().getAdvancementLoader().get(id("open_codex"));
                advancementTracker.grantCriterion(advancement, "opened_codex");
                EndlessCutscenes.OPEN_CODEX.play(player);
            }
        } else {
            return super.use(world, user, hand);
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
