package dev.mayaqq.endless.datagen.advancements;

import dev.mayaqq.endless.utils.advancement.AdvancementGottenCriterion;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.ImpossibleCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

import static dev.mayaqq.endless.Endless.id;

public class EndlessAdvancements implements Consumer<Consumer<Advancement>> {
    @Override
    public void accept(Consumer<Advancement> consumer) {
        Advancement rootAdvancement = Advancement.Builder.create().display(Items.DEBUG_STICK, // The display icon
                Text.translatable("advancements.endless.root.title"), // The title
                Text.translatable("advancements.endless.root.description"), // The description
                new Identifier("textures/block/sculk_catalyst_top.png"), // Background image used
                AdvancementFrame.TASK, // Options: TASK, CHALLENGE, GOAL
                true, // Show toast top right
                true, // Announce to chat
                false // Hidden in the advancement tab
        ).criterion("got_first_cutscene", new ImpossibleCriterion.Conditions()).build(consumer, "endless:root");

        Advancement clickEggAdvancement = Advancement.Builder.create().display(Items.BARRIER,
                Text.translatable("advancements.endless.click_egg.title"),
                Text.translatable("advancements.endless.click_egg.description"),
                null,
                AdvancementFrame.TASK,
                true,
                true,
                true
        ).criterion("clicked_egg", new ImpossibleCriterion.Conditions()).parent(rootAdvancement).build(consumer, "endless:click_egg");

        Advancement theNextGeneration = Advancement.Builder.create().display(Items.DRAGON_EGG,
                Text.translatable("advancements.endless.the_next_generation.title"),
                Text.translatable("advancements.endless.the_next_generation.description"),
                null,
                AdvancementFrame.TASK,
                false,
                false,
                true
        )
                .criterion("got_egg", InventoryChangedCriterion.Conditions.items(Items.DRAGON_EGG))
                .criterion("gotten_previous", AdvancementGottenCriterion.create(rootAdvancement.getId()))
                .parent(rootAdvancement)
                .rewards(AdvancementRewards.Builder.loot(id("advancement_reward/dragon_egg"))).build(consumer, "endless:the_next_generation");
    }
}
