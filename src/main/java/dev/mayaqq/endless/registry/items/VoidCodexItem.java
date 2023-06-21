package dev.mayaqq.endless.registry.items;

import de.dafuqs.revelationary.api.advancements.AdvancementHelper;
import dev.mayaqq.endless.networking.PacketMethods;
import io.wispforest.lavender.book.BookItem;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
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
                PacketMethods.showCutscene(player, Text.translatable("cutscene.endless.open_codex"), id("open_codex"));
                PacketMethods.showCutscene(player, Text.translatable("cutscene.endless.open_codex2"), id("open_codex"));

            }
        } else {
            return super.use(world, user, hand);
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
