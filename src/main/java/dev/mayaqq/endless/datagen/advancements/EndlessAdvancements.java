package dev.mayaqq.endless.datagen.advancements;

import de.dafuqs.revelationary.advancement_criteria.AdvancementGottenCriterion;
import dev.mayaqq.endless.registry.EndlessBlocks;
import dev.mayaqq.endless.registry.EndlessItems;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.ImpossibleCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Items;
import net.minecraft.text.Text;

import java.util.function.Consumer;

import static dev.mayaqq.endless.Endless.id;

public class EndlessAdvancements implements Consumer<Consumer<Advancement>> {
    @Override
    public void accept(Consumer<Advancement> consumer) {
        Advancement rootAdvancement = Advancement.Builder.create().display(EndlessBlocks.CHORUS_PLANT_ROOT, // The display icon
                Text.translatable("advancements.endless.root.title"), // The title
                Text.translatable("advancements.endless.root.description"), // The description
                id("textures/block/chorus_plant_root.png"), // Background image used
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
                .build(consumer, "endless:the_next_generation");

        Advancement openedCodex = Advancement.Builder.create().display(EndlessItems.VOID_CODEX,
                Text.translatable("advancements.endless.opened_codex.title"),
                Text.translatable("advancements.endless.opened_codex.description"),
                null,
                AdvancementFrame.TASK,
                true,
                true,
                false
        )
                .criterion("opened_codex", InventoryChangedCriterion.Conditions.items(Items.BOOK))
                .criterion("gotten_previous", AdvancementGottenCriterion.create(rootAdvancement.getId()))
                .parent(rootAdvancement)
                .build(consumer, "endless:open_codex");

        Advancement generateEndergy = Advancement.Builder.create().display(EndlessBlocks.THE_CORE,
                Text.translatable("advancements.endless.generate_endergy.title"),
                Text.translatable("advancements.endless.generate_endergy.description"),
                null,
                AdvancementFrame.TASK,
                true,
                true,
                false
        )
                .criterion("generated_endergy", new ImpossibleCriterion.Conditions())
                .criterion("gotten_previous", AdvancementGottenCriterion.create(theNextGeneration.getId()))
                .parent(theNextGeneration)
                .build(consumer, "endless:generate_endergy");
    }
}
