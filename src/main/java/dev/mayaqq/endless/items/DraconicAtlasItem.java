package dev.mayaqq.endless.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import vazkii.patchouli.api.PatchouliAPI;

import static dev.mayaqq.endless.Endless.id;

public class DraconicAtlasItem extends Item {
    public DraconicAtlasItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient && user instanceof ServerPlayerEntity serverPlayerEntity) {
            if (isNewPlayer(serverPlayerEntity)) {
                openGuidebook(serverPlayerEntity);
                //openGuidebook(serverPlayerEntity, id("general/intro"), 0);
            } else {
                openGuidebook(serverPlayerEntity);
            }

            user.incrementStat(Stats.USED.getOrCreateStat(this));

            return TypedActionResult.success(user.getStackInHand(hand));
        } else {
            return TypedActionResult.consume(user.getStackInHand(hand));
        }
    }
    private boolean isNewPlayer(ServerPlayerEntity serverPlayerEntity) {
        return serverPlayerEntity.getStatHandler().getStat(Stats.USED, this) == 0;
    }

    private void openGuidebook(ServerPlayerEntity serverPlayerEntity) {
        PatchouliAPI.get().openBookGUI(serverPlayerEntity, id("draconic_atlas"));
    }
    private void openGuidebook(ServerPlayerEntity serverPlayerEntity, Identifier entry, int page) {
        PatchouliAPI.get().openBookEntry(serverPlayerEntity, id("draconic_atlas"), entry, page);
    }
}
